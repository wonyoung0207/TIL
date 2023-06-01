# 쿼리 스트링 Stringgify

---

## 쿼리 스트링 

### 정의

- `qs.stringify(params)`는 `qs` 라이브러리의 `stringify()` 메서드를 사용하여 객체를 쿼리스트링(query string) 형식으로 직렬화(serialize)하는 작업을 수행하는 것을 의미한다. 
  - 이 때, 객체의 각 속성은 쿼리스트링 형식인 `key=value`의 형태로 변환된다. 

### 사용법

```vue
const params = { name: "John", age: 25 };
const queryString = qs.stringify(params);
console.log(queryString); // "name=John&age=25"
```

-  `qs.stringify()`를 사용하여 `{ name: "John", age: 25 }` 객체가 `name=John&age=25`라는 문자열로 변환