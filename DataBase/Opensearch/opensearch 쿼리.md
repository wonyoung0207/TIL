# Opensearch 쿼리 

---

>

## Query  

1. 쿼리의 구조는 Elasticsearch와 유사하며, 기본적으로 **JSON 형식**으로 작성된다. 

2. 다양한 엔드 포인트가 존재해 쿼리에 사용할 수 있다. 

   1. 각 엔드포인트는 특정 작업을 처리하는 데 최적화되어있다. 

   ```bash
   # 구조 
   GET /index_name/end_point
   
   # 예시 
   GET /index_name/_search
   ```

### Query 조회시 조심할점

##### 1. Timestamp 의 시간 기준을 잘 봐야한다. 

1. 조회기준에 맞춰 프론트에서 Date format 변경이 필요함 
2. https://www.epochconverter.com/

```js
// 그냥 날짜 포멧으로 변환
let searchStartDt1 =  dayjs(this.vehicleObj.startDt).format("YYYY-MM-DDTHH:mm:ss.SSS");

// UTC로 변환 후 날짜 포멧으로 변경 
let searchStartDt = dayjs.utc(this.vehicleObj.startDt, "YYYYMMDDHHmmssSSS").toISOString();

// UTC로 변환 후 Timestamp 포멧으로 변경 
let searchStartDt = dayjs.utc(this.vehicleObj.startDt, "YYYYMMDDHHmmssSSS").valueOf();
```

##### 2. 검색어로 조회시 "분석 필드 or 비분석 필드" 인지에 따라 결과값이 다르다. 

### Query 구조

1. 크게 **검색 조건(query)**과 **집계(aggregations)** 로 나뉜다. 

   ```json
   {
     "size": 10,  // 반환할 문서 수
     "query": {   // 검색 조건
       // 여기에 검색 조건을 작성
     },
     "aggs": {    // 집계 (옵션)
       // 여기에 집계 조건을 작성
     },
     "sort": [    // 정렬 기준 (옵션)
       { "@timestamp": { "order": "desc" } }
     ]
   }
   ```

##### 1. **query**

1. 쿼리의 본문
2. 검색 조건을 지정하는 부분.
3. 종류
   1. **match**: 단일 필드에서 텍스트를 검색
   2. **term**: 정확한 값을 검색
   3. **range**: 특정 범위 안에 있는 값을 검색 (예: 날짜 또는 숫자)
   4. **bool**: 복잡한 쿼리를 만들기 위한 논리적 연산을 제공

```json
{
  "query": {
    "match": { "field_name": "search_value" }
  }
}
```

##### 1-1 range

1. 기간 조회에 많이 사용된다. ( 범위 조회 )
2. 조건
   1. **`gte`**: greater than or equal (이상)
   2. **`lte`**: less than or equal (이하)
   3. **`gt`**: greater than (초과)
   4. **`lt`**: less than (미만)
   5. **`format`**: 날짜 필드에 사용할 때 특정 포맷을 지정할 수 있음 (예: `yyyy-MM-dd`)

```json
{
  "query": {
    "range": {
      "@timestamp": {
        "gte": "2024-10-01T00:00:00",  // 시작 날짜 (이상)
        "lte": "2024-10-15T23:59:59",  // 종료 날짜 (이하)
        "format": "yyyy-MM-dd'T'HH:mm:ss"  // 날짜 형식
      }
    }
  }
}
```

#### 1-2 wildcard

1. 문자열 필드에서 특정 패턴을 찾을 때 사용한다. 
2. 일반적으로 `*`(별표)와 `_`(언더스코어)를 사용해 문자열 내 위치와 개수에 맞는 문자를 찾는다. 
   1. , `*` : 여러 문자에 대응
   2. `_` :  한 글자에만 대응
3. 대소문자를 구별한다. 

