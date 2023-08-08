## npm 커스텀 명령어

---

>[참고 사이트1](https://joshua1988.github.io/webpack-guide/getting-started.html#%EC%9B%B9%ED%8C%A9-%EB%A7%9B%EB%B3%B4%EA%B8%B0-%ED%8A%9C%ED%86%A0%EB%A6%AC%EC%96%BC)

## 커스텀 명령어

### 정의

- 사용자가 임의로 명령어의 이름과 동작을 정의해서 사용할 수 있는 기능을 의미한다. 
  - **package.json**에 **script 속성을 사용**한다. 
  - package.json 파일은 NPM 패키지 관리 파일이다. 

### 사용 이유 

-  **복잡한 npm 명령어를 손쉽게 사용**할 수 있다. 
   - 즉, `node server.js`와 `cross-env NODE_ENV=production webpack --progress --hide-modules` 같은 복잡하고 긴 명령어를 손쉽게 **key값으로 치환해서 사용**할 수 있다.

### 사용방법

1. package.json 에 script 태그를 추가한다. 

   ```json
   {
     "name": "vuexy-vuejs-react-html-laravel-admin-dashboard-template",
     "version": "6.4.0",
     "scripts": {
         // 스크립트 속성 작성 
     },
   }
   ```

2. script 속성에 `key : 커스텀 명령어` 의 내용을 작성한다 .

   ```json
   "scripts": {
       "serve": "vue-cli-service serve --port 34051",
       "build": "vue-cli-service build",
       "lint": "vue-cli-service lint"
   },
   ```

3. key를 이용해 npm 으로 설정되어있는 명령어를 손쉽게 실행한다. 

   ```npm
   npm run serve
   npm run build
   npm run lint
   ```
