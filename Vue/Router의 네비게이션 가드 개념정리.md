# Router의 네비게이션 가드 개념정리 

---

>[인프런강의 - 네비게이션가드 정리 페이지](https://joshua1988.github.io/web-development/vuejs/vue-router-navigation-guards/)

## 네비게이션 가드  (navigation guard)

### 정의

- 뷰 라우터로 특정 URL에 접근할 때 해당 **URL의 접근을 막는 방법**을 말한다. 
  - 예를 들어, 사용자의 인증 정보가 없으면 특정 페이지에 접근하지 못하게 할 때 사용하는 기술이다.

### 종류

1. 애플리케이션 전역에서 동작하는 **전역 가드**
2. 특정 URL에서만 동작하는 **라우터 가드**
3. 라우터 컴포넌트 안에 정의하는 **컴포넌트 가드**

### 1. 전역가드

- 전역가드를 설정하면 모든 라우팅이 대기 상태가 된다. 
  - url이 변경되면 화면이 전환되어야 하는데 전역가드로 인해 대기하고 전환되지 않는다. 
- 따라서 화면전환이 정상적으로 이루어지기 위해서는 next() 라는 함수를 호출해줘야 한다. 

1. beforEach( function() { } )

   - **라우터의 이동이 일어나기 전** 실행되는 함수이다. 

     - 라우터로의 **이동을 거부**하도록 할 때 많이 사용한다. 
     - 예를들어, 사용자 권한에 따른 접근을 막을 수 있다. 

     ```js
     router.beforEach(function(to, from , next ) { // 라우터 이용한 페이지 이동이 일어났을 경우 무조건 실행 
         // to : 호출 라우터 
         // from : 이전 라우터 
         // necx : 다음 실행할 라우터 
         
         next(); // 해당 메소드로 이동의 승인을 결정할 수 있다. 
     })
     
     
     var router = new VueRouter();
     router.beforeEach(function (to, from, next) {
       // to : 이동할 url
       // from : 현재 url
       // next : to에서 지정한 url로 이동하기 위해 꼭 호출해야 하는 함수
         if( a > 10) {
             console.log('화면전환이 가드에의해 막혔습니다.')
         }else{
             next();// 해당 함수 호출시 가드 풀리고 정상적으로 화면 전환 이루어짐 
         }
     });
     ```

2. afterEach( function(to, from ) { } )

   - **라우터의 이동이 일어난 후** 실행되는 함수이다. 
   - beforEach () 와 다른점은 **next()** 가 없다는 점이다. 
     - 왜냐하면 라우터의 이동이 일어난 후 실행되기 때문에 next() 가 불필요하다. 
   - **서버에 분석 데이터**를 보내는데에 유용하다. 

### 2. 라우터 가드

```js
var router = new VueRouter({
  routes: [
    {
      path: '/login',
      component: Login,
      beforeEnter: function(to, from, next) {
        // 인증 값 검증 로직 추가
      }
    }
  ]
})
```

- 라우터에 설정되어있는 URL 객체 안에 옵션으로 beforeEnter 라는 것을 이용한다. 
  - beforeEnter 함수 안의 매개변수는 전역가드와 사용법이 같다. 

### 3.  컴포넌트 가드 

```js
const Login = {
  template: '<p>Login Component</p>',
  beforeRouteEnter (to, from, next) {
    // Login 컴포넌트가 화면에 표시되기 전에 수행될 로직
    // Login 컴포넌트는 아직 생성되지 않은 시점
  },
  beforeRouteUpdate (to, from, next) {
    // 화면에 표시된 컴포넌트가 변경될 때 수행될 로직
    // `this`로 Login 컴포넌트를 접근할 수 있음
  },
  beforeRouteLeave (to, from, next) {
    // Login 컴포넌트를 화면에 표시한 url 값이 변경되기 직전의 로직
    // `this`로 Login 컴포넌트를 접근할 수 있음
  }
}
```

- 컴포넌트 안에서 설정할 수 있는 가드이다. 
- 시점을 기준으로 3가지 가드를 할 수 있다 .
  1. beforeRouteEnter
     - 컴포넌트가 화면에 표시되기 전에 가드하기  
  2. beforeRouteUpdate
     - 화면에 표시된 컴포넌트가 변경될 때 가드 
  3. beforeRouteLeave
     - 화면에 표시한 url 값이 변경되기 직전에 가드