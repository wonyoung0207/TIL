# provide & inject 개념정리

---

>

## provide

- **inject와 쌍**으로 쓰이는 vue의 옵션으로, **사용하고자 할 데이터를 공유할 떄 사용**한다 .
  - 이떄, provide에 설정한 값들은 inject 배열로 받을 수 있다. 
  - 주의할점은 inject에는 상위 레벨관계에서 제공된 provide만 사용할 수 있다. 
- **부모 컴포넌트에서 자식 컴포넌트로 데이터를 제공한다.** 

## inject

- **자식 컴포넌트에서 부모 컴포넌트로부터 제공된 데이터를 주입**받는다. 

- 단점
  - `provide`와 `inject`는 컴포넌트 간의 강력한 결합을 만들기 때문에 부모 컴포넌트가 변경되면 자식 컴포넌트도 영향을 받게 된다. 
    - 따라서 사용할 때 주의해야 합니다.

### 예시

```html
<template>
  <div>
    <ChildComponent />
  </div>
</template>

<script>
import ChildComponent from './ChildComponent.vue';

export default {
  components: {
    ChildComponent,
  },
  data() {
    return {
      message: "Hello from Parent Component!",
    };
  },
  provide: {
  // 해당 컴포넌트와 상하 관계에 있는 컴포넌트에만 적용된다. -> 하위 컴포넌트로 값을 보냄 
    sharedMessage: "This is a shared message!", 
  },
};
</script>
```

```html
<!-- ChildComponent.vue -->
<template>
  <div>
    <p>{{ sharedMessage }}</p>
    <p>{{ message }}</p>
  </div>
</template>

<script>
export default {
    // 상위에서 provide 로 내려준 값의 내용을 해당 변수에 받아 사용할 수 있다. 
  inject: ["sharedMessage"], 
    
  data() {
    return {
      message: "Hello from Child Component!",
    };
  },
};
</script>
```



