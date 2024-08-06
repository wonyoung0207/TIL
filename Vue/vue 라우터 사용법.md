# Vue 라우터 사용법

---

>

## 라우터 사용법 

1. **path** : 이동할 페이지 URL 
2. **component** : 표시할 컴포넌트 
3. **`<router-view>`** : vue 라우터에게 렌더링 위치를 알려주는 특수한 컴포넌트 
   - vue에 라우터가 연결되어있어야 사용할 수 있는 태그 
   - 라우터에 연결되어있는 **컴포넌트가 표시되는 부분**
     - 즉, **URL별**로 설정되어있는 **페이지(컴포넌트) 가 표시**되는 부분이다.  
4. **`<router-link>`** : 클릭시 vue-router에 설정되어있는 path 로 이동시켜주는 컴포넌트 
   - html 에는 a태그로 표시되어 router-vue가 기본동작을 막고 설정한 router로 이동할 수 있도록 설정된 컴포넌트를 띄워준다. 
5. **`linkActiveClass`** : router-link 에 설정되는 class  이름을 설정한다. 해당 클래스를 이용해 CSS 스타일을 적용할 수 있다. 
6. **`meta`** : $router 를 이용할 수 있는 곳에서 meta 필드에 설정되있는 데이터를 사용할 수 있다.
7. **`scrollBehavior`** : 스크롤의 위치를 기억하는 메소드로, 페이지 이동간 이전 페이지 스크롤 위치를 기억할 때 유용하게 사용된다. 

```js
// main.js 파일 

import { Router, createWebHistory } from 'vue-router';
import TeamList from './components/teams/TeamList.vue';
import UserList from './components/users/UserList.vue';

const router = ({
    history : createWebHistory(), // 라우터 이용시 이전페이지 및 이동경로를 알 수 있다. 
    routers : [// 표시할 페이지를 인자값으로 가진다. 
		{ path : '/teams' , name : 'TeamPage', component : TeamList }, 
		{ path : '/users' , name : 'UserPage',component : UserList }, 
    ],  
    linkActiveClass : 'active ', // 설정 안한다면 디폴트값인 'router-link-active' 로 설정된다. 
    meta : { componentAuth : true }, // router 사용할 수 있는 모든 곳에서 사용할 수 있는 데이터이다. 
    scrollBehavior(to, from, savedPosition){
        // to : 이동할 페이지 URL 
        // from : 이전 페이지 URL
        // savedPosition : 저장된 스크롤 위치 
        if(savedPosition){
            return savedPosition;
        }
        return { left : 0, top : 0 } // 만약 저장된 스크롤 위치가 없다면 가장 위쪽인 0,0 으로 이동
	},
})

const app = createApp(App)

app.use(router); // app.use() : 서브 파티 패키지오 다른 기능을 연결해 주는 역할을 한다.
// 라우터를 전달하면 vue가 라우터를 인식하게 된다.
// 또한 다른 곳에서 this.$router 로 라우터의 메소드를 사용할 수 있다. 

app.mount('#app')
```

```vue
// App.vue 파일

<template>
	<main>
		<router-view></router-view>    
	</main>
</template>
```

```vue
// navigation.vue 파일 

<template>
	<header>
        <nav>
            <ul>
                <li> <router-link to="/teams"> Teams </router-link></li>    
                <li> <router-link to="/users"> Users </router-link></li>    
            </ul>
    	</nav>
	</header>
</template>
    
```



### 라우터  `$router `사용법

- app.vue에 router를 등록했을 경우 사용할 수 있는 기능이다. 

- vue-router 에서 제공하는 다양한 메소드를 이용할 수 있다. 

  - router 의 히스토리 스택을 이용해 페이지를 자유롭게 이동 할 수 있다. 

- 메소드 종류

  1. **push( )**

     - **URL 이동**. **히스토리 스택에 추가**되므로 뒤로가기 버튼 동작시 이전 URL 로 이동

     - router-link의 to 와 같은 기능으로, 선언한 매개변수 주소로 이동한다. 

       ```js
       // 리터럴 string
       router.push('home')
       
       // object
       router.push({ path: 'home' })
       
       // 이름을 가지는 라우트
       router.push({ name: 'user', params: { userId: 123 }})
       
       // 쿼리와 함께 사용, 결과는 /register?plan=private 이다. 
       router.push({ path: 'register', query: { plan: 'private' }})
       
       // 새창 열기 
       window.open(this.$router.resolve({ name: 'dashboard' }).href, '_blank'); // 새창에서 router 표출 
       this.$router.go(-1); // 현재 브라우저 이동 중지 
       ```

  2. **replace( )** 

     - **URL 이동**. 현재 URL 을 대체하기 때문에 **히스토리 스택 쌓지 않음**

  3. **go( )** 

     - `window.history.go(n)`와 비슷하게 동작하는 것으로, 숫자만큼 뒤로가기, 앞으로가기를 진행한다. 
  
     - 브라우저 히스토리에서 앞 또는 뒤로 이동할 수 있게 한다. 
  
       ```js
       // 한 단계 앞으로. history.forward()와 같다.
       this.$router.go(1)
       
       // 한 단계 뒤로. history.back()와 같다.
       this.$router.go(-1)
       
       // 3 단계 앞으로 간다.
       this.$router.go(3)
       
       // 지정한 만큼의 기록이 없으면 자동으로 실패 
       this.$router.go(-100)
       this.$router.go(100)
       ```

  4. **back( )**

     - `history.go(-1)`와 같은 기능으로, 히스토리의 한칸 뒤로 이동한다. 

  5. **forward( )**

     - `history.go(1)`와 같은 기능으로, 히스토리의 한칸 앞으로 이동한다. 

  6. **path** 

     - 현재 경로 확인
  
  7. params
  
     - 동적 router를 사용할 때 사용되며, ( : ) 를 이용해 동적 URL을 표시한다. 
     - `this.$router.params.teamId` 에서 teamId 가 동적 URL로 설정되어있는 부분이다. 



### not Fount 페이지 처리

```js
import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
    history : 'createWebHistory',
    routers : [
		{ path : '/login', name : 'loginPage' component : null,
		{ path : '/', name : 'main', component : null, 
         	beforEnter(to, from, next ){
        		if(localstorage.getItem('userData') != null){
    				next({name : "coache"});
				}else{
                    next({name : "loginPage"}); // 라우터의 이름으로 페이지를 호출 한다. 
                }
        }};
		{ path : '/coaches', name : 'coache', component : null}
		{ path : '/coaches:id', name : 'coacheId' component : null, 
            children : [
			{path : 'context' , name : 'coacheContext' component : null }, // 주소 : /coaches/c1/context
         	]
		},
		{ path : ':/notFound(.*)', name : 'notFound' , component : null } 
        // ':/' 다음에 오는 값은 사용자가 정함. notFound 가 아닌 a 로 정해도 됨 
        // () 안에는 정규식이 온다. 
		{ path : '/coaches:id', name : 'coacheId',
            component : () => import("@/views/abc.vue"), // 컴포넌트를 import 하지않고 절대주소로 연결도 가능
    ]
})
```



