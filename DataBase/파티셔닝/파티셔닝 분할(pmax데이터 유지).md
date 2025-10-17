# 파티셔닝 분할(pmax데이터 유지)

---

>

## 학습 개요

- 월단위 파티셔닝을 하는 프로시저를 만들어놓고 DB 이벤트를 이용해 돌리고 있었다. 
  - 프로시저 에러로 인한 p202509 파티셔닝 테이블 생성 X → 그로인해 p202509 데이터가 pmax 에 데이터 적제
  - 다음달 예약 이벤트로 인해 프로시저 수행 → p202510 새 파티셔닝 추가 로직으로 인해 pmax 파티셔닝 데이터 삭제됨

- pmax 에 있는 데이터를 안전하게 분할하기 위해 찾아보다 다음을 발견했다. 
  - `REORGANIZE PARTITION` 은  MySQL에서 **기존 파티션을 병합하거나 나누는(Divide or Merge)** 명령이다.  
  - 즉, 기존 파티션의 **데이터 범위는 유지하면서**  **새로운 경계값으로 재구성(reorganize)** 할 때 사용한다.


## 사용 이유

- 원래 파티션을 추가하기 위해선 뒤에오는 파티션들을 drop 하고 추가해야 했다. 
  - ex) **월별 파티셔닝 pmax 뒤에 다음 달 파티셔닝**을 추가하기 위해선 **pmax를 삭제 후 추가**해야했다. 
  - 하지만 이렇게 할 경우 pmax에 있는 데이터가 유실될 위험이 존재한다. 
- **데이터 유실을 최소화 하기 위해 `REORGANIZE PARTITION` 를 사용하게 되었다.** 

| 목적                             | 설명                                                |
| -------------------------------- | --------------------------------------------------- |
| 새 파티션 추가                   | `pmax`를 쪼개서 새로운 달(예: `p202511`) 추가       |
| 파티션 범위 수정                 | 경계값(`VALUES LESS THAN`) 잘못된 경우 수정         |
| 여러 파티션 병합                 | 불필요한 월/분기 파티션을 합쳐 단순화               |
| **데이터 손실 없이 구조 변경**   | 기존 데이터를 삭제하지 않고 재분배 가능             |
| `ADD PARTITION` 불가능할 때 대안 | `pmax`가 있을 경우 `ADD PARTITION` 대신 사용해야 함 |

## 기본 문법

- `기존파티션` 들의 **전체 범위**는 `새파티션` 들이 **정확히 동일한 범위로 덮어야 함**
  - 범위가 다르면 `ERROR 1520` 발생 (`Reorganize of range partitions cannot change total ranges`)

- 즉, **기존 파티션의 description 이 `202509010000000` 라면, 재분할 되는 파티션의 범위도 기존파티션의 범위인 `202509010000000` 와 동일해야함**

```sql
ALTER TABLE 테이블명
REORGANIZE PARTITION 기존파티션1 [, 기존파티션2, ...]
INTO (
  PARTITION 새파티션1 VALUES LESS THAN (...),
  PARTITION 새파티션2 VALUES LESS THAN (...),
  ...
);
```

## 사용 예시

##### 새 달 파티션 추가 (pmax 포함)

```sql
ALTER TABLE test.TB_ED_HT_EVENT_TEST
REORGANIZE PARTITION pmax INTO (
  PARTITION p202511 VALUES LESS THAN ('20251201000000000'),
  PARTITION pmax VALUES LESS THAN (MAXVALUE)
);
```

- `pmax`를 쪼개서 새 파티션 `p202511` 추가
   (데이터 손실 없음)

##### 중간 구간 분할 (pmax 없이)

```sql
ALTER TABLE test.TB_ED_HT_EVENT_TEST
REORGANIZE PARTITION p202508, p202510, pmax INTO (
  PARTITION p202508 VALUES LESS THAN ('20250901000000000'),
  PARTITION p202509 VALUES LESS THAN ('20251001000000000'),
  PARTITION p202510 VALUES LESS THAN ('20251101000000000'),
    
);
```

- `p202508`~`p202510` 사이를 세 파티션으로 세분화
   (기존 구간 전체를 동일하게 덮기 때문에 안전)

##### 불필요한 파티션 병합

```sql
ALTER TABLE test.TB_ED_HT_EVENT_TEST
REORGANIZE PARTITION p202501, p202502 INTO (
  PARTITION p2025_Q1 VALUES LESS THAN ('20250401000000000')
);
```

- 두 달 파티션(`p202501`, `p202502`)을 `p2025_Q1`으로 병합

## 주의사항

| 항목                            | 설명                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| 전체 범위 일치 필수             | 기존 파티션들의 범위를 정확히 덮어야 함                      |
| 데이터 손실 위험                | 새 경계가 기존보다 좁으면 범위 밖 데이터 삭제됨              |
| `pmax`가 있을 때                | `ADD PARTITION` 대신 `REORGANIZE pmax` 사용                  |
| `information_schema.PARTITIONS` | 변경 후 새 경계값(`PARTITION_DESCRIPTION`) 갱신됨            |
| 잠금                            | DDL이므로 테이블 전체 잠금 발생 (짧은 기간)                  |
| 안전 절차                       | `SELECT MIN(OCCUR_DT), MAX(OCCUR_DT)`로 기존 범위 확인 후 실행 |



---

## 문제 발생 

- 기존 description 이 문자열이였는데, 15자리였다. 하지만 17자리로 변경하고 싶었다(17자리가 맞는데 잘못해서 DDL 정의시 15자리로 지정함)
  - **근데 여기서 재배치의 범위 문제가 발생했다.** 
  - 범위가 틀렸음 : `15자리 < 17자리`
- `RANGE COLUMNS` 파티션이 `VARCHAR` 타입일 경우, **숫자 크기 비교가 아니라 문자열 사전순(lexical) 비교**로 작동한다. 
  - 그래서 **자릿수가 다르거나 0으로 시작하는 값**은 의도한 순서와 다르게 판단되는것이다. 

#####  예시

```sql
PARTITION p1 VALUES LESS THAN ('2025090100000'), -- (13자리)
PARTITION p2 VALUES LESS THAN ('202509010000000') -- (15자리)
```

MySQL은 왼쪽부터 문자 단위로 비교하므로

```
'2025090100000'  < '202509010000000'  (짧은 게 작다고 판단)
'2025090100001'  < '202509010000000'  (문자열 비교로는 “크다”로 판단)
```

결과적으로 **의도한 시간/숫자 순서와 다르게 분류**될 수 있는것이다. 

##### 해결방법

- **나같은 경우는 뒤에 오는 파티션 범위를 이용해 범위를 번경하는 방법을 썻다.** 

- ex) p202509 가 15자리일 때, 해당 범위를 바꾸기 위해 뒤에 있는 p202510 의 17 자리 범위를 끌어와 사용한다. 

  ```sql
  ALTER TABLE daegu5g_dev.TB_ED_HT_EVENT_TEST
  REORGANIZE PARTITION p202509, p202510
  INTO (
    PARTITION p202509 VALUES LESS THAN ('20251101000000000'),
    PARTITION p202510 VALUES LESS THAN ('20251201000000000')
  );
  ```