```js
{
  "wildcard": {
    "productCode": "ABC*123" // "ABC123", "ABCD123", "ABCXYZ123" 등의 값인 경우 모두 조회
    "ipAddress": "192.168.1.*" // 192.168.1.1", "192.168.1.100" 등 192.168.1.로 시작하는 모든 IP 주소를 조회
    "videoFilePath": "*20241025175500_20241025180000*" // "20241025175500_20241025180000" 문자열이 포함된 모든 문서를 조회
  }
}
```

##### 2. term

1. term 안에는 1개의 조건만 지정할 수 있다. 
2. 따라서 여러 조건 하기 위해서는 term을 여러개 써줘야 한다. 

```json
{
  "query": {
    "bool": {
      "must": [
        {
          "term": {
            "deviceId": 1
	        // "deviceId": [1, 2, 3] // 하나의 필드에서 여러 조건 검색 
          }
        },
      	{ 
          "terms": { // terms 는 여러 조건 검색에 이용
	        "deviceId": [1, 2, 3] // 하나의 필드에서 여러 조건 검색 
          }
        },
        {
          "term": {
            "objectCount": 1
          }
        }
      ]
    }
  }
}
```

##### 3. **bool**

1. 논리적 조합(AND, OR, NOT)을 제공하는 쿼리 구조.
2. 종류
   1. **must**
      1. 모든 조건이 참이어야 함 (AND).
   2. **must_not**
      1. 조건이 참이면 제외 (NOT).
   3. **should**
      1. 하나라도 참이면 반환 (OR).
   4. **filter**
      1. 문서를 필터링하되, 점수에는 영향을 미치지 않음

```json
{
  "query": {
    "bool": {
      "must": [
        { "term": { "field_name": "value" } },
        { "range": { "date_field": { "gte": "2024-10-01", "lte": "2024-10-31" } } }
      ],
      "must_not": [
        { "term": { "status": "error" } }
      ],
      "should": [
        { "term": { "priority": "high" } }
      ],
      "filter": [
        { "term": { "category": "network" } }
      ]
    }
  }
}
```

##### 3-1 exites

1. 해당 필드에 값이 존재하는지 여부 판단

```json
{
  "size": 10,
  "query": {
    "bool": {
      "must": [
        {
          "term": {
            "deviceId": 1
          }
        },
        {
          "exists": {
            "field": "objects"
          }
        },
      ]
    }
  },
}
```

##### 4. **aggs** (집계 aggregations)

1. 집계 조건을 지정하는 부분
2. 종류
   1. **terms**
      1. 필드의 고유 값과 각 값의 빈도를 계산
   2. **date_histogram**
      1. 날짜 필드를 기준으로 시간 간격에 따라 그룹화할때 사용
   3. **avg**, **sum**, **max**, **min**
      1. 필드 값에 대한 평균, 합계, 최대, 최소값을 구할때 사용

```json
{
  "aggs": {
    "status_count": {
      "terms": {
        "field": "status"
      }
    }
  }
}
```

##### 5. 정렬 ( sort )

1. 결과를 특정 필드를 기준으로 정렬
2. 기본적으로 `_score` 필드를 기준으로 정렬된다

```json
{
  "sort": [
    { "@timestamp": { "order": "desc" } }
  ]
}
```

##### 6. 페이지네이션 (from과 size)

1. 검색 결과의 페이지네이션을 위해 `from`과 `size`를 사용하여 검색할 위치와 문서 개수를 지정할 수 있다
2. 즉, 조회해올 문서 개수 정하기 
3. 종류
   1. `from`: 반환할 문서의 시작 위치.
   2. `size`: 반환할 문서 수

```json
{
  "from": 0,
  "size": 10
}
```

##### 7.복합쿼리 

```json
GET index_name/_search
{
  "size": 5,
  "query": {
    "bool": {
      "must": [
        { "term": { "status": "active" } },
        { "range": { "@timestamp": { "gte": "2024-10-01", "lte": "2024-10-31" } } }
      ]
    }
  },
  "aggs": {
    "status_count": {
      "terms": { "field": "status" }
    }
  },
  "sort": [
    { "@timestamp": { "order": "desc" } }
  ]
}
```

### Query 에서의 Score?

