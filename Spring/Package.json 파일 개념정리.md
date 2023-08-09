# package.json 파일 개념정리

---

>

## package.json 

### 정의

- npm 으로 JavaScript 라이브러리를 설치하면 해당 파일에 추가가되어 버전관리를 돕는다. 
  - 프로젝트에서 사용하는 **패키지들**에 대한 정보

  - 시작시 실행되며 필요한 패키지들이 node_modules 에 추가된다. 

  - **따라서 `npm install` 하면 실행되어 필요한 의존성 패키지들이 node_modules 에 다운받아 진다.** 


### 형태

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

### dependencies 와 devDependencies 차이점

1. dependencies 
   - **애플리케이션과 관련**된 로직 ( 화면 )
   - **배포**용 라이브러리를 나타낸다. 
   - 종류 : jquery, jquery-ui, react , angular, chart, vue
   - 애플리케이션이 **실행될 때 필요한 라이브러리**들이 이에 속한다. 
2. devDependencies 
   - 개발할때 **보조**해주는 라이브러리. 
   - **개발용** 라이브러리를 나타낸다. 
   - 서버 배포할때 **배포되지 않는다**. 
   - 종류 : vue, webpack, js-compression 
   - 애플리케이션 개발시에는 필요하지만, **배포되지 않아도 될 라이브러리**를 나타낸다. 
   - 예를들어, 다음 도구들은 개발시에만 필요하고 애플리케이션 구동과 연관이 없는 라이브러리이다. 
     1. `webpack` : 빌드 도구
     2. `eslint` : 코드 문법 검사 도구
     3. `imagemin` : 이미지 압축 도구

