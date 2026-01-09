# MCP 이용한 자연어로 Opensearch Index 조회 (Opensearch Index 이용 version)

---

>

## 목표 구조

> **자연어 요청 → index catalog 검색(RAG) → 적절한 index 선택 → OpenSearch 실제 쿼리 실행**

1. Opensearch Index 생성 
   - opensearch 에 Index 들의 Description 정리된 Index 생성 
2. 사용자 요청 
   - 사용자 자연어로 MCP Server 로 요청 
3. 자연어 이용 Index 명 찾기 
   - 프롬프트로 가장 먼저 Description Index 을 조회해서 Index 명 찾기 
   - 찾을 때 Opensearch 의 BM25 에 의해 Score 로 상위 1개 인덱스만 리턴 ( Vector DB 같이 점수 계산)
4. opensearch 데이터 조회 
   - 찾아온 Index 명을 이용해 조회 쿼리 생성 후 데이터 조회 

------

## 1. 전체 흐름 

- index catalog도 OpenSearch 인덱스
- MCP 서버는 변경 없음
- Agent가 “두 단계 검색”을 수행

```
사용자 질문 (자연어)
   ↓
Agent (antigravity)
   ↓
[1] index catalog 검색 (RAG)
   ↓
[2] 적절한 index / alias 결정
   ↓
[3] OpenSearch MCP Server
   ↓
실제 데이터 index 조회
```

------

## 2. index catalog 인덱스 설계

##### 인덱스 이름 예시

```json
index_catalog // 해당 Index 에는 여러 Index 의 Description 내용들이 정리되어있음 
```

##### 매핑 설계

```json
PUT /index_catalog
{
  "mappings": {
    "properties": {
      "index_pattern": {
        "type": "keyword"
      },
      "description": {
        "type": "text"
      },
      "domain": {
        "type": "keyword"
      },
      "tags": {
        "type": "keyword"
      },
      "example_queries": {
        "type": "text"
      }
    }
  }
}
```

##### catalog 문서 예시 

- **이 문서 하나가 “index 설명서” 역할**

```json
POST /index_catalog/_doc
{
  "index_pattern": "idx_t.vehicle-rcv-wireless-asn-pvd*",
  "description": "차량 무선 통신 ASN 수신 데이터 및 PVD 이벤트 로그",
  "domain": "vehicle",
  "tags": ["vehicle", "wireless", "asn", "pvd", "telemetry"],
  "example_queries": [
    "차량 무선 수신 데이터에서 에러 비율",
    "ASN PVD 이벤트 최근 10분 요약",
    "차량 통신 이상 로그"
  ]
}
```

------

## 3. index catalog를 RAG로 검색

- agent 가 mcp server이용해 가장 의미가 가까운 Index 를 검색함
- 이때 `index_catalog` 에는 필요한 Index 들에 대한 description 이 정의되어있어야함 

```json
GET /index_catalog/_search
{
  "size": 1,
  "query": {
    "multi_match": {
      "query": "차량 무선 수신 데이터에서 최근 오류",
      "fields": [
        "description",
        "tags",
        "example_queries"
      ]
    }
  }
}
```

- 결과 
  - opensearch 의 BM25 에 의해 점수를 계산하고 가장 높은 Score Index 문서를 1개 반환한다.

```json
{
  "index_pattern": "idx_t.vehicle-rcv-wireless-asn-pvd*"
}
```

------

## 4. 실제 데이터 index 조회

- Agent는 위 결과를 바탕으로 **두 번째 쿼리**를 생성함 

```json
GET /idx_t.vehicle-rcv-wireless-asn-pvd*/_search
{
  "query": {
    "range": {
      "timestamp": {
        "gte": "now-10m"
      }
    }
  }
}
```

------

## 5. MCP 서버 설정

- MCP 설정은 거의 그대로 사용
- index_catalog 반드시 허용

```json
"env": {
  "OPENSEARCH_URL": "http://192.168.105.104:19200",
  "OPENSEARCH_USERNAME": "admin",
  "OPENSEARCH_PASSWORD": "admin",
  "OPENSEARCH_VERIFY_SSL": "false",
  "OPENSEARCH_ALLOWED_INDICES": "idx_t.vehicle-rcv-wireless-asn-pvd*,index_catalog"
}
```

------

## 6. Agent (antigravity) 쪽에서 필요한 로직

##### Agent Prompt 설계

- Agent에게 이렇게 역할을 명확히 줘야 함

> 1. 사용자의 질문에서 “어떤 데이터 영역인지” 판단한다
> 2. 먼저 index_catalog에서 가장 적합한 index_pattern을 찾는다
> 3. 그 index_pattern으로 실제 OpenSearch 쿼리를 실행한다

##### 예시 시스템 프롬프트 

```
You have access to an index catalog.
Always identify the best index by searching the index_catalog first.
Use the returned index_pattern to query actual data indices.
Never guess index names without consulting the catalog.
```

------

## 7. 사용자 UX 요청 

- 사용자 자연어 예시 

> “차량 무선 통신 데이터에서 최근 10분간 오류 요약해줘”

- 내부 동작 순서 
  1. index_catalog 검색
  2. `idx_t.vehicle-rcv-wireless-asn-pvd*` 선택
  3. 실제 데이터 검색
  4. 결과 요약
- **사용자는 index 이름을 절대 몰라도 됨**

------

## 8. 확장 장점 

- 검색에 필요한 인덱스 늘어날 때
  - index_catalog에 문서만 추가
  - Agent 로직 변경 X
  - MCP 서버 변경 X

```json
{
  "index_pattern": "idx_t.vehicle-rcv-wired-can*",
  "description": "차량 유선 CAN 통신 데이터",
  "domain": "vehicle",
  "tags": ["vehicle", "can", "wired"]
}
```

##### 멀티 인덱스 대응

- index_catalog 검색 결과가 여러 개면
  - domain 기준 필터
  - score 기준 상위 N개
  - 사용자에게 재질문도 가능

------

## 9. 주의사항 

- index_catalog 없이 바로 index 추측
- OPENSEARCH_ALLOWED_INDICES에 `*` 허용
- write/delete API 허용

---

## 10. Index 로 Catalog 저장해야 하는 이유 

##### 1. MCP 서버는 “파일 시스템”을 모른다

- OpenSearch MCP 서버는:
  - OpenSearch REST API만 호출 가능
  - 로컬 파일 시스템 접근 X
  - 프로젝트 내부 파일 읽기 X
- 즉 `GET /index_catalog/_search` 는 **OpenSearch에 있는 인덱스만 조회 가능**

```
MCP Server
  ├─ OpenSearch API 
  └─ 로컬 파일 X
```

##### 2. RAG의 핵심은 “검색 가능성”

- RAG는 단순 매핑이 아니라 다음 작업하기 위해 **검색 엔진이 필요**함.
  - 자연어
  - 유사도
  - 부분 일치
  - 의미 기반 선택
- 파일 기반이면 
  - fuzzy search
  - multi_match
  - scoring
  - ranking
  - 전부 직접 구현해야 함 → 비현실적

##### 3. 구조 확장성 (핵심)

- index_catalog를 OpenSearch에 두면:
  - 인덱스 늘어나도 코드 변경 X
  - description 바꿔도 재배포 X
  - 운영자가 catalog 문서만 수정하면 끝
- 운영 친화적

---

## 정리

- **index catalog + RAG 방식은 “자연어 → 의미 → index 선택 → 실제 검색”을 가능하게 하는 가장 안전하고 확장성 있는 MCP 활용 구조다.**