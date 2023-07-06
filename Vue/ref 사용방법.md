## vue에서의 ref 란

- `ref` 옵션은 Vue 컴포넌트나 **HTML 엘리먼트**에 **고유한 참조를 설정**하는 데 사용되는 **옵션**이다. 
  - Vue 인스턴스의 `$refs` 속성을 통해 접근할 수 있다. 

- 따라서 `ref`를 사용하여 엘리먼트에 이름을 부여하면, 해당 엘리먼트에 직접적으로 접근하거나 메서드를 호출할 수 있다. 

### 예시

- `this.$refs.myButton`을 통해 해당 엘리먼트에 접근한다. 
  - 따라서 `this.$refs.name`를 이용해 태그들 중  ref=name 에 접근할 수 있다.  

```vue
<template>
  <button ref="myButton" @click="handleClick">Click me</button>
</template>

<script>
    export default {
      methods: {
        handleClick() {
          // 버튼 엘리먼트에 접근하여 속성을 변경하거나 메서드를 호출할 수 있음
          this.$refs.myButton.disabled = true;
          this.$refs.myButton.innerText = "Clicked!";
        }
      }
    }
</script>
```

