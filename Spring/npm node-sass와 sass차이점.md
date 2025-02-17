# sass 와 node-sass 라이브러리 차이점

---

>
>
>

## 문제 발생

1. ` npm i` 를 이용해 프로젝트에 필요한 라이브러리를 설치하려고 했다. 
2. 이때 `node-sass` 라이브러리에서 에러가 발생했다. 
3. node-sass 와 node.js의 버전 충돌 
   1. local nodejs 버전 : v16
   2. node-sass 버전 : ^4.14.0

##### 문제 발생 원인

1. `nodejs` 를 v16버전을 사용하고 있었는데, `node-sass` 의 버전인 4.14.0 에서는 해당 nodejs를 사용하지 못한다.
2. 즉, nodejs의 버전을 내리거나, node-sass 버전을 올려야 한다. 

##### 해결방법

1. node.js 버전 내림 => **성공**

   1. `v16.20.1` ===> `v14.0.0`

2. node-sass 버전 올림 => **실패** 

   1. `^4.14.0`  ==>  `^6.0.0`
   2. 실패 이유 : `sass-loder` 안에 있는게 인식 안되서 사용되는 여러 vue 파일에서 에러 발생 

   ```bash
   # nodejs 14 버전 설치
   nvm install 14
   
   # nodejs 14 버전으로 변경 
   nvm use 14
   
   # 로컬 nodejs 리스트 
   nvm list
   
   # Node Modules 설치 
   npm i --legacy-peer-deps
   ```



## node-sass 란?

1. `node-sass`는 **Sass 파일(확장자 .scss 또는 .sass)을 CSS로 컴파일**하는 Node.js 라이브러리이다. 
2. 이를 통해 개발자는 Sass를 사용해 더 구조적이고 재사용 가능한 CSS를 작성하고, 이를 최종적으로 브라우저에서 사용할 수 있는 CSS 파일로 변환할 수있다. 

### Sass란?

- **Sass는 CSS의 확장 언어**로, CSS에 다음과 같은 기능을 추가 한다. 
  - 변수(Variables): CSS 값 재사용 (`$primary-color: #3498db;`)
  - 중첩(Nesting): 계층적 스타일 정의
  - 믹스인(Mixins): 재사용 가능한 코드 블록
  - 함수(Functions): 동적 스타일 계산
  - 상속(Inheritance): 스타일 상속
- Sass는 **브라우저에서 직접 실행되지 않으므로**, CSS로 변환해야 한다. 이 역할을 `node-sass`가 담당한다. 
- **Sass 생태계에서는 `node-sass` 대신 더 가벼운 Dart Sass 기반의 `sass` 라이브러리를 사용하는 추세**



## 기능 비교: `node-sass` vs `sass`

1. `node-sass`는 LibSass 기반이고, `sass`는 Dart Sass 기반이다. 
2. 두 라이브러리는 동일한 Sass 언어를 지원하지만, Dart Sass는 Sass의 참조 구현으로 현재 권장되고 있다. 

##### 공통적으로 지원되는 기능

- 변수, 믹스인, 함수
- 중첩(Nesting)
- Partials(`@import`) 및 Modules(`@use`, `@forward`)
- 연산(산술, 색상 조작 등)
- SassScript
- Sourcemaps 생성

#### **차이점**

| 기능/특성                        | `node-sass` (LibSass)                  | `sass` (Dart Sass)                |
| -------------------------------- | -------------------------------------- | --------------------------------- |
| **Sass 표준 준수**               | Sass 표준을 지원하지만 업데이트가 멈춤 | Sass 표준 준수, 지속적인 업데이트 |
| **JavaScript 함수 커스터마이징** | 지원                                   | 지원                              |
| **@import 지원**                 | 지원 (이후 `@use`로 대체 권장)         | 지원 (Deprecated, `@use` 권장)    |
| **성능**                         | 더 빠름 (C++로 작성됨)                 | 느리지만 충분히 최적화됨          |
| **지원 플랫폼**                  | Node.js 의존                           | 독립적 (Node.js 없이도 실행 가능) |
| **비동기 API**                   | 지원                                   | 지원                              |
| **Custom Importers**             | 지원                                   | 지원                              |



## `sass`와 `sass-embedded`의 관계

1. **`sass` (Dart Sass)**: JavaScript로 작성된 Dart Sass CLI 및 API.
2. **`sass-embedded`**: 더 성능 최적화된 버전으로, Dart Sass를 웹 어셈블리(WASM) 또는 네이티브 방식으로 실행.
3. `node-sass`에서 `sass`로 전환 시, 기능적으로 호환되며, 추가적인 성능 이점이 있습니다.