1. 검색 결과에서 문서가 얼마나 **관련성**이 높은지를 나타내는 지표이다. 
2. 문서가 쿼리와 더 잘 맞을수록 점수가 높아지며, 기본적으로 검색 결과는 점수가 높은 문서부터 반환된다. 
3. **_score**라는 필드로 나타나며, 이는 쿼리가 입력된 조건에 맞춰 문서가 얼마나 적합한지 평가하는 지표이다. 

## Query 결과값

1. 쿼리의 결과 구조는 복잡하기 때문에 자세히 봐야한다. 
2. 조회 데이터 
   1. 조회된 데이터는 `hits -> hits -> _source` 밑에 들어가 있다. 
3. 구조
   1. `took`: 쿼리가 실행되는데 걸린 시간
   2. `timed_out`: 쿼리가 시간 초과되었는지 여부
      1. false`면 정상 처리, `true`면 시간 초과로 실패했음을 의미
   3. `_shards`: 쿼리가 분산된 샤드에서 어떻게 실행되었는지에 대한 정보
      1. `total`: 총 샤드의 개수
      2. `successful`: 성공적으로 처리된 샤드 개수
      3. `skipped`: 건너뛴 샤드 개수
      4. `failed`: 실패한 샤드 개수
   4. `hits`
      1. `total.value`: 쿼리 조건에 일치하는 총 문서 수
      2. `max_score`: 조회된 문서 중 가장 높은 점수
      3. `_source`: 문서의 실제 데이터가 포함된 부분

```json
{
  "took" : 7,  // 쿼리 실행에 소요된 시간 (밀리초 단위)
  "timed_out" : false,  // 쿼리가 시간 초과되지 않았음을 의미함 (false는 정상 처리, true는 시간 초과)
  "_shards" : {
    "total" : 1,  // 쿼리가 실행된 샤드(데이터 분할 단위)의 총 개수
    "successful" : 1,  // 성공적으로 쿼리가 실행된 샤드의 개수
    "skipped" : 0,  // 쿼리 실행 중 건너뛴 샤드의 개수
    "failed" : 0  // 실패한 샤드의 개수
  },
  "hits" : {
    "total" : {
      "value" : 118,  // 쿼리 조건에 맞는 총 문서(레코드)의 개수
      "relation" : "eq"  // `eq`는 정확히 118개의 결과가 있다는 의미. "gte"는 118개 이상이라는 의미
    },
    "max_score" : 1.0,  // 조회된 문서 중 가장 높은 점수. (쿼리의 적합성에 대한 평가)
    "hits" : [  // 실제 조회된 문서 목록 (조회된 데이터의 세부 정보)
      {
        "_index" : "my_index",  // 문서가 속한 인덱스 이름
        "_id" : "abc123",  // 해당 문서의 고유 ID
        "_score" : 1.0,  // 해당 문서가 쿼리와 얼마나 적합한지에 대한 점수 (높을수록 쿼리와 적합)
        "_source" : {  // 실제 문서의 데이터 내용
          "deviceId" : 1,  // 조회된 문서의 필드 값 (여기서는 deviceId 필드가 1인 문서)
          "@timestamp" : "2024-10-14T07:51:39.558Z",  // 문서의 타임스탬프 (ISO 8601 형식)
          "objectCount" : 1,  // 객체 수를 나타내는 필드
          "objects" : [
            {
              "classType" : "aaa",  // 객체의 타입 (예: fire)
              "x" : 230,  // 객체의 X 좌표
              "y" : 5,  // 객체의 Y 좌표
              "width" : 253,  // 객체의 너비
              "height" : 54  // 객체의 높이
            }
          ]
        }
      }
    ]
  }
}
```

## 비동기 처리 

- 5개의 Task 로 나눠 빠르게 비동기 처리가능 

```json
POST indexA/_delete_by_query?wait_for_completion=false&slices=5
{
  "query": {
    "bool": {
      "must": [
        {
          "term": {
            "verifyId.keyword": "asdf"
          }
        }
      ]
    }
  }
}
```

