# vue Router Redirection Error

---

> [참고 사이트1](https://marklee1117.tistory.com/55)
>
> [참고 사이트2](https://playground.naragara.com/2345/)

## Redirection Error

### 발생내용

```js
Uncaught (in promise) Error: Redirected when going from "/" to "/userInfo" via a navigation guard.
```

### 발생이유

- router 의 beforeEach 에서 라우터 가드를 이용해 원하는 페이지를 호출했는데 해당 에러가 발생했다. 
  - 정상적으로 next()를 이용해 원하는 페이지로 로딩 되었지만, console에는 에러가 발생하는 상황이다. 
- 쉽게말해, **router.push 혹은 router.replace는 promise를 리턴**하는데, **해당 메소드에 callback함수가 제공되지 않을 때 해당 에러가 발생**한다.

### 발생예시

- 예를들어, a,b,c 페이지 경로가 있다고 하자. 
- 사용자는 a 경로에 있고, b 경로를 router.push(B)를 이용해 호출하였다. 
- 이동전 라우터에서 제공하는 router.beforEach() 의 특정 조건문을 이용해 B로 보내려던 명령을 next(C) 를 통해 C 페이지로 이동시키고자 한다.  
  - 이때, rotuer는 C 경로로 이동하는 것을 리디렉션으로 판단하게 되고, C 경로로 router.push(C) 를 진행한다. 
- 이 결과, 리디렉션된 경로 말고 그 전 경로(B로 가라고 했던 것)에 대한 결과값은 실패한것으로 간주하게 된다. 
- 따라서 이전 push() 결과 실패로 인한 콜백함수가 제공되지 않아 동작은 제대로 하지만 에러를 발생시키게 된다. 

### 해결방법

1. router-link 사용

   - router-link는 **noop callback을 기본적으로 제공**하기 때문에, 위와 같은 현상은 router-link를 사용할 때에는 일어나지 않는다. 
     (noop function: 아무것도 하지 않는 함수를 의미한다.)

2. 개별 콜백함수 제공 

   - 개별로 호출할때 error 발생 처리 문구를 설정해 콜백함수를 만들어 잡을 수 있다. 
   - router.push().catch(err => { })

3. Global 로 push 함수에 콜백함수 제공 

   - Router 객체에 접근해 calback을 처리한다. 

     ```js
     import Router from 'vue-router'
     
     const originalPush = Router.prototype.push
     Router.prototype.push = function push(location, onResolve, onReject) {
       if (onResolve || onReject) {
           return originalPush.call(this, location, onResolve, onReject)
       }
       return originalPush.call(this, location).catch(err => err)
     }
     
     
     const originalPush = router.push;
     router.push = function push(location) {
       return originalPush.call(this, location).catch(err => {
         if (err.name !== 'NavigationDuplicated') throw err;
       });
     }
     ```

     

