# LLM 생성 모델 EmbeddingModel 과 ChatModel 비교

---

>

##### 정리

> **ChatModel은 “말을 만드는 모델”
>  EmbeddingModel은 “의미를 숫자로 바꾸는 모델”**

------

### 목적 

| 구분 | ChatModel        | EmbeddingModel     |
| ---- | ---------------- | ------------------ |
| 목적 | 텍스트 생성·추론 | **의미 벡터 생성** |
| 역할 | 답변, 요약, 판단 | 검색, 비교, 분류   |

------

### 입력 / 출력

| 구분 | ChatModel    | EmbeddingModel    |
| ---- | ------------ | ----------------- |
| 입력 | 텍스트(대화) | 텍스트            |
| 출력 | 텍스트       | 숫자 배열(Vector) |

------

### 사용 시점

| 구분      | ChatModel          | EmbeddingModel      |
| --------- | ------------------ | ------------------- |
| 언제 쓰나 | “답변이 필요할 때” | “비슷한 걸 찾을 때” |

------

### 비용 / 성능

| 구분 | ChatModel       | EmbeddingModel |
| ---- | --------------- | -------------- |
| 비용 | 비쌈            | 저렴           |
| 속도 | 상대적으로 느림 | 빠름           |

------

### 대표 사용 예

| ChatModel      | EmbeddingModel |
| -------------- | -------------- |
| 챗봇           | 문서 검색      |
| 요약           | RAG            |
| 질의 판단      | 추천           |
| Tool 호출 판단 | 중복 제거      |

------

### Spring AI에서의 위치

| 구분       | ChatModel   | EmbeddingModel   |
| ---------- | ----------- | ---------------- |
| 인터페이스 | `ChatModel` | `EmbeddingModel` |
| 연계       | Tool / MCP  | VectorStore      |

```
EmbeddingModel → (검색)
ChatModel → (설명/답변)
```

------

## 정리

- **ChatModel** = 사고하고 말한다
- **EmbeddingModel** = 의미를 숫자로 바꾼다
- **RAG에서는 둘 다 필요**