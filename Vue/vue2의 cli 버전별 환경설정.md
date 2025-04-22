

# vue2의 Cli 버전별 환경설정 방법

---

>
>

## `config/index.js`와 `vue.config.js` 차이점

1. Vue CLI 2와 3 이상에서 설정 방식이 완전히 다르기 때문에, 이 차이를 정확히 아는 게 매우 중요!

| 항목                  | `config/index.js`                                            | `vue.config.js`                                              |
| --------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 적용되는 Vue CLI 버전 | Vue CLI 2.x (Webpack 템플릿 기반)                            | Vue CLI 3.x 이상                                             |
| 내부 구조             | `dev`, `build` 객체로 환경별 분리                            | 환경별 분기 없음, 전체 하나의 export 객체                    |
| 사용 목적             | 개발/배포 환경 분리, Webpack 설정 간접 제어                  | Webpack, devServer 등 직접 커스터마이징                      |
| 설정 파일 위치        | `/config/index.js` + `/build/*.js`                           | 루트 디렉토리 `/vue.config.js`                               |
| 빌드 명령어           | `npm run build` → Webpack config 수동 관리                   | `vue-cli-service build`                                      |
| 대표 설정 항목        | `assetsPublicPath`, `assetsRoot`, `devServer.port`, `proxyTable` | `publicPath`, `outputDir`, `devServer`, `configureWebpack`, `chainWebpack` |

## `config/index.js` (Vue CLI 2.x)

- **Webpack 설정 파일들이 분리되어 있고**

- `build/webpack.dev.conf.js`, `webpack.prod.conf.js`, `webpack.base.conf.js` 등과 함께 동작

- 구조가 복잡하고, 커스터마이징하려면 Webpack 파일을 직접 수정해야 함

  ```js
  # 구조 예시
  build/
  ├── webpack.base.conf.js
  ├── webpack.dev.conf.js
  ├── webpack.prod.conf.js
  config/
  ├── index.js  ← 설정 핵심
  ```

## `vue.config.js` (Vue CLI 3+)

- **Webpack 설정이 추상화되었고**, 하나의 파일에서 대부분 설정 가능

- Vue CLI의 내부 빌드 설정을 오버라이드 할 수 있는 **중앙 설정 파일**

- `vue-cli-service`를 통해 실행되며, 복잡한 Webpack 설정 없이도 개발 가능

  ```js
  // 예시
  module.exports = {
    publicPath: '/twin/',
    outputDir: 'dist',
    devServer: {
      port: 8080,
      proxy: { '/api': 'http://localhost:3000' }
    },
    configureWebpack: {
      resolve: {
        alias: {
          '@': path.resolve(__dirname, 'src')
        }
      }
    }
  }
  ```



## Vue CLI 버전 확인 방법

##### 1. `package.json` 직접 확인

- ❗ Vue CLI 2는 보통 `webpack`, `webpack-dev-server`, `build/`, `config/` 폴더만 있고
   `@vue/cli-service`는 **없다.**
- 즉, `package.json` 안에 다음 항목이 있으면 **Vue CLI 3 이상**:

```js
"@vue/cli-service": "^4.5.19"
```

##### 2. 프로젝트 내 Vue CLI 버전 확인 (로컬 프로젝트 기준)

```bash
npm list @vue/cli-service

# 예시결과값 (이건 현재 프로젝트에서 사용 중인 Vue CLI 버전)
perl복사편집my-vue-app@1.0.0
└── @vue/cli-service@4.5.19
```

