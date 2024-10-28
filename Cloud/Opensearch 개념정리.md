# AWS Opensearch 개념정리

---

>[opensearch 내부구조, 성능최적화 for AWS](https://www.slideshare.net/awskorea/t2s2pdf)

## 개념

1. **Amazon**이 개발한 오픈 소스 검색 및 분석 엔진이다. 
   1. 데이터의 **실시간 검색, 모니터링, 분석**을 가능하게 해주는 소프트웨어이다. 
   2. 즉, DB처럼 데이터를 조회할 수있을 뿐만 아니라, 데이터 이용에 있어 모니터링과 분석을 할 수 있다.
2. noSQL 의 성격을 가지고 있어 문서의 형태로 데이터가 저장되는 DB 라고 생각하면 된다. 
   1. RDB와는 다르게 데이터간 관계 정의가 되어있지 않고, Json 형식으로 문서화 되서 저장된다. 

## Elasticsearch와의 관계

1. `Elasticsearch` 가 유료화로 전환됨에 있어 아마존이 오픈소스를 유지하고자 시작된 프로젝트 이다. 
2. 그래서 `OpenSearch`는 `Elasticsearch`와 `Kibana`의 포크(fork) 버전으로 시작되었다. 

## 주요 기능

1. 검색
   1. **구조화된 데이터**와 **비정형 데이터** 모두에 대해 고급 검색 기능을 제공한다. 
   2. RDB가 아닌 NoSQL 형태이다.
   3. **대규모 데이터**에서 특정 정보를 매우 빠르게 검색할 수 있는 능력이 있어 로그 데이터, 애플리케이션 데이터 등을 빠르게 처리할 수 있다. 
2. 실시간 분석 
   1. 로그나 이벤트 데이터를 실시간으로 수집해 분석한다. 
   2. 성능 이슈를 실시간으로 파악할 수 있게 된다. 
3. 확장성
   1. 다양한 **플러그인**을 통해 추가 기능을 쉽게 구현할 수 있다.
4. **OpenSearch Dashboards**
   1. Elasticsearch의 Kibana와 유사해 데이터 시각화를 할 수 있다. 
   2. 대시보드는 Kibana와 매우 유사하며, 데이터를 조회하고 차트, 그래프 등으로 시각화하는 데 사용한다. 

## Node 

1. 노드(Node)는 OpenSearch 클러스터 내에서 독립적으로 데이터를 저장하고 처리하는 서버를 의미한다. 
   1. OpenSearch 클러스터는 여러 노드들로 구성되며, 각 노드는 클러스터 내에서 중요한 역할을 담당한다. 
2. 노드는 **크게 세 가지 역할**을 가질 수 있으며, 이 역할에 따라 **데이터 처리 방식이 달라진다.** 
   1. 노드를 추가하면 클러스터의 처리 능력이 증가하여 성능을 유지하거나 향상시킬 수 있다. 
3. 즉, 데이터가 증가하거나 쿼리 요청이 많아질 경우, **노드를 추가하여 성능을 확장할 수 있다**
4. 역할
   1. 마스터 노드(Master Node): 클러스터의 **관리 및 조정** 역할을 담당
   2. 데이터 노드(Data Node): 실제로 **데이터를 저장**하고, **검색 요청**이나 **인덱싱 작업**을 처리하는 노드
   3. 코디네이팅 노드(Coordinating Node): **클라이언트 요청**을 받아서 처리하는 노드
5. **Shard(샤드)**
   1. 노드가 늘어나면 OpenSearch는 데이터를 **샤드(shard)**라는 단위로 나누어 분산 저장한다. 
   2. 샤드는 인덱스의 부분 집합으로, 클러스터 내에서 여러 노드에 걸쳐 분산 저장되며, 이를 통해 대량의 데이터를 처리할 수 있다.
   3. 즉, 하나의 인덱스를 여러 개의 샤드로 나누어 각 노드에 할당하게 되면, 더 많은 데이터를 처리하면서도 성능을 유지할 수 있게 된다. 

## Index ( 인덱스 )

1. **데이터를 저장하는 기본 단위**이다. 

   1. 데이터는 인덱스명으로 저장되고 관리되어진다. 
   2. 주로 **특정한 데이터 세트를 가리키는 이름**으로 사용되며, 그 데이터를 검색하거나 업데이트할 때 사용한다. 
   3. 즉, 특정 데이터가 어느 Topic 에 쌓일때, 해당 **Topic 명**이 opensearch 의 인덱스가 되어 **Index 명**으로써 데이터가 관리된다. 

2. 호출 예시

   ```bash
   # 메소드 /인덱스명/처리작업
   GET /index_Name/_search
   
   # 인덱스의 매핑 정보를 조회 ( 분석필드인지 비분석필드인지 확인 가능 )
   GET /index_Name/_mapping
   ```

## 분석 필드 vs 비분석 필드

1. 분석필드
   1. 저장된 데이터를 검색하기 쉽게 처리하는 필드
   2. **소문자로 변경되어 text 검색에 조심해야한다.** 
2. 비분석 필드
   1. 전체 값을 그대로 저장하는 필드로, 분석 과정을 거치지 않는다. 
3. 따라서
   1. 정확한 텍스트 검색할때에는 "비분석 필드" 의 값으로 조회해야한다. 

## Opensearch Dashboard 이용방법

<img src="./images/opensearch 이용방법1.png" width="300">

##### 1. 인덱스 정보 보기 

1. 메뉴 -> Opensearch Dashboard-> **Discover** -> **Change index pattern** -> 인덱스 명 입력.

   <img src="./images/opensearch 이용방법2.png" width="300">

   <img src="./images/opensearch 이용방법4.png" width="800">

##### 2. 쿼리 이용 

1. 메뉴 -> Management -> Dev Tools -> 쿼리 조회 

   <img src="./images/opensearch 이용방법3.png" width="800">

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
3.  **_score**라는 필드로 나타나며, 이는 쿼리가 입력된 조건에 맞춰 문서가 얼마나 적합한지 평가하는 지표이다. 

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

