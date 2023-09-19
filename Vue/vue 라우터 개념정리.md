# Vue 라우터 개념정리 

---

>[인프런 강의 - 라우터 사용방법 정리 공간 ](https://joshua1988.github.io/vue-camp/vue/router.html#%E1%84%87%E1%85%B2-%E1%84%85%E1%85%A1%E1%84%8B%E1%85%AE%E1%84%90%E1%85%A5-%E1%84%89%E1%85%A5%E1%86%AF%E1%84%8E%E1%85%B5)
>
>[참고 사이트1](https://happy-coding-day.tistory.com/entry/Vue-vue-router%EC%97%90%EC%84%9C-Hash-Mode-Vs-History-Mode-%EC%B0%A8%EC%9D%B4%EC%A0%90%EC%9D%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80)
>
>[참고 사이트2](https://router.vuejs-korea.org/ko/guide/essentials/history-mode.html#%ED%95%B4%EC%8B%9C-%EB%AA%A8%EB%93%9C)
>

## 라우터  ( Router )

### 정의

- 뷰 라이브러리를 이용하여 싱글 페이지 애플리케이션을 구현할 때 사용하는 라이브러리
- 브라우저에서 JavaScript 로 URL의 변경사항을 관리하고 동일한 싱글 페이지 애플리케이션 상에 URL이 변경되거나 해당 URL을 기반으로 화면에 로드한다. 
- 간단한 패키지를 설치해 사용할 수 있다. 

### 필요 라이브러리

- 라우터가 있는 라이브러리를 가져와야 함 
- 라우터를 설치하는 방법은 CDN 방식과 NPM 방식 2가지가 있다.
  - 그 전에 Vue를 사용하는데 필요한 라이브러리도 필요하기 때문에 가져와줘야 한다. 
  - 이때 라이브러리 가져오는 순서도 중요하다. 먼저 Vue 에 필요한 라이브러리 가져온 후  라우터 라이브러리를 가져온다. 

```html
<!-- Vue.js 개발버전, 도움되는 콘솔 경고를 포함. -->
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
<!-- Vue Router CDN 코드 -->
<script src="https://unpkg.com/vue-router@3.5.3/dist/vue-router.js"></script>

<!-- NPM 방식-->
npm install vue-router
 
```

### 라우터 패키지 설치 방법

1. 사용 애플리케이션에서 `npm install --save vue-router` 를 이용해 패키지를 설치한다. 

2. `npm run serve` 를 이용하면 라우팅이 추가된것을 확인한다. 

3. import 를 이용해 vue-router 패키지를 가져온다. 

   ````js
   import { Router } from 'vue-router';
   ````

4.  가져온 라우터를 이용해 여러 설정을 할 수 있다. 

    - router는 현시점 두가지로 나뉜다. 
      1. vue-router ( vue 2.x 를 위한 버전 )
      2. vue-router-next ( vue 3.x 를 위한 버전 )

    ```js
    import { Router, createWebHistory} from 'vue-router';
    
    const router = ({
        history : createWebHistory(), // 라우터 이용시 이전페이지 및 이동경로를 알 수 있다. 
        routers : [],  // 표시할 페이지를 인자값으로 가진다. 
    })
    ```

### 형태

```html
<script>
    var LoginComponent = {
        template : '<div> login </div>',
    }
    
    var MainComponent = {
        template : '<div> main </div>',
    }
    
    var router = new VueRouter({
      mode : 'history',
      // 페이지의 라우팅 정보      
      routes: [ // 어떤 페이지가 뿌려질지 배열로 담긴다. 
        { // 객체 1 
          path: '/login',// 페이지의 url
          name: 'login', // name을 지정해줘도 되고 안해줘도 된다. 안해도 자동으로 됨 
          // 해당 url에서 표시될 컴포넌트
          component: LoginComponent // 페이지마다 뿌려질 컴포넌트는 무조건 하나이기 때문에 components가 아니라 component 이다. 
        }, 
        { // 객체 2
          path: '/main',
          component: MainComponent
        }
      ]
    });

    new Vue({
        el : '#app',
        data : ,
        router : router,
    })
</script>
```

1. mode

   - **url 에 \# 을 붙이지 않고 '/' 만 표시**되도록 함 

   - Vue는 URL 이동 시 뒤에 \# 를 가지고 브라우저의 히스토리 API를 활용한다. 

     - 따라서 히스로토 이용하지 않을 경우 없애는게 **URL 을 더 깔끔하게 볼 수 있는 방법**이다. 

     ```html
     // mode 적용 전 
     rotuer.html#/login
     
     // mode : 'history' 적용 후 
     router.html/login
     ```

2. **routes** 

   - 배열로 선언되어 배열 안에는 **객체**가 들어간다. 
     - 따라서 **페이지의 갯수**만큼 **객체**가 배열에 들어가는 형태이다. 

3. **path**

   - 페이지의 **URL** 이름 

4. **component** 

   - 해당 URL에서 **표시될 컴포넌트** 

### 라우터의 모드 

- 사용자의 브라우저 이동 경로를 파악할 수 있는 설정값으로 2가지가 존재한다. 

  1. Hash 모드 ( default )
  2. History 모드 

- **Hash 모드**

  - 모든 URL을 Hash(#) 형태로 서비스한다. 
  - 즉, URL이 변경될 때 router를 통해 주소를 호출하므로 서버 요청 주소가 1개이다. 
    - SPA 으로써 동작한다. 
    - ex) `http://localhost:9090/#/user`
  - 또한 페이지가 다시 로드되지 않는다. 

  ```js
  import { createRouter, createWebHistory } from 'vue-router'
  
  const router = createRouter({
    mode: 'history',	// 같은 기능->   history: createWebHistory(),
    routes: [
      //...
    ],
  })
  ```

- **History 모드** 

  - Page 재로딩이 발생한다. 
  - 하지만 사용자가 직접 없는 주소를 호출했을 경우 404 페이지를 표시한다. 
  - 해당 URL에 맞는 리소스를 서버로 요청해야하기 때문에 매번 HTML, Javascript, CSS를 중복으로 가져와 퍼포먼스가 떨어질 수 있다. 

  ```js
  import { createRouter, createWebHistory } from 'vue-router'
  
  const router = createRouter({
    history: createWebHistory(),
    routes: [
      //...
    ],
  })
  ```

- 차이점

  - Hash 모드에서는 중간에 `http://localhost:9090/user `와 같이 다른 url을 요청하면 전체 리소스 로딩없이 SPA page 이동만 일어난다.

  - History 모드에서는 page 재로딩이 다시 발생한다.

  - 따라서 history 모드에서는 없는 page의 경우에 404 not found 에러가 뜨게 된다.

    -  이를 위해서 router 설정시에 * path에 대해서 notFound 페이지 처리를 해주어야 한다.

    ```js
    const router = new Router({
        // mode: 'history',
        routes : [
           ...
            /* [404] */
            { path: '/*'          , component: pageNotFoundPage },
        ]
    });
    ```

### 사용법

```html
<body>
  <div id="app">
    <div>
      <router-link to="/login">Login</router-link>
      <!--<a href="/login"> 로 표시되며 같은 의미를 가진다.  -->
      <router-link to="/main">Main</router-link>
    </div>
    <router-view></router-view>
  </div>
</body>
```

1. **router-link** 
   - \<a> 태그로 표시되는 태그로, 링크 부분을 나타낸다. 
     - 따라서 **페이지 이동** 링크 태그로 생각하면 된다. 
   - **VueRouter** 객체와 연결되어있어 VueRouter 객체의 **routers[] 배열**에 설정한 객체의 **path와 연결**된다. 
2. **router-view**
   - vue에 라우터가 연결되어있어야 사용할 수 있는 태그 
   - 라우터에 연결되어있는 **컴포넌트가 표시되는 부분**
     - 즉, **URL별**로 설정되어있는 **페이지(컴포넌트) 가 표시**되는 부분이다. 

