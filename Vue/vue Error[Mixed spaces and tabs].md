# error: Mixed spaces and tabs (no-mixed-spaces-and-tabs)

---

>[참고 사이트1](https://duriduriduri.tistory.com/5)
>
>

### 현상 

error: Mixed spaces and tabs 에러 발생

### 원인 

- 들여쓰기에 공백과 탭을 동시에 사용하면 뜸

### 해결 

-   package.json 편집

-   "eslintConfig" - "rules" 에 아래 소스 추가

  ```js
  "rules": { "no-mixed-spaces-and-tabs": 0, // disable rule }
  ```

  