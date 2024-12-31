# js 모듈 시스템

---

>[짐코딩 - 자바스크립트 모듈 시스템 정리](https://gymcoding.notion.site/e9085fcc1538486eb1451ab988199d06)
>
>[js 모듈 시스템 - 짐코딩 유튜브](https://www.youtube.com/watch?v=5NXEXkIrkAk&list=PLlaP-jSd-nK9LiA2n07uBhzOn9wI53xGV&index=10)

## 모듈(Module) 이란?

1. 개발하는 애플리케이션의 규모가 커지면 파일을 여러 개로 분리해야 하는 시점이 옵니다. 
2. 이때 분리된 하나의 파일을 `모듈(module)`이라고 부르는데, 모듈은 대개 클래스 하나 혹은 특정한 목적을 가진 여러개의 함수를 포함하는 라이브러리로 구성되어 있습니다.

##### 모듈 장점

- **유지보수용이** - 기능들이 모듈화가 잘 되어 있는 경우, 의존성을 줄일 수 있기 때문에 기능을 개선이나 수정이 용이합니다.
- **네임스페이스화** - 코드의 양이 많아질수록 전역스코프에 존재하는 변수명이 겹치는 경우가 존재합니다. 이때 모듈로 분리하면 모듈만의 네임스페이스를 갖기 때문에 그 문제를 해결할 수 있습니다.
- **재사용성** - 같은 코드를 반복하지 않고 모듈로 분리시켜서 필요할 때마다 재활용할 수 있다.
- 그리고 이러한 모듈을 필요시에 언제든지 불러올 수 있도록 하는 다양한 방법들이 있습니다. 이렇게 모듈을 불러오는 방법을 `모듈 시스템`이라고 하며, 그 `모듈 시스템`은 다음과 같은 방법들이 있습니다.

##### 모듈 시스템 종류

- `AMD` - 가장 오래된 모듈 시스템 중 하나로 require.js라는 라이브러리를 통해 처음 개발되었습니다.
- **`CommonJS`** - NodeJS 환경을 위해 만들어진 모듈 시스템 입니다.
- `UMD` - AMD와 CommonJS와 같은 다양한 모듈 시스템을 함께 사용하기 위해 만들어졌습니다.
- **`ES Module`** - `ES6(ES2015)`에 도입된 자바스크립트 모듈 시스템 입니다



## ES Module 방식

1. `ES6(ES2015)`에 도입된 자바스크립트 모듈 시스템 입니다. 
2. `<script>` 태그에 `type="module"` 속성을 추가해주면, 이 파일은 모듈로서 동작합니다.

```jsx
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>ES Modules</title>
    <script type="module" src="module/index.js"></script>
  </head>
  <body></body>
</html>
```

모듈을 외부에서 사용할 수 있도록 내보낼 때는 `named export`, `default export`와 같은 키워드를 사용하며, 외부에서 모듈을 불러올 때는 `import`를 사용하여 모듈을 불러올 수 있습니다.

### 내보내기

- `named export` 를 사용하여 함수 또는 변수를 내보낼 수 있습니다.

  ```jsx
  // module/math.js
  export const perfectScore = 100;
  
  export const sum = (num1, num2) => {
    return num1 + num2;
  };
  
  export const avg = function (score1, score2) {
    return (score1 + score2) / 2;
  };
  ```

- `default export`를 사용하여 하나 기본 함수를 내보낼 수 있습니다. **단, 모듈 당 하나만 가능**

  ```jsx
  // module/math.js
  export const perfectScore = 100;
  
  export const sum = (num1, num2) => {
    return num1 + num2;
  };
  
  export const avg = function (score1, score2) {
    return (score1 + score2) / 2;
  };
  
  const subtract = function (num1, num2) {
    return num1 - num2;
  };
  
  export default subtract;
  ```

### 불러오기

1. `import`를 사용하여 모듈을 불러올 수 있습니다.

2. `export`를 `import` 하는 경우

   1. 각각의 변수나 함수를 가져올 수 있습니다.

      ```js
      // index.js
      import { perfectScore, sum, avg } from './math.js';
      console.log('만점: ', perfectScore);
      console.log('합: ', sum(80, 10));
      console.log('평균: ', avg(80, 90));
      ```

   2. `* as 별칭` 을 통하여 가져올 수 있습니다.

      ```js
      // index.js
      import * as math from './math.js';
      console.log('만점: ', math.perfectScore);
      console.log('합: ', math.sum(80, 10));
      console.log('평균: ', math.avg(80, 90));
      ```

   3. `default export`를 `import` 하는 경우

      ```js
      // index.js
      import subtract from './math.js';
      console.log('빼기: ', subtract(100, 45));
      ```

## CommonJS 방식

1. **NodeJS** 환경에서 자바스크립트 모듈을 사용하기 위해 만들어진 모듈 시스템 입니다. 
2. 모듈을 외부에서 사용할 수 있도록 내보낼 때는 **`exports`, `module.exports`**와 같은 키워드를 사용하며, 외부에서 모듈을 불러올 때는 **`require`**를 사용하여 모듈을 불러올 수 있습니다

###### 내보내기

- `exports` 변수의 `속성`으로 내보낼 함수를 설정할 수 있습니다.

  ```jsx
  // commonjs/math.js
  exports.perfectScore = 100;
  
  exports.sum = function (num1, num2) {
    return num1 + num2;
  };
  
  exports.avg = function (score1, score2) {
    return (score1 + score2) / 2;
  };
  ```

- `module.exports`를 사용하여 하나의 객체로 묶어서 내보낼 수 있습니다.

  ```jsx
  // commonjs/math.js
  const math = {};
  math.perfectScore = 100;
  
  math.sum = function (num1, num2) {
    return num1 + num2;
  };
  
  math.avg = function (score1, score2) {
    return (score1 + score2) / 2;
  };
  
  module.exports = math;
  ```

###### 불러오기

- `require` 키워드를 통해 변수에 할당할 수 있습니다.

  ```jsx
  // index.js
  const math = require('./math');
  console.log('만점: ', math.perfectScore);
  console.log('합: ', math.sum(80, 10));
  console.log('평균: ', math.avg(80, 90));
  ```

- 전개구문(spread syntax)을 통하여 불러오기

  ```jsx
  // index.js
  const { sum, avg, perfectScore } = require('./math');
  console.log('만점: ', perfectScore);
  console.log('합: ', sum(80, 10));
  console.log('평균: ', avg(80, 90));
  ```

## ES Module for NodeJS

1. `CommonJS` 모듈 시스템을 채택했던 **NodeJS** 환경에서  `ES Module`을 사용하려면 Babel과 같은 트랜스파일러(transpiler)를 사용했어야 했는데요. **NodeJS 버전 13.2부터 ES모듈 시스템에 대한 정식 지원**이 시작됨에 따라 다른 도구 없이 **NodeJS**에서 손쉽게 `ES Module`을 사용할 수 있게 되었습니다.

### NodeJS 에서 ES Module 사용법

- package.json → type=”module” 선언