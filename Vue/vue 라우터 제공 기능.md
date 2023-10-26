# Vue 라우터 제공 기능

---

>[Router method 문서](https://developer.mozilla.org/en-US/docs/Web/API/History)

## 라우터 기능

### 1. 동적 라우터

- URL 주소 뒤에 다른 문자가 오더라도 하나의 컴포넌트를 호출 하고싶을때 사용한다. 

- 라우터 path에 작성하는 동적 세그먼트를 `param`이라고 하며, 파라미터는 콜론`:`으로 시작한다.

  - 동적 URL 선언 예시 

  ```js
  // main.js
  const router = ({
      history : createWebHistory(), // 라우터 이용시 이전페이지 및 이동경로를 알 수 있다. 
      routers : [// 표시할 페이지를 인자값으로 가진다. 
  		{ path : '/teams' , name : 'TeamPage', component : TeamList }, 
  		{ path : '/users' , name : 'UserPage',component : UserList }, 
  		{ path : '/tems/:teamId' , name : 'TeamDetails',component : TeamMember  }, // 동적 URL을 통해 TeamMember 컴포넌트를 호출한다. 	
      ],  
  })
  ```

  -  동적 URL 사용 예시 

  ```js
  // TeamMemver.vue 
  ...
  created(){
      const teamId = this.$router.params.teamId; // router에 (:) 로 지정된 param 중에 teamId 부분의 URL 값을 teamId 로 인식한다. -> 즉, URL 로 넘어온 /teams/t1 중 t1 이 변수에 저장된다. 
      const selectedTeam = this.teams.find(team => team.id === teamId ) // teams 배열에서 team 정보 찾기 
      const members = selectedTeam.member;
      for(const member of members){
          const selectedUser = this.users.find(user => user.id === member);
          selectedMembers.push(selectedUser);
      }
  }
  ```

### 2. 리디렉션 라우터 

- **재호출**을 뜻하는 것으로, 지정 URL 로 요청이 들어왔을 때, redirect 에 **설정한 URL로 리디렉션 하기 위해 사용**.

  - 예시로 , 여러 URL을 원하는 URL 페이지로 재로드 시킬 수 있다. 

  ```js
  // main.js
  const router = ({
      routers : [
  		{ path : '/' , redirect : 'teams' },  // '/' 로 요청이 들어왔을 때, /teams URL로 리디렉션해 컴포넌트를 표시한다. 
  		{ path : '/teams' , name : 'TeamPage', component : TeamList }, 
  		{ path : '/users' , name : 'UserPage',component : UserList }, 
  		{ path : '/tems/:teamId' , name : 'TeamDetails',component : TeamMember  }, 
      ],  
  })
  ```

### 3. Catch All 라우터 

- 사용자가 **제공하지 않는 URL로 접근할 경우**를 대비해 설정해놓는 라우터이다. 

- 기본 제공 경로에 해당하지 않는다면 모두 다 catch all 라우터에서 받아 처리한다. 

  - 이때 중요한것은 **선언 순서** 이다. -> **catch All 라우터는 가장 마지막**에 와야한다. 
  - **`(.*)`** : 정규 표현식으로, 모든 라우터를 뜻한다. 

  ```js
  // main.js
  const router = ({
      routers : [
  		{ path : '/' , redirect : 'teams' }, 
  		{ path : '/teams' , name : 'TeamPage', component : TeamList }, 
  		{ path : '/users' , name : 'UserPage',component : UserList }, 
  		{ path : '/tems/:teamId' , name : 'TeamDetails',component : TeamMember  }, 
  		{ path : '/:notFound(.*)' , component : NotFound  }, // Catch All 라우터로, 가장 마지막에 선언되야한다. 
      ],  
  })
  ```

### 4. 중첩 라우터 

- 같은 url을 중첩으로 표기할 수 있는 기능으로, router에서 URL의 중첩 사용을 줄여준다. 

  - 다만 root 로 설정된 Router 안에 children 라우터가 표시되는 방식이다. 
  - **children 속성**을 이용한다. 
  - 예를들어, router의 Path로 '/team ' 과 '/team/teamId' 가 있다면, 중복되는 '/team' 를 줄일 수 있다. 

  ```js
  // main.js
  const router = ({
      routers : [
  		{ path : '/' , redirect : 'teams' }, 
  		{ path : '/teams' , name : 'TeamPage', component : TeamList, children : [ // children으로 하위 path 지정 가능 
           	{ path : ':teamId' , name : 'TeamDetails',component : TeamMember  }, // '/tems/:teamId' 의 호출과 같다. 다만 TeamList 컴포넌트 안에 TeamMember 가 표시되는 방식으로 동작한다. 
           ]}, 
      ],  
  })
  ```


### 5. 쿼리 파라미터 

- URL 뒤에 '?sort=abc' 같은 형태로 데이터를 보내줄 수 있는데, 이때 사용되는것이 쿼리 파라미터이다. 

  - `this.$router.query` 를 사용해 쿼리 파라미터로 받아온 값들을 확인할 수 있다. 

  ```js
  // main.js
  const router = ({
      routers : [
  		{ path : '/' , redirect : 'teams' }, 
  		{ path : '/teams' , name : 'TeamPage', component : TeamList, children : [ 
           	{ path : ':teamId' , name : 'TeamDetails',component : TeamMember, query : { //해당 URL의 쿼리로 sort와 userName을 내려줄 수 있다.
                  sort : 'abc',
                  userName : 'user01',
              } }, 
           ]}, 
      ],  
  })
  ```

### 6. 스크롤 위치 기억 라우터

- [공식 문서](https://router.vuejs.org/guide/advanced/scroll-behavior.html)

- router의 히스토리 모드에서만 동작한다. 

- 페이지마다 ( 즉, 라우터 설정 Path 마다 ) 페이지의 스크롤 위치를 알 수 있는 메소드이다. 

  - 해당 기능을 통해 이전 페이지의 스크롤 위치를 기억하여 , 뒤로가기시 해당 위치로 바로 이동할 수 있게 해주는 유용한 기능이다.
  - **to, from** : router 객체(라우트의 경로, 매개변수, 쿼리 등)를 가진다. 
  - **savedPosition**  : 이전 페이지에서 저장된 스크롤 위치

  ```js
  const router = ({
      history : createWebHistory(), // 라우터 이용시 이전페이지 및 이동경로를 알 수 있다. 
      routers : [// 표시할 페이지를 인자값으로 가진다. 
  		{ path : '/teams' , name : 'TeamPage', component : TeamList }, 
  		{ path : '/users' , name : 'UserPage',component : UserList }, 
      ],  
      linkActiveClass : 'active ', // 설정 안한다면 디폴트값인 'router-link-active' 로 설정된다. 
      scrollBehavior(to, from, savedPosition){
          // to: 이동하려는 라우트
          // from: 현재 라우트
          // savedPosition: 이전 페이지에서 저장된 스크롤 위치
          
          if(savedPosition){ // 이전 페이지에서 저장된 스크롤 위치가 있는 경우
              return savedPosition; // 이전 스크롤 위치로 이동
          }
          return { left : 0, top : 0 } // 만약 저장된 스크롤 위치가 없다면 가장 위쪽인 0,0 으로 이동
  	},
  })
  ```

- 위 코드에서 savedPosition 역할

  1. 사용자가 페이지를 새로고침하지 않고 다른 라우트로 이동하면, 새 페이지의 스크롤 위치를 반환한다. 
  2. 사용자가 이전 페이지에서 뒤로 가기를 누르면 이전 스크롤 위치를 반환한다. 
  3. 사용자가 처음 페이지로 접근하거나 저장된 스크롤 위치가 없는 경우 페이지 상단으로 스크롤한다. 

### 7. 매개변수에 함수 전달

- 매개변수로 함수를 전달해 호출된 곳에서 넘겨준 함수를 실행한다. 

```js
// router의 index.js 파일  
// jwt 호출하는 곳 
import jwt from '../libs/jwt'

jwt.validToken(
      () => {
        // jwt.js의 validToken 에서 success() 함수 호출시 실행
        var auth = storage.get("auth");
        checkAuth(to, from, next, auth); // 사용자 권한 체크
      },
      async () => {
        // jwt.js의 validToken 에서 failure() 함수 호출시 실행
        // 로그인 되어있다면 실행
        if (userData.authentication == true) {
          // 로그인 사용자 정보 초기화
          localStorage.removeItem("userData");
          store.commit("userData/INIT_USER_DATA");
          Vue.$cookies.remove("hasToken");
        } else {
          return next({ name: "auth-login" });
        }
        return next({ name: "auth-login" });
      }
    );
```

```js
// jwt.js 파일 
import axios from './axios'

export default {
    validToken : function(success, failure) {
        axios.get('auth')
        .then(res => {
            if(res.data.success) {
              if (success != null) success(); // 매개변수로 넘어온 success()함수 호출
            } else {
                this.refreshToken(success, failure);
            }
        }).catch(() => {
          if (failure != null) failure(); // 매개변수로 넘어온 failure()함수 호출
        });
    },
}
```



