# 이벤트 수식어 Event Modifier 정리 

---

## Event Modifier

### 정의

- 이벤트 수식어(Event Modifier)를 사용하여 **이벤트의 동작 방식을 조정**하는 것 

### 사용법

- 이벤트 핸들러 바로 뒤에 점으로 구분하여 사용한다. 
- 각 수식어는 특정 동작을 정의하며, 사용자의 행동에 따라 해당 이벤트를 어떻게 처리할지 결정할 수 있다. 

### 종류

1. .stop

   - **이벤트 전파를 중단**시킨다. 

     - 버블링과 캡처링을 막아 부모 요소나 자식 요소로의 이벤트 전파를 막는다. 

     ```html
     <div @click="parentClick">
     	<button @click.stop="childClick">Click Me</button>
     </div>
     ```

2. **.prevent**

   - **기본 동작**을 중단시킨다. 

     - **event.preventDeafult()** 와 같은 의미를 가진다. 

   - 예를 들어, `<a>` 태그의 클릭 **기본 동작**인 **페이지 이동**을 막을 수 있다. 

     ```html
     <template>
       <a href="https://www.google.com" @click.prevent>Google</a>
       <a href="https://www.google.com" @click="myEvent(event)">Google</a>
     </template>
     <script>
     	// event.prevent 와 같은 동작인 event.preventDefault() 구현 
         myEvent(){
     	    event.preventDeafult()        
         }
     </script>
     ```

3. .left  &  .right 

   - 해당 수식어는 click 이벤트에서만사용할 수 있다. 

   - 마우스 왼쪽과 오른쪽으로 동작이 들어왔을 떄만 동작하게 할 수있게만든다. 

     ```html
     <div v-on:click.left="leftBtn">
     	<button v-on:click.left="rightBtn">Click Me</button>
     </div>
     ```

4. .enter  

   - 엔터키가 눌렸을 때 동작한다. 

     ```html
     <div v-on:keyUp.enter="leftBtn">
     </div>
     ```

5. .self

   - 이벤트가 요소 자체에서 발생했을 때에만 이벤트 핸들러를 호출한다. 

   - 따라서 자식 요소에서 발생한 이벤트는 무시한다. 

     ```html
     <div @click.self="divClick">
     	<button @click="buttonClick">Click Me</button>
     </div>
     ```

6. .capture

   - 캡처링 모드를 사용하여 이벤트를 처리한다. 

   - 이벤트가 내부 요소로 전파되기 전에 먼저 상위 요소에서 처리한다. 

     ```html
     <div @click.capture="parentClick">
     	<button @click="childClick">Click Me</button>
     </div>
     ```

7. .once

   - 이벤트 핸들러가 한 번만 실행되도록 보장한다. 

     ```html
     <template>
       <button @click.once="clickOnce">Click Me Once</button>
     </template>
     ```

