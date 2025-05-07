# Axios의 paramSerializer

---

>

## 학습 개기 

- axios 버전을 `0.21.x -> 1.1.3` 으로 버전업 했다. 
  - 그런데 `options must be an object` 에러가 발생 했다. (api cancle 하기 위해)
- 이유를 여러군데서 찾다가 axios 커스텀 인스턴스 생성시 옵션 사용 방법이 달라졌다는걸 알게 되었다. 
  - 즉, 파라미터의 **유효성 검사** 문제였다.(하루날림@@#@#$@%#)

##### 기존 코드

- paramsSerializer 옵션이 무시되서 "params"가 query string으로 제대로 직렬화되지 않거나, 기본 방식으로 처리되며 오류나 오작동이 발생함
- `axios 1.x` 버전부터 내부적으로 config 를 검사하는데, 여기서 걸림 
- `axios 1.x` 에서는 `instance.defaults.paramsSerializer`에 형식이 잘못된 값을 설정하면 아예 `API 호출 자체가 막히고`, 오류가 throw된다.

```js
// node_modules 라이브러리의 axios
import axios from "axios";

function getAxios() {
  var baseUrl = getBaseUrl();
  let instance = axios.create({
    baseURL: baseUrl,
    headers: {
      get: { "Cache-Control": "no-cache", Pragma: "no-cache" },
      post: { "Content-Type": "application/json;charset=utf-8" },
      patch: { "Content-Type": "application/json;charset=utf-8" },
      put: { "Content-Type": "application/json;charset=utf-8" },
    },
    withCredentials: true,
  });

  // 다른점 (timeout, paramsSerializer 옵션이 따로 빠져있음)
  instance.defaults.timeout = 60000;
  instance.defaults.paramsSerializer = (params) => {
     return qs.stringify(params);
   };
}
```

##### 변경 코드

- `axios.create` 시 옵션으로 `paramsSerializer` 를 포함. 
- `headers` 옵션을 common 으로 통일 

```js
// node_modules 라이브러리의 axios
import axios from "axios";

function getAxios() {
  var baseUrl = getBaseUrl();
  let instance = axios.create({
    baseURL: baseUrl,
    headers: {
      common: {
        "Cache-Control": "no-cache",
        Pragma: "no-cache",
        "Content-Type": "application/json;charset=utf-8"
      },
    },
    withCredentials: true,
    // 변경점 
    timeout: 60000,
    paramsSerializer: {
      serialize: (params) => qs.stringify(params),
    },
  });
}
```

## paramsSerializer 예제

##### 기본 동작 (paramsSerializer 없음)

```js
axios.get("/search", {
  params: {
    category: "book",
    tags: ["js", "vue"],
    page: 2
  }
});
```

```js
// Axios는 기본적으로 이렇게 직렬화한다. 
/search?category=book&tags[]=js&tags[]=vue&page=2
```

**문제**:

- `tags[]`처럼 배열 처리하는 방식은 백엔드가 기대하는 포맷과 다를 수 있음.
- Spring이나 PHP 기반 서버는 이를 제대로 파싱하지 못할 수도 있음.

##### 이럴 때 `paramsSerializer` 사용

```js
import qs from "qs";

const axiosInstance = axios.create({
  paramsSerializer: {
    serialize: (params) =>
      qs.stringify(params, { arrayFormat: "repeat" }), // → 중요
  },
});
```

```js
// 이제 위와 같은 요청은 이렇게 바뀐다. 
/search?category=book&tags=js&tags=vue&page=2
```

> `"repeat"` 형식: 배열 요소를 반복하는 방식
>  → `tags=js&tags=vue` ← 이걸 대부분 서버가 잘 인식함

## 다양한 포맷 옵션 (`qs` 기준)

```js
qs.stringify({ tags: ["js", "vue"] }, { arrayFormat: "repeat" });
// → tags=js&tags=vue

qs.stringify({ tags: ["js", "vue"] }, { arrayFormat: "brackets" });
// → tags[]=js&tags[]=vue

qs.stringify({ tags: ["js", "vue"] }, { arrayFormat: "comma" });
// → tags=js,vue
```