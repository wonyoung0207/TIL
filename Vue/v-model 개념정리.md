# v-model 개념정리

---

>

## v-model

### 정의

- v-bind와 v-on 을 대체하기 위해 나온 개념으로, **양방향(two-way) 데이터 바인딩**을 만들고 싶을 때 사용한다. 

### 속성 

1. `v-bind` 
   - 뷰 인스턴스의 데이터 속성을 해당 HTML 요소에 연결할 때 사용한다.
2. `v-on` 
   - 해당 HTML 요소의 이벤트를 뷰 인스턴스의 로직과 연결할 때 사용한다.

### 단점

- 한글 입력시 한글자 입력이 끝나야 동기화가 된다. 따라서 영문이 아닌 한글인 경우 v-bind와 v-on 사용이 권장된다. 
  - 영문은 누르는 순간 동기화됨 

### 양방향 바인딩

- 예를 들어, 입력시 입력값과 input 이벤트를 사용해 값을 표시한다고 했을때, 
- v-bind:value 와 v-on:input 를 합친 의미라고 생각하면 된다. 

### v-model 과 ref

- 같은정

  - 둘다 데이터를 바인딩 할 때 사용한다. 

- 차이점 

  - v-model은 자동으로 데이터 타입을 변환해 준다. 
  - ref를 사용하면 자동 데이터 타입 변환이 없어 숫자를 쓰더라도 문자로 인식한다. 

- 예시

  ```vue
  <template>
  	<form>
          <label> input a </label>
          <input type="number" v-model="userAge" ref="ageInput">
          <label> input b </label>
          <input type="text" v-model="userAge" ref="ageInput">
      </form>
  </template>
  
  <script>
  export default{
      data(){
          return {
              userAge : 10,
          }
      }
      methods : {
      	submitForm(){
              console.log(this.userAge + 5); //  input type 이 number라면 숫자로 계산해서 (15) 가됨 , text라면 문자로 인식 함
              console.log(this.$refs.ageInput.value + 5); // type에 상관없이 ref는 모두 문자로 인식해 (10+5) 가 된다. 
          }
  	}
  }
  </script>
  ```

  