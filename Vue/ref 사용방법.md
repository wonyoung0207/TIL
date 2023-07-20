## vue에서의 ref 

---

>

## ref

### 정의

- `ref` 옵션은 Vue 컴포넌트나 **HTML 엘리먼트**에 **고유한 참조를 설정**하는 데 사용되는 **옵션**이다. 
- vue에서 제공하는 **기본 프로퍼티 중 하나**로, vue의 기본 제공 프로퍼티는 모두 앞에 **' $ ' 를 붙여 사용**한다.

### 동작 방법

- `<input type="text" ref="myInput"> ` 와 같이 ref를 사용하면 **vue는 특정공간에 ref에 대한 정보를 등록**한다. 
- 이떄 **javascript**에서  **`this.$refs ` 로 불러와 사용**할 수 있다. 

### 사용처

- 값이 계속해서 필요할때가 아닌 필요할때만 추출해서 쓸때 사용한다. 
  - **@input** 사용시 **키 입력할때마다 값이 추출**된다. 
  - 하지만 **ref**를 이용하면 **특정 javascript 동작이 있을 떄만** 값을 추출할 수 있다
- 따라서 `ref`를 사용하여 엘리먼트에 이름을 부여하면, 해당 엘리먼트에 직접적으로 접근하거나 메서드를 호출할 수 있다. 

### 예시

- `this.$refs.myButton`을 통해 해당 엘리먼트에 접근한다. 
  - 따라서 `this.$refs.name`를 이용해 태그들 중  ref=name 에 접근할 수 있다.  

```vue
<!-- 1번 예시  -->
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

<!-- 2번 예시  -->
<input type="text" @input="addText($event)"> 
<input type="text" ref="addRefText"> 

<script>
	addText(event){
        event.target.data;
    }
    addRefText(){
        this.$refs.addRefText;
    }
</script>
```

