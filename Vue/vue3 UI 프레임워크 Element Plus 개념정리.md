# Vue3 의 UI 프레임워크 Element Plus 개념정리

---

>[참고 사이트1](https://velog.io/@olddanbi97/Vue3Element-Plus-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0)
>
>[공식문서](https://element-plus.org/en-US/component/overview.html)

##  Element Plus란?

1. **Element Plus**는 Vue 3용으로 개발된 UI 컴포넌트 라이브러리입니다.
2. Vue 3에서는 **Element UI** 대신 **Element Plus**를 사용해야 함
   1. 원래 Vue 2용으로 존재하던 **Element UI**가 Vue 3를 지원하지 않기 때문에, Vue 3에서는 **Element Plus**를 사용해야 한다 .

## 특징

- Vue 3에 최적화된 UI 컴포넌트 라이브러리
- 다양한 UI 컴포넌트 제공 (버튼, 테이블, 모달, 폼 등)
- TypeScript 지원
- 다국어(Localization) 지원
- 사용자 정의 스타일 커스터마이징 가능

------

## 설치 방법 (Vue 3 프로젝트에 추가)

### 1.  프로젝트에 Element Plus 설치

```bash
npm install element-plus
```

또는

```bash
yarn add element-plus
```

### 2. `main.js`에서 Element Plus 등록

```js
import { createApp } from 'vue';
import App from './App.vue';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';

const app = createApp(App);
app.use(ElementPlus);
app.mount('#app');
```

------

## 기본 사용 예제

```vue
<template>
  <div>
    <el-button type="primary">Primary Button</el-button>
  </div>
</template>
```

## 주요 컴포넌트

| 컴포넌트        | 설명            |
| --------------- | --------------- |
| `el-button`     | 버튼            |
| `el-table`      | 테이블          |
| `el-dialog`     | 모달 창         |
| `el-input`      | 입력 필드       |
| `el-form`       | 폼 요소         |
| `el-select`     | 드롭다운 선택창 |
| `el-pagination` | 페이지네이션    |

------

## 공식 문서

🔗 https://element-plus.org/