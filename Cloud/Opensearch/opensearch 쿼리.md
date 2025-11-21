# Opensearch 쿼리 

---

>

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

