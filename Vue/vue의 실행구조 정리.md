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

- main.js에서 가장 먼저 불려지는 싱글 컴포넌트로, template, html, css 코드 모두가 있는 vue 파일이다. 

### package.json 파일

- npm 으로 JavaScript 라이브러리를 설치하면 해당 파일에 추가가되어 버전관리를 돕는다. 

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
