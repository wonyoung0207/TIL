# vue2의 빌드 정리

---

>

## 빌드 경로 설정

- 빌드 실행 순서 

  ```bash
  # Vue CLI 2인경우 
  npm run build
  → webpack-dev-server 실행
  → webpack.dev.conf.js 로 Webpack 빌드 구성
  → CopyWebpackPlugin 실행됨 (파일 복사)
  → dev 서버 메모리 기반 번들 결과에 포함됨
  ```

1. Vue CLI 2
   1. `config/index.js` 경로에서 설정 
   2. `.env` 이용시 변수명 마음대로지만 require 로 dotenv 패키지 가져와 사용해야함 
2. Vue CLI 3
   1. root 경로의 vue.config.js 통해 build 경로 설정 
   2. `.env` 이용시 `VUE_APP_` 을 접두어로 사용해야함 

| 항목          | `assetsRoot`                             | `assetsPublicPath`                                     |
| ------------- | ---------------------------------------- | ------------------------------------------------------ |
| **역할**      | 빌드 결과물의 실제 디렉토리 위치 설정    | HTML 내에서 **브라우저가 로드하는 자산 URL 경로** 설정 |
| **적용 대상** | 파일 시스템(디스크) 경로                 | 웹 브라우저에서 참조되는 경로                          |
| **예시 위치** | `build/index.html`, `build/js/...`       | `<script src="/js/app.js">`                            |
| **설정 위치** | `path.resolve(__dirname, '../build')` 등 | `'/'`, `'./'`, `'/twin/'` 등                           |



## 빌드 정적 Data 포함 

| 항목        | CopyWebpackPlugin (`root/resources/`)             | Webpack 처리 (`src/assets/`)             |
| ----------- | ------------------------------------------------- | ---------------------------------------- |
| 복사 대상   | 외부 정적 리소스                                  | 내부 참조 자산                           |
| 포함 조건   | 존재만 하면 OK                                    | 참조(import, require)가 필요             |
| 해시 처리   | ❌ 없음 (직접 복사)                                | ✅ 있음 (캐시 방지 목적)                  |
| 파일명 보장 | ✅ 동일                                            | ❌ 해시 붙음                              |
| 사용 예     | `favicon.ico`, `terms.pdf`, 외부 라이브러리, 문서 | 이미지, SCSS, 폰트, Vue 스타일 이미지 등 |



## CopyWebpackPlugin 사용시 빌드 번들링 파일

```
dist/
├── index.html
├── resources/      ← Webpack 로더가 처리한 번들 (해시 포함)
│   ├── js/
│   └── css/
└── static/         ← CopyWebpackPlugin으로 복사된 파일
    ├── manual.pdf
    ├── logo.svg
```

##### 정적 파일 종류 

1. `frontend/resources/`에 있는 정적 파일
   1. 외부 주입할 resources 파일
2. `src/assets` 의 정적 파일
   1. `Vue 컴포넌트에서 import되거나 style에서 참조된 자산`들만 빌드시 포함 

##### Webpack은 다음 두 경로에서 파일을 수집

1. **Webpack 로더 처리 경로 (예: `src/assets/logo.png`)**
2. **CopyWebpackPlugin 경로 (예: `frontend/resources/logo.png`)**
3. 같은 파일명"이 있을 경우 충돌이 발생하지는 않지만, 덮어쓰기 위험은 존재

```
build/twin/resources/
├── logo.a1b2c3.png    ← Webpack이 처리한 것 (자동 해시 붙음)
├── logo.png           ← CopyWebpackPlugin이 그대로 복사한 것
```

##### 외부 resources 번들 

```js
new CopyWebpackPlugin([
  {
    from: path.resolve(__dirname, '../resources'), // 복제원본 resources 위치 
    to: path.resolve(config.build.assetsRoot, 'static'), // 복제한 resouces 위치 
    noErrorOnMissing: true
  }
])
```

##### assets (내부 resources) 번들

```js
// build/webpack.prod.conf.js or webpack.dev.conf.js 파일 
output: {
  path: config.build.assetsRoot,          // 디스크 상의 번들 저장 위치
  publicPath: config.build.assetsPublicPath, // 브라우저에서 참조하는 경로 접두어
  filename: utils.assetsPath('js/[name].[chunkhash].js')
}
```

```js
// config/index.js
module.exports = {
  build: {
    assetsRoot: path.resolve(__dirname, '../build/dist'),     // 디스크에 저장할 경로
    assetsPublicPath: '/frontend/',                               // 브라우저에서 접근할 prefix
  }
}
```

