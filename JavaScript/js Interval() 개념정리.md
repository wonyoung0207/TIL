# Interval() 개념정리 

---

>

## setInterval()

### 정의 

1. 특정한 시간 간격마다 지정된 코드를 반복 실행하는 기능을 제공한다. 
   1. 주기적으로 작업을 수행해야 하는 경우에 유용하다. 

### 사용방법

1. `setInterval` 함수는 두 개의 인수를 받는다. 
   1. 실행할 함수 또는 코드 문자열
   2. 실행할 간격(밀리초 단위)

```js
// 매 1초마다 콘솔에 메시지를 출력하는 인터벌 설정
const intervalId = setInterval(() => {
  console.log('1초마다 실행됩니다.');
}, 1000);

// 5초 후에 인터벌 중지
setTimeout(() => {
  clearInterval(intervalId);
  console.log('인터벌이 중지되었습니다.');
}, 5000);
```

### 관련 함수 

1. `setInterval(callback, delay, ...args)`

   1. `callback`: 실행할 함수.
   2. `delay`: 실행 간격(밀리초).
   3. `...args`: 콜백 함수에 전달할 추가 인수.

2. `clearInterval(intervalId)`

   1. `intervalId`: 중지할 인터벌의 ID(`setInterval`로 반환된 ID).

   ```JS
   let count = 0;
   
   const intervalId = setInterval(() => {
     count++;
     console.log(`현재 카운트: ${count}`);
   }, 1000);
   
   // 10초 뒤 intervalId 삭제 
   setTimeout(() => {
     clearInterval(intervalId);
     console.log('카운트 중지');
   }, 10000);
   ```

   