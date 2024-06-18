# vue의 $nextTick 개념정리

---

>[참고 사이트1](https://velog.io/@ganu/Vue-nextTick-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0)
>
>[참고 사이트2](https://www.w3schools.com/vue/ref_metNextTick.php)
>
>[참고 사이트3](https://v2.vuejs.org/v2/guide/reactivity#Async-Update-Queue)

## $nextTick

### 정의 

- vue 동작
  - Vue에서 데이터를 수정하게 되면 DOM에 바로 업데이트 되는 것이 아니라 **다음 이벤트 루프가 시작 될때까지 버퍼에 저장**된다. 
  - 현재 이벤트 루프가 끝나고 다음 이벤트 루프가 시작되면 버퍼에 저장되었던 변경사항을 꺼내 DOM에 반영한다. 
  - 즉, **"데이터를 변경해도 바로 DOM에 반영이 되지 않는다"** 
- $nextTick
  - `$nextTick`은 데이터 변경 후 DOM에 까지 업데이트가 완료된 후 인자로 전달받은 콜백함수를 실행한다. 

### 공부 하게된 이유 

1. vue의 메소드 실행 시 바인딩을 통해  태그가 생성되는 부분이 있었다. 
   1. 이때 메소드 실행 중에 바인딩 통해 생성된 태그의 정보를 가져와야 했다. 
   2. 태그는 생성이 됐는데, 메소드 실행 시점에서는 태그의 정보가 null 인것을 확인했다. 
   3. 그래서 찾아보다 보니 Dom 이 업데이트 되기전에 소스 코드가 모두 실행되는 것을 발견했고, nextTick 을 사용하게 됐다. 
2. 즉, **데이터를 변경해도 바로 DOM에 반영이 되지 않는 문제가 발생** 해서 공부하게 됐다. 

### JavaScript 의 이벤트 루프 동작 방법 

1. 이벤트 루프란? 

   1. **콜백 큐**(Callback Queue)에 있는 콜백을 꺼내어 실행하는 로직

2. 일단 문제를 파악하려면 javascript의 **이벤트 루프** 동작 방식에 대해 알아야 했다. 

   1. JavaScript는 기본적으로 **동기** 방식으로 진행되며, setTimeout 같은 콜백 함수는 **콜백 큐**(Callback Queue)에 순차적으로 들어가  **비동기**처리 된다. 
   2. 이때, 콜백 큐는 현재 시점에서 판단하지 않고 해당 로직이 끝나고 실행된다. 

3. 예제

   ```js
   console.log("첫번째");
   
   setTimeout(function() {
     console.log("두번째");
   }, 0);
   
   console.log("세번째");
   
   // 결과값 :::  
   // 첫번째 -> 세번째 -> 두번째 
   ```

   1. `setTimeout` 코드가 실행될때 `setTimeout`이 비동기 함수임을 안 자바스크립트는 지연시간과 관계없이 일단 **콜백을 어딘가에 등록만 해둔다**. (몇 ms 뒤에 실행하는지는 현재 시점에서 판단하지 않는다.) 
   2. 이렇게 등록이 완료되면, 3번 코드가 실행되고 3번 코드는 1번 코드와 마찬가지로 동기적으로 실행되어 `3번째`를 출력한다. 
   3. 여기서 중요한점은 두번째, 세번째 텍스트의 출력이다. 
      1. `setTimeout` 에 분명 0초 뒤에 실행하라고 했음에도, `세번째`가 출력된 이후에 `두번째`가 출력되는것을 확인할 수 있다. 
   4. 즉, 모든 로직이 실행되고 나서 **콜백 큐** 의 로직들이 순차적으로 실행된다. 

### $nextTick 사용법

1. Dom 의 데이터를 변경하는 로직을 실행한다. 
2. 데이터의 변경 로직 안에서 $nextTick() 을 사용해 Dom이 업데이트 된 후 데이터를 처리한다. 

```js
this.$nextTick(function() { 
    // DOM에 변경사항 모두 반영 후 실행
    }); 
}

async foo() {
    await this.$nextTick();
    // DOM에 변경사항 모두 반영 후 실행
}

updateMsg() {
    this.message = '"Hello! This is a new message."';
    this.results.push(this.$refs.pEl.textContent);
    this.$nextTick(() => {
      this.results.push(this.$refs.pEl.textContent + ' (using $nextTick())');
    });
}
```



