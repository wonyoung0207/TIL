# vue의 환경변수 내부 주입(env파일)

---

>[참고 사이트1](https://eunsung-dev.tistory.com/entry/Vuejs-env-%ED%8C%8C%EC%9D%BC%EB%A1%9C-%ED%99%98%EA%B2%BD-%EB%B3%80%EC%88%98-%EA%B4%80%EB%A6%AC%ED%95%98%EA%B8%B0)
>
>[참고 사이트2](https://kang-ji.tistory.com/entry/env-%ED%8C%8C%EC%9D%BC%EA%B3%BC-%ED%99%98%EA%B2%BD%EB%B3%80%EC%88%98-%EC%84%A4%EC%A0%95%EB%B0%A9%EB%B2%95)

## 방법

1. 프로젝트 Root폴더 위치에 .env 파일 생성
   1. 키 = 값 의 형태로 작성한다. (환경변수 파일)
2. vue에서 지원하는 `process.env` 에서 값을 꺼내와 사용 

## 사용예제

1. 정의한 환경 변수를 접근하기 위해서는 **process.env.${이름}**의 형태를 사용
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

