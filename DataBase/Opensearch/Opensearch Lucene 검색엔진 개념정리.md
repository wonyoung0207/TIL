

# Lucene 검색엔진 연관성

---

>

## 연관성 정리 

- **OpenSearch는 내부 검색 엔진으로 Apache Lucene을 사용한다.**
- 즉, OpenSearch는 Lucene 기반(search engine)이다.
  - Lucene은 검색과 점수 계산을 담당하는 단일 노드 엔진이고, OpenSearch는 이를 shard 단위로 묶어 분산·운영하는 플랫폼이다.
- **OpenSearch 검색은 Lucene이 수행**

---

## Lucene 이란? 

- Lucene은 Apache Software Foundation이 개발한 오픈소스 풀텍스트 검색 엔진 라이브 러리이다. 
  - 인덱싱 속도가 매우 빠름
  - 용량 대비 효율적인 색인 구조를 가지고 있음
  - 고성능 검색 쿼리를 지원
- 즉, OpenSearch는 “분산 시스템 + API + 클러스터링 기능”을 제공하는 상위 레이어이고, **실제로 검색과 인덱싱을 수행하는 엔진은 Lucene이다**

---

## 관계 

- Lucene  ← 검색 엔진 코어
- OpenSearch ← Lucene을 감싼 분산 검색 플랫폼

##### OpenSearch

- Lucene 여러 개를 묶어 분산 처리,장애 대응, 운영 자동화
- OpenSearch는 Lucene을 **운영 가능하게 만든 분산 시스템**

##### Lucene

- **단일 노드 검색 라이브러리** 이다. 
  - 단일 JVM
  - 단일 머신
  - 라이브러리
- 대규모 로그/검색에는 부족
  - 어떻게 빠르게 찾을 것인가를 담당한다. 

---

## 구조 

- Shard 1개 = Lucene Index 1개
- `애플리케이션 → REST API → OpenSearch → Lucene → 실제 데이터 검색/저 장`
  1. 문서(JSON)가 들어옴
  2. 필드별 분석기(Analyzer)가 적용되어 토큰(Token)으로 분리
  3. Lucene이 각 토큰을 역색인(inverted index) 구조로 저장
  4. 검색 시 이 인덱스를 기반으로 빠르게 매칭

```json
[ Client ]
    ↓
[ OpenSearch ]
    ↓
[ Shard ]
    ↓
[ Lucene Index ]
    ↓
[ Segment / Inverted Index ]


OpenSearch Index
 └─ Shard
     └─ Lucene Index
         ├─ Segment 1
         ├─ Segment 2
         └─ Segment 3
```

---

## Update 비용 비싼 이유 

- Lucene은 **Mutable Storage가 아님**
- update →
  1. 기존 doc delete 표시
  2. 새 doc add
- **Reindex 비용 발생** 으로 데이터 수정에 많은 성능 비용이 발생한다.

---

## 특징

| 관점     | Lucene                          |
| -------- | ------------------------------- |
| 저장     | Append-only (읽기만 됨)         |
| 수정     | Delete + Add ( 비용 많이 발생 ) |
| 트랜잭션 | 없음                            |
| 정합성   | Eventually Consistent           |
| JOIN     | 불가                            |
| 스키마   | Mapping 기반                    |