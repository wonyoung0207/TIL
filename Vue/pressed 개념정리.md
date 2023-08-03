# pressed 개념정리 

---

> [참고 사이트1](https://bootstrap-vue.org/docs/components/button)

##  pressed

### 정의

- **button 태그**에 사용되는 **vue의 옵션**으로, 버튼의 눌림과 안눌림의 표시 차이를 나타낼 때 사용한다. 
  - 특정 이벤트가 발생했을 때 해당 엘리먼트가 **눌려진 상태**일 때만 동작하도록 지정할 수 있다.  
  - **즉, 버튼이 눌렸는지 안눌렸는지 눈으로 확인할 수 있도록 하는 기능이다.** 

### 예시

<img src="./images/pressed 예시1.png" width="300">

<img src="./images/pressed 예시2.png" width="300">

### 사용법

```vue
<template>
  <div>
    <h5>In a button group</h5>
    <b-button-group size="sm">
      <b-button
        v-for="(btn, idx) in buttons"
        :key="idx"
        :pressed.sync="btn.state"
        variant="primary"
      >
        {{ btn.caption }}
      </b-button>
    </b-button-group>
    <p>Pressed States: <strong>{{ btnStates }}</strong></p>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        myToggle: false,
        buttons: [
          { caption: 'Toggle 1', state: true },
          { caption: 'Toggle 2', state: false },
          { caption: 'Toggle 3', state: true },
          { caption: 'Toggle 4', state: false }
        ]
      }
    },
    computed: {
      btnStates() {
        return this.buttons.map(btn => btn.state)
      }
    }
  }
</script>
```

