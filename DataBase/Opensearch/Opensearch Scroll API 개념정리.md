# OpenSearch Scroll API 개념 정리

---

>

## Scroll API 란? 

- Scroll API는 대량의 검색 결과를 한 번에 가져오지 않고, 검색 시점의 데이터를 스냅샷으로 고정한 뒤 일정 단위로 나눠서 조회하는 기능이다.
- 핵심 키워드 : **대량 데이터 / 순차 조회 / 스냅샷 / 배치 처리**

------

## Scroll API 사용이유

##### 기본 검색의 한계

- `from + size` 방식은
  - 10,000건 제한 (`max_result_window`)
  - offset 증가 시 성능 급격히 저하

##### Scroll API의 목적

- 수만 ~ 수백만 건 데이터를 **안전하게 전부 읽기**
- 전체 데이터를 한 번씩 순회해야 하는 작업용

##### 대표 사용 사례

- 데이터 마이그레이션
- 로그/이벤트 전체 분석
- 배치 처리
- 백업/재색인 작업

------

## 장점

##### 1. 대량 데이터 조회 가능

- `max_result_window` 제한 없음
- 수백만 건도 순차 처리 가능

##### 2. 결과 일관성 보장

- 최초 검색 시점의 **스냅샷 유지**
- 조회 중 데이터가 추가/수정돼도 결과 변하지 않음

#####  3. 단순한 구현

- offset 계산 불필요
- `scroll_id`만 사용

------

## 단점

###  1. 서버 메모리 사용

- Scroll Context를 서버에 유지
- 동시 사용 많으면 cluster 부담

###  2. 실시간성 없음

- 검색 시점 이후 데이터 x
- UI, 대시보드에는 부적합

### 3. 관리 필요

- `clear scroll` 안 하면 리소스 누수
- 장시간 scroll 유지 시 장애 위험

------

## 사용 부적합한 경우

1. 사용자 목록 페이징
2. 무한 스크롤 UI
3. 실시간 검색
4. 집계(Aggregation) 결과 조회

- 이런 경우는 **Search After** 또는 **Aggregation** 사용

------

## Scroll API 기본 동작 흐름

1. 최초 검색 (scroll 설정)
2. scroll_id 반환
3. scroll_id로 다음 데이터 요청
4. 결과 없을 때까지 반복
5. scroll context 해제

##### 1. 최초 검색

```json
POST /my-index/_search?scroll=1m
{
  "size": 1000,
  "query": {
    "match_all": {}
  }
}
```

##### 2. 다음 데이터 요청

```json
POST /_search/scroll
{
  "scroll": "1m",
  "scroll_id": "DXF1ZXJ5QW5kRmV0Y2gBAAAAAAA..."
}
```

##### 3. Scroll 종료

```json
DELETE /_search/scroll
{
  "scroll_id": "DXF1ZXJ5QW5kRmV0Y2gBAAAAAAA..."
}
```

------

## Scroll vs Search After

| 항목        | Scroll API       | Search After  |
| ----------- | ---------------- | ------------- |
| 목적        | 전체 데이터 순회 | 사용자 페이징 |
| 스냅샷      | O                | X             |
| 실시간성    | 낮음             | 높음          |
| 메모리 사용 | 높음             | 낮음          |
| 추천 용도   | 배치 처리        | UI 조회       |

------

## 요약

- Scroll API는 “대량 데이터를 스냅샷 기반으로 **안정적으로 끝까지 읽기 위한 배치 전용 조회 방식**”이다.