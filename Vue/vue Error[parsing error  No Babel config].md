# "parsing error " :  No Babel config file detected for 파일경로]

---

>[참고 사이트1](https://www.inflearn.com/questions/455705/parsing-error-no-babel-config-file-detected-%EC%97%90%EB%9F%AC%EB%AC%B8%EC%9D%98%EB%93%9C%EB%A6%AC%EA%B2%A0%EC%8A%B5%EB%8B%88%EB%8B%A4)
>
>[참고 사이트2](https://developer-heo.tistory.com/34)
>
>[참고 사이트3](https://goldswan.tistory.com/59)

## 문제 발생 

1. vue 학습하려고 `vue create` 를 이용해 프로젝트를 생성했는데, 다음 에러가 발생함

2. ```
   Parsing error: No Babel config file detected for [프로젝트 경로]\src\App.vue. Either disable config file checking with requireConfigFile: false, or configure Babel so that it can find the config files.
   ```

3. 개발 환경
   - VS Code (Extension : ESLint, Vetur, Vue 3 Snippets)
   - Vue3

## 원인 

1. 원인 1
   1. vue-eslint-parser 파서에 의해 발생. App.vue가 Babel에 처리하는 형식으로 변환되지 않아서 발생함.
   2. Babel
      1. 최신 문법을 **이전 버전의 브라우저에서도 동작**할 수 있는 버전의 **자바스크립트 코드로 변환해주는 도구**
   3. ESLint
      1. 자바스크립트 코드에서 **잠재적인 오류를 찾고**, 일관된 코딩 스타일을 유지하기 위한 **정적 코드 분석 도구**
2. 원인 2
   1. VSCode에서 프로젝트의 ESLint 설정 파일을 인식하는 부분에서 에러발생 

## 해결

### 방법1

1.  프로젝트의 package.json ->eslint -> "requireConfigFile" 관련 설정 추가 

   ```json
   "eslintConfig": {
       "root": true,
       "env": {
         "node": true
       },
       "extends": [
         "plugin:vue/vue3-essential",
         "eslint:recommended"
       ],
       "parserOptions": {
         "parser": "@babel/eslint-parser",
         "requireConfigFile": false
       },
       "rules": {}
     },
   ```

### 방법2

1. vsCode 의 settings.json에 다음 설정 추가 

   ```json
   "eslint.workingDirectories": [
       {"mode": "auto"}
   ],
   ```

   