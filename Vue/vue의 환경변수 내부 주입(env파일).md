# vue의 환경변수 내부 주입(env파일)

---

>[참고 사이트1](https://eunsung-dev.tistory.com/entry/Vuejs-env-%ED%8C%8C%EC%9D%BC%EB%A1%9C-%ED%99%98%EA%B2%BD-%EB%B3%80%EC%88%98-%EA%B4%80%EB%A6%AC%ED%95%98%EA%B8%B0)
>
>[참고 사이트2](https://kang-ji.tistory.com/entry/env-%ED%8C%8C%EC%9D%BC%EA%B3%BC-%ED%99%98%EA%B2%BD%EB%B3%80%EC%88%98-%EC%84%A4%EC%A0%95%EB%B0%A9%EB%B2%95)

## 방법

1. 프로젝트 Root폴더 위치에 .env 파일 생성
   1. 키 = 값 의 형태로 작성한다. (환경변수 파일)
2. vue에서 지원하는 `process.env` 에서 값을 꺼내와 사용 

## env 파일 로드

1. 버전
   1. vue-cli 2.X 까지는 .env파일을 애플리케이션 소스코드에서 인식하려면 웹펙의 DefinePugin을 사용해줘야했다.
   2. vue-cli 3.X 부턴 사용자 편리를 위해 자체로 규칙을 제공하는데 **`VUE_APP_`** 접두가사 붙은 변수는 자동 로드된다.
   3. 즉, 거의다 `VUE_APP` 으로 인식해서 사용가능 

2. 경로
   1. 프로젝트 바로 밑에 `.env` 파일 생성 (파일 이름 없이 `.env` 으로하면 자동 인식됨) 
   2. `VUE_APP_(원하는변수명)` 으로 변수 설정 후 사용 

| Vue CLI 버전   | `.env` 자동 인식            | 설명                                             |
| -------------- | --------------------------- | ------------------------------------------------ |
| Vue CLI 3 이상 | ✅ 네 (기본 지원)            | `.env`, `.env.production`, `.env.development` 등 |
| Vue CLI 2      | ❌ 아니요 (직접 적용해야 함) | `dotenv` 직접 불러와서 적용 필요                 |


## 사용예제

1. 정의한 환경 변수를 접근하기 위해서는 **process.env.${원하는변수명}**의 형태를 사용
2. VITE라는 빌드 툴을 사용하고 있으면 접두사를 VUE 대신 VITE로 사용할 수 있음 

```js
// env 파일 (VUE)
VUE_APP_TITLE=My App
VUE_APP_API_URL=https://my-api-url.com

// vue 파일 사용방법
created() {
  console.log(process.env.VUE_APP_TITLE); // 'My App'
  console.log(process.env.VUE_APP_API_URL); // 'https://my-api-url.com'
}
```

```js
// env 파일 (VITE)
VITE_API_URL=https://my-api-url.com

// vue 파일 사용방법
const API_URL = import.meta.env.VITE_API_URL;
```



## CLI 2에서 `.env` 사용 방법

| 항목                            | 설명                                |
| ------------------------------- | ----------------------------------- |
| 자동으로 `.env` 파일 인식 ❌     | 반드시 `dotenv.config()` 필요       |
| Vue 컴포넌트에서 사용하려면?    | `DefinePlugin`으로 명시적으로 주입  |
| `VUE_APP_` prefix는 규칙이 아님 | CLI 2에선 자유롭게 이름 정해도 무관 |

1. `dotenv` 패키지를 설치

   ```bash
   npm install dotenv --save
   ```

2. 루트에 `.env` 파일을 생성

   ```js
   VUE_APP_API_BASE_URL=http://localhost:3000/api
   VUE_APP_MODE=development
   ```

3. 사용하고자 하는 곳에 명시적으로 불러옵니다 (예: `webpack.base.conf.js`, `main.js`, `api.js` 등):

   - 이제 `process.env.VUE_APP_API_BASE_URL` 등으로 접근 가능 

   ```js
   // 예: main.js
   require('dotenv').config();
   
   console.log(process.env.VUE_APP_API_BASE_URL);
   ```
