# Axios의 Config 설정

---

>

## `axios config`란 무엇인가?

- **Axios의 config 객체**는 REST API 요청 시 사용할 수 있는 **모든 설정 값**을 담는 구성 객체이다.
  - 이 설정은 HTTP 요청의 다음과 같은 부분과 직접 연결됨

| Axios config 항목 | REST API와의 연관성                                          |
| ----------------- | ------------------------------------------------------------ |
| `baseURL`         | 요청 주소의 기본 경로                                        |
| `url`             | API 엔드포인트 (ex: `/users`)                                |
| `method`          | HTTP 메서드 (`GET`, `POST`, `DELETE` 등)                     |
| `params`          | URL 쿼리스트링 (`?key=value`)                                |
| `data`            | 요청 본문 (JSON body 등, `POST`, `PUT`, `PATCH`, `DELETE`에서 사용 가능) |
| `headers`         | 요청 헤더 (`Authorization`, `Content-Type`, 등)              |
| `timeout`         | 요청 제한 시간 (ms 단위)                                     |
| `withCredentials` | 쿠키 전송 여부                                               |

## 메서드별 config 위치 

```js
// GET
axios.get('/users', {
  params: { name: 'Alice' },
  headers: { 'X-API-Key': '123abc' },
  timeout: 3000,
  withCredentials: true
});

// Post
axios.post(url, data, {
  params: {  name: 'Alice' },       // 쿼리스트링 (URL에 붙음)
  headers: { ... },      // 요청 헤더
  timeout: 5000,         // 요청 타임아웃
  withCredentials: true, // 쿠키 포함 여부 등
});

// ❌ `name`은 config 속성이 아님 → 무시됨
axios.get('/users', { name: 'Tom' }); 
```

| 메서드   | 사용 예                         | 설명                                      |
| -------- | ------------------------------- | ----------------------------------------- |
| `get`    | `axios.get(url, config)`        | config만                                  |
| `delete` | `axios.delete(url, config)`     | config만 ← ❗ `data`도 여기 안에 넣어야 함 |
| `post`   | `axios.post(url, data, config)` | 데이터 + config                           |
| `put`    | `axios.put(url, data, config)`  | 데이터 + config                           |

| 비교 항목         | `params`                             | `data`                   |
| ----------------- | ------------------------------------ | ------------------------ |
| HTTP 위치         | URL                                  | Request Body             |
| 사용 메서드       | 모든 메서드 (특히 GET)               | POST, PUT, PATCH, DELETE |
| 주요 용도         | 필터/검색 기준                       | 실제 데이터 등록/수정    |
| 서버 측 추출 위치 | `req.query` (Express), `$_GET` (PHP) | `req.body`, `$_POST` 등  |

## 기본 config 지정방법

- `axios.create({...})` 통해서 설정
  - 이후 모든 요청에 이 값들이 **기본적으로 적용**됨
  - REST API를 호출할 때마다 중복 설정 없이 통일된 환경 유지 가능

```js
const apiClient = axios.create({
  baseURL: '/api',
  timeout: 5000,
  headers: {
    common: { Authorization: 'Bearer token' },
  },
});
```

## 요청 시 `config` 설정

- api call 시 파라미터의 config 정보를 포함하면 기본 설정을 덮어쓰기 가능

```js
apiClient.get('/users', {
  params: { role: 'admin' }, // → /users?role=admin
  timeout: 2000,             // → 이 요청만 2초 타임아웃
});
```

## `DELETE` 요청에 `{}`를 추가하는 이유

```js
// 안전하지 않은 방식 → 일부 axios 버전 또는 interceptor에서 오류 발생 가능
axios.delete('/schedules/123');

// 안전한 방식 → 기본 config 객체를 명시적으로 전달
axios.delete('/schedules/123', {});
```

##### 이유:

- `axios.delete()`는 두 번째 인자가 **config 객체**이며, axios 1.x부터는 이 인자가 **무조건 “object”여야 함** (이거 찾는데 개고생함...)
- `undefined`나 `null`이 들어가면 다음 에러 발생
- 요청 interceptor, 응답 interceptor, `data` 병합 등 내부에서 `config.xxx`를 참조할 때

```js
AxiosError: options must be an object
```

##  정리

| 항목                           | 설명                                                     |
| ------------------------------ | -------------------------------------------------------- |
| `axios config`                 | REST API의 headers, params, body, timeout 등을 담는 객체 |
| `axios.create(config)`         | 인스턴스 기본값 설정용                                   |
| `axios.method(url, config)`    | 개별 요청에 대한 설정 전달                               |
| `delete(url, {})`              | 빈 config라도 명시해야 axios 1.x에서 오류 방지됨         |
| `config.data`, `config.params` | REST API의 query string, JSON body 역할을 함             |