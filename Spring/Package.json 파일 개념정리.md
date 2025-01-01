# package.json 파일 개념정리

---

>[package.json NPM 문서](https://docs.npmjs.com/cli/v8/configuring-npm/package-json)
>
>[axios 문서 ](https://github.com/axios/axios/blob/main/package.json)
>
>[vue-enterprise 문서](https://github.com/bencodezen/vue-enterprise-boilerplate/blob/main/package.json)

## package.json 

### 정의

- npm 으로 JavaScript 라이브러리를 설치하면 해당 파일에 추가가되어 버전관리를 돕는다. 
  - 프로젝트에서 사용하는 **패키지들**에 대한 정보

  - 시작시 실행되며 필요한 패키지들이 node_modules 에 추가된다. 

  - **따라서 `npm install` 하면 실행되어 필요한 의존성 패키지들이 node_modules 에 다운받아 진다.** 

- package.json 파일은 프로젝트에 대한 정보를 갖고 있는 파일이다. 그리고 dependencies와 같은 속성을 활용하여 프로젝트에 의존된 라이브러리를 관리한다.
  - package.json 파일은 직접 작성할 수도 있고, npm init 명령어를 통하여 자동으로 생성할 수 있다.

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

| 속성            | 설명                                                         |
| --------------- | ------------------------------------------------------------ |
| name            | **프로젝트 이름**으로 가장 중요하다. (필수항목)              |
| version         | **프로젝트 버전**을 정의한다. 주로 3단계 버전을 사용한다. (필수항목) |
| description     | **프로젝트 설명**을 기술한다. npm search로 검색된 리스트에 표시되기 때문에 사람들이 패키지를 찾아내고 이해하는 데 도움이 된다. |
| keywords        | 프로젝트를 **검색할 때 참조되는 키워드**이다. description과 마찬가지로 npm search로 검색된 리스트에 표시된다. |
| private         | true 로 설정되면 npm 게시를 거부합니다. 이 값은 개인 리포지토리가 실수로 게시되는 것을 방지합니다. |
| main            | main은 **프로그램의 기본 진입점** 입니다.                    |
| scripts         | 프로젝트에서 자주 **실행하는 명령어**를 scripts로 작성해두면 npm 명령어로 실행 가능하다. |
| author          | **제작자의 이름**을 지정합니다.                              |
| license         | 패키지에 대한 **라이선스를 지정**하여 사람들이 패키지를 사용할 수 있는 방법과 패키지에 대한 제한 사항을 알 수 있도록 해야 합니다. |
| dependencies    | **프로젝트에서 사용하는(의존하는) 모듈을 기술**하는 부분이다. 따라서, 이 프로젝트가 어떤 모듈을 사용하는지 한눈에 볼 수 있다. <br />애플리케이션을 설치할 때 이 내용을 참조하여 필요한 확장 모듈을 자동으로 설치한다. 따라서 개발한 애플리케이션에서 사용하는 모듈은 여기에 꼭 명시를 해주어야 한다. |
| devDependencies | **개발할 때만 의존하는 모듈을 관리**한다.                    |





## package-lock.json

1. 사용되는 패키지에 의존성을 가지는 패키지를 관리하는 파일 
2. 즉,모듈들의 의존성 정보를 가지고 있는 파일이다. 
3.  package-lock.json 파일을 참고하여 node_modules 디렉토리안에 모듈을 다운받습니다.
