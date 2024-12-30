# defer와 async 차이점

---

>

## `defer`와 `async`의 차이점

1. **`defer`**와 **`async`**는 HTML에서 `<script>` 태그에 사용되는 속성으로, **JavaScript 파일의 로딩 및 실행 시점**을 제어한다. 
2. 두 속성 모두 **비동기적**으로 스크립트를 로드하지만, **실행 순서와 타이밍**에서 차이가 있다. 

## 차이점 정리

| 속성          | **defer**                                           | **async**                                               |
| ------------- | --------------------------------------------------- | ------------------------------------------------------- |
| **로딩 방식** | HTML을 **파싱하면서 동시에** 스크립트 로드          | HTML을 **파싱하면서 동시에** 스크립트 로드              |
| **실행 시점** | **HTML 파싱 완료 후** 실행 (순서 보장됨)            | **로드 완료 즉시 실행** (순서 보장 안 됨)               |
| **순서 보장** | 여러 개의 `defer` 스크립트는 **작성 순서대로 실행** | 여러 개의 `async` 스크립트는 **완료되는 순서대로 실행** |
| **사용 목적** | **의존 관계가 있는 코드**나 DOM 조작 코드에 적합    | **독립적 코드(예: 분석, 광고 스크립트)**에 적합         |

## 코드 예시

##### (1) `defer` 예제

- `script1.js`와 `script2.js`는 **HTML 파싱과 동시에 비동기적으로 로드**된다. 
- HTML 파싱이 **완료된 후** 순서대로 실행
- DOM이 준비된 이후 실행되므로, DOM 조작 코드가 있어도 안전하다. 

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Defer Example</title>
  <script defer src="script1.js"></script>
  <script defer src="script2.js"></script>
</head>
<body>
  <h1>Hello, World!</h1>
</body>
</html>
```

```
HTML 파싱 완료
script1 실행!
script2 실행!
```

##### (2) `async` 예제

- `script1.js`와 `script2.js`는 **HTML 파싱과 동시에 비동기적으로 로드**된다. 
- 로드가 완료된 스크립트부터 **즉시 실행**되므로 **순서 보장이 안 됨**.
- DOM이 아직 준비되지 않았을 수도 있으므로, DOM 조작 코드가 있을 경우 오류가 발생할 수 있다.

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Async Example</title>
  <script async src="script1.js"></script>
  <script async src="script2.js"></script>
</head>
<body>
  <h1>Hello, World!</h1>
</body>
</html>
```

```
script2 실행!  <-- 네트워크 상태에 따라 순서 변경 가능
script1 실행!
HTML 파싱 완료
```