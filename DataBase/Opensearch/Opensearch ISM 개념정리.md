# ISM (Index State Management) 개념 정리

---

>

## ISM이란?

- **ISM(Index State Management)**은 OpenSearch에서 **Index의 생명주기(Lifecycle)**를 정책(Policy)으로 정의하고
   **자동으로 상태를 전환·관리**해주는 기능이다.
- 언제 생성되고, 얼마나 유지되고, 언제 이동·삭제되는지 를 사람이 아니라 **정책으로 관리**한다.
- 즉, 
  - ISM은 Index를 자동으로 “태어나게 하고, 늙게 만들고, 죽이는 정책 엔진”이다.

---

## 사용이유

##### ISM 없을 때 문제

- Index 수동 삭제 → 실수 위험
- 오래된 데이터가 HOT 노드에 계속 존재
- 디스크 부족 → 클러스터 장애
- 로그/이벤트 데이터 관리 불가능

##### ISM 사용 시

- 자동 정리 (Retention)
- 비용 최적화 (Hot → Warm)
- 운영 안정성 확보
- 대규모 로그 시스템 필수 구성

---

## 동작 흐름

1. Index 생성
2. Policy 적용
3. ISM Job 주기적 실행
4. 조건 충족 여부 확인
5. Action 수행
6. 다음 State로 이동

---

## Alias와 ISM

- ISM은 보통 **Alias 기반**으로 동작
- 쓰기 Alias → 최신 Index
- 검색 Alias → 전체 Index

---

## 구조

```json
Policy
 └─ State (상태)
     └─ Action (행동)
         └─ Transition (조건)
```

##### 1. Policy (정책)

- Index에 적용되는 **상태 전이 규칙 집합**
- JSON 형태로 정의
- 예:
  - 7일은 HOT
  - 30일은 WARM
  - 90일 후 삭제

##### 2. State (상태)

- Index가 머무르는 **단계(Stage)**
- 대표적인 상태 예시
  - 이름은 자유롭게 정의 가능

| 상태   | 의미            |
| ------ | --------------- |
| hot    | 쓰기/검색 빈번  |
| warm   | 읽기 위주       |
| cold   | 거의 조회 안 함 |
| delete | 삭제 대상       |

##### 3. Action (행동)

- State에 진입하면 실행되는 작업
- 자주 쓰는 Action

| Action        | 설명             |
| ------------- | ---------------- |
| rollover      | 새 index 생성    |
| read_only     | 쓰기 차단        |
| allocation    | 특정 노드로 이동 |
| force_merge   | segment 병합     |
| delete        | index 삭제       |
| replica_count | replica 수 조절  |

##### 4. Transition (전이 조건)

- 다음 state로 넘어가는 조건
- 대표 조건
  - `min_index_age`→ index 생성 후 경과 시간
- 예:

```json
"transitions": [
  {
    "state_name": "warm",
    "conditions": {
      "min_index_age": "7d"
    }
  }
]
```

---

## Rollover와 ISM의 관계

##### Rollover란?

- Index 크기 / 문서 수 / 기간 기준으로
- **새로운 Index 생성**
- 예:

```
logs-000001 → logs-000002
```

##### ISM + Rollover 패턴

- Alias 기준 검색
- Index는 자동 증가
- 오래된 Index는 ISM이 관리