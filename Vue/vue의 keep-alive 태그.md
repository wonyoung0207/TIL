# \< keep-alive > 태그 개념정리

---

>

## \< keep-alive > 태그

### 정의

- Vue.js에서 제공하는 **빌트인 컴포넌트**로, **다른 컴포넌트를 캐싱하고 재사용하는 기능을 제공**한다. 

### 사용 이유

- vue에서 다른 컴포넌트로 넘어갔을 떄 기존 컴포넌트는 **Dom에서 사라지게 된다.** 
  - 만약 기존 컴포넌트의 input이나 **값들을 유지**하고 싶을때 사용하는 것이 **keep-alive**이다. 
- 이 기능을 사용하면 브라우저의 캐시에 저장되어 컴포넌트의 상태를 유지하고 불필요한 렌더링을 방지하여 성능을 최적화할 수 있다. 
  - 주로 **SPA(Single Page Application)에서 자주 사용**된다. 
  - 또한 뒤로 가기 버튼을 눌렀을 때 이전에 방문한 페이지가 그대로 유지되어야 할 때 사용

### 사용방법

- 캐시에 저장해 재렌더링 하고싶지 않은 컴포넌트를 keep-alive 태그로 감싸면 된다. 

```vue
<template>
  <div>
    <button @click="toggleComponent">Toggle Component</button>
    <keep-alive>
      <component :is="currentComponent"></component>
    </keep-alive>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentComponent: 'ComponentA',
    };
  },
  methods: {
    toggleComponent() {
      this.currentComponent = this.currentComponent === 'ComponentA' ? 'ComponentB' : 'ComponentA';
    },
  },
};
</script>
```
