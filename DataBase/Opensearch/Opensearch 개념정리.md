# Opensearch 개념정리

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

## Lucene 엔진과의 관계

- Lucene 검색엔진을 사용하여 성능을 높였다. 
- OpenSearch의 진짜 강점은 Lucene이 제공하는 고성능 검색 + OpenSearch가 제공 하는 분산 아키텍처가 결합된 결과

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

