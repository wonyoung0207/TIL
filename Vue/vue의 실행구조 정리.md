# vue의 실행 구조 

---

>[vue CLI 공식홈페이지](https://cli.vuejs.org/)

## 1. 도구 및 명령어

### CLI ( Commend Line Interface )

- 명령어를 통한 특정 액션을 실행하는 도구
- 사용법
  - 터미널과 node를 이용해 npm 으로 vue CLI를 다운받을 수 있다. 방법은 공식홈페이지에서 확인 

### npm run serve

- npm (node package manager ) 를 통해 로컬 서버를 실행한다. 
- **package.json에 설정해놓은 serve 라는 변수에 설정된 행위를 실행**한다. 



## 2. 관련 파일 설명

### index.js

- public 파일 밑에 구현되어있는 파일.
  - 빌드된 내용들이 모두 주입되는 곳
- 여기서 빌드된 내용들이란 **`src` 폴더** 밑에 구현되어있는 App.vue , main.js ... 의 파일들이다.

### main.js

- index.js에 의해 빌드되는 파일로, 가장 상위에서 사용되는 App.vue를 생성하는 파일이다. 

- **Vue 프로젝트는 main.js 파일을 먼저 로드하고 실행하도록 설정**되어있다. 

- 이 파일에서 **`id=app` 이라는 vue파일을 mount (Dom에 부착 ) 한다.**

- 해당 파일에서 다른 컴포넌트들을 불러와 render() 한다고 생각하면 된다. 

  ```html
  new Vue({
  	render : h => h(App),
  }).$mount('#app')
  
  <!-- 위와 같은 의미 -->
  new Vue({
  	el: '#app',
  	render : h => h(App),
  })
  ```

### 싱글 파일 컴포넌트

- 하나의 vue 파일 내에 html, javascript, css를 모두 구현한 vue 파일 

```vue
<template>
	html 코드
</template>

<script>
export default {
	javaScript 코드 
}
// export default 는 new Vue({}) 와 같다고 보면 됨
</script>

<style>
	Css 코드 
</style>
```

### App.vue

- Vue 컴포넌트를 작성하게 한다. 
- main.js에서 가장 먼저 불려지는 싱글 컴포넌트로, template, html, css 코드 모두가 있는 vue 파일이다. 
- 싱글 컴포넌트로 , template, script, css로 나누니어 작성된다. 
- main.js에서 등록한 컴포넌트의 이름을 이용해 template 태그안에 적어 컴포넌트를 포함시킨다. 

### package.json 파일

- npm 으로 JavaScript 라이브러리를 설치하면 해당 파일에 추가가되어 버전관리를 돕는다. 

- 프로젝트에서 사용하는 **패키지들**에 대한 정보

- 시작시 실행되며 필요한 패키지들이 node_modules 에 추가된다. 

- **따라서 `npm install` 하면 실행되어 필요한 의존성 패키지들이 node_modules 에 다운받아 진다.** 

- 형태

  ```json
  {
    "name": "vuexy-vuejs-react-html-laravel-admin-dashboard-template",
    "version": "6.4.0",
    "private": true,
    "scripts": {
      "serve": "vue-cli-service serve --port 34051",
      "build": "vue-cli-service build",
      "lint": "vue-cli-service lint"
    },
    "dependencies": {
      "vue-router": "3.4.9",
      "vue-simple-calendar": "^5.0.1",
      "vue-slide-bar": "^1.2.0",
      "vuex": "3.6.0"
    },
    "devDependencies": {
      "eslint-plugin-import": "^2.22.1",
      "eslint-plugin-vue": "6.2.2",
      "sass": "1.32.*",
      "sass-loader": "^10.1.0",
      "vue-template-compiler": "2.x"
    }
  }
  ```

- dependencies와 devDependencies의 차이점

  - **dependencies**
    - 애플리케이션과 관련된 로직 ( 화면 )
    - 종류 : jquery, jquery-ui, react , angular, chart, vue
  - **devDependencies**
    - 개발할때 보조해주는 라이브러리. 
    - 서버 배포할때 **배포되지 않는다**. 
    - 종류 : vue, webpack, js-compression 

### public 폴더

- vue 애플리케이션을 다루는 폴더
- **index.html 에 있는 app을 mount 해서 vue 컴포넌트들을 삽입**한다. 

### src 폴더

- **모든 javascript 파일**을 가진 폴더



## 3. vue 파일의 실행 순서

1. .vue 파일의 컴포넌트 생성 후 `export default` 로 내보냄 (template 태그 안에는 해당 컴포넌트를 Dom에 삽입했을 떄 표시할 태그들을 나열한다.)

   ```vue
   <template>
       <ul >
           <li>
               <h2>{{  friend.name  }}</h2>
           </li>
       </ul>
   </template>
   
   <script>
       export default {
           data() {
               return {
               };
           },
           methods: {
           }
       };
   </script>
   ```

2. main.js 에서 해당 컴포넌트를 import 한다. 

3. import한 컴포넌트를 App.vue의 `#app` 에 컴포넌트로 등록 후 mount 한다. 

   ```js
   import App from './App.vue';
   import FriendContact from './components/FriendContact.vue';
   
   const app = createApp(App);
   app.component("friend-contact", FriendContact);
   app.mount('#app');
   ```

4. App.vue의 template 태그 안에서 사용하고 싶은 위치에 해당 컴포넌트 이름을 넣는다. 

   ```vue
   <template>
       <section>
           <h2>
               My friends
           </h2>
           <ul>
               <friend-contact></friend-contact>
               <friend-contact></friend-contact>
           </ul>
       </section>
   </template>
   ```



## 4. Dom 트리 구조

[참고 사이트](https://v2.ko.vuejs.org/v2/guide/render-function.html)

- **브라우저**는 태그와 내용등 **모든 엘레멘트를 노드**로 본다. 

  - 노드는 페이지를 이루는 각각의 조각을 뜻하며 텍스트, 주석 , 태그 등등 **모든것이 노드로 인식**된다. 

  - 따라서 브라우저가 코드를 읽게되면 "**DOM 노드 트리**" 를 만든다. 

  - 노드 트리 

    <img src="./images/Dom트리노드.png" width="600">

- `{{ }}` 를 이용해 vue가 페이지에서 수정하고싶은 노드만 자동 갱신하게 할 수있다. 

