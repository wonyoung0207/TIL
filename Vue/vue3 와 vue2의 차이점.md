# vue3 와 vue2의 차이점

---

>

## Vue3 개선된점

1. Vue 2와 비교해 성능, 코드 구조, 유연성 측면에서 많은 개선이 이루어졌다. 

## Vue3 주요 차이점 

#### 1. Composition API 도입

1. 기존 Options API(예: `data`, `methods`, `computed` 등) 대신 함수형으로 로직을 구성하여 컴포넌트를 구성할 수 있다.

2. 재사용성과 가독성이 향상되어 복잡한 로직을 가진 컴포넌트에서 큰 장점을 가진다. 

   ```javascript
   import { ref } from 'vue';
   
   export default {
     setup() { // composition API 시작점
       const count = ref(0);
       function increment() {
         count.value++;
       }
       return { count, increment };
     }
   };
   ```

#### 2. Fragments 지원

1. Vue 2에서는 컴포넌트에 하나의 루트 요소가 필요했지만, 

2. Vue 3에서는 Fragment가 지원되어, 여러 요소를 **루트 요소 없이 반환**할 수 있게 되었다. 

   ```html
   <!-- Vue 2 -->
   <template>
     <div> <!-- 루트 요소 무조건 필요 -->
       <h1>Title</h1>
       <p>Some content</p>
     </div>
   </template>
   
   <!-- Vue 3 -->
   <template>
     <h1>Title</h1>
     <p>Some content</p>
   </template>
   ```

#### 3. Teleport 사용가능

1. 모달이나 툴팁을 HTML 구조 내 원하는 위치로 이동시킬 수 있는 기능이다. 

2. 하지만 파악하기 어렵고 유지보수 또한 어렵기 때문에 사용을 지양하는 것이 좋다. 

   ```vue
   <template>
     <button @click="showModal = true">Open Modal</button>
     <teleport to="body"> <!-- Teleport 로 DOM의 Body 부분으로 이동  -->
       <div v-if="showModal" class="modal">
         <p>This is a modal</p>
         <button @click="showModal = false">Close</button>
       </div>
     </teleport>
   </template>
   
   <script>
   import { ref } from 'vue';
   
   export default {
     setup() {
       const showModal = ref(false);
       return { showModal };
     }
   };
   </script>
   ```

#### 4. 변경된 라이프사이클 훅

1. Vue 3에서는 Composition API와 함께 사용할 수 있는 새로운 라이프사이클 훅이 도입되었다. 

   ```javascript
   import { onMounted, onUnmounted } from 'vue';
   
   export default {
     setup() {
       onMounted(() => {
         console.log('컴포넌트가 마운트되었습니다.');
       });
   
       onUnmounted(() => {
         console.log('컴포넌트가 언마운트되었습니다.');
       });
     }
   };
   ```

#### 5. v-model 개선

1. Vue 2에서는 `v-model`을 단일로 사용하며 기본적으로 `value`와 `input`을 사용헀지만,

2. Vue 3에서는 복수의 `v-model`을 사용할 수 있게되었다. 

   ```vue
   <!--  vue 2 -->
   <my-component v-model="value"></my-component>
   
   <!--  vue 3 -->
   <my-component v-model:title="title" v-model:content="content"></my-component>
   
   <script>
   import { ref } from 'vue';
   
   export default {
     setup() {
       const title = ref('제목');
       const content = ref('내용');
       return { title, content };
     }
   };
   </script>
   ```

#### 6. Proxy 개념추가

1.  JavaScript의 객체와 관련된 메타프로그래밍 기능 중 하나로, **객체의 기본적인 동작**(속성 접근, 할당, 삭제 등)을 **가로채고 제어**할 수 있도록 해주는 기능이다. 

2. `Proxy` 요소

   1. Target
      1. `Proxy`가 감싸서 **감시할 실제 객체**
      2. 즉, 객체의 **값**
   2. Handler
      1. 객체에 접근할 때 수행할 동작을 정의한 객체로, 다양한 **트랩(trap) 함수**를 정의한다. 
      2. 즉, **객체를 컨트롤**

3. 트랩함수 종류

   1. **get**(target, prop, receiver)
      1. 속성에 접근할 때 호출
   2. **set**(target, prop, value, receiver)
      1. 속성에 값을 할당할 때 호출
   3. **has**(target, prop)
      1. `in` 연산자로 속성 존재 여부를 확인할 때 호출
   4. **deleteProperty**(target, prop)
      1. `delete` 연산자로 속성을 삭제할 때 호출
   5. **apply(target, thisArg, args)** 트랩
      1. 함수 객체가 호출될 때 실행 
      2. 함수 호출 시 추가 동작을 수행할때 사용한다. 
   6. **construct(target, args)** 
      1. `new` 키워드로 생성자가 호출될 때 실행
      2. 객체 생성 시 추가 동작을 수행할때 사용한다. 

4. 기본 사용법

   ```js
   const target = { message: 'Hello World' };
   const handler = {
     get: function(obj, prop) { // get 트랩은 target 객체의 프로퍼티에 접근할 때마다 실행
   	return prop in obj ? obj[prop] : 'Property does not exist'; 
     }, 
     set(target, prop, value) {
   	return true
     },
     has(target, prop) {
   	return prop in target;
     },
     deleteProperty(target, prop) {
   	delete target[prop];
   	return true;
     }
   };
   
   // proxy.message는 target.message 값을 반환
   // proxy.nonExistent처럼 없는 프로퍼티에 접근하면 'Property does not exist'를 반환
   const proxy = new Proxy(target, handler);
   
   console.log(proxy.message); // 'Hello World' 출력
   console.log(proxy.nonExistent); // 'Property does not exist' 출력
   ```

5. Proxy 통한 반응성 구현

   1. `ref`나 `reactive`로 만든 변수들은 Proxy로 감싸져, 데이터가 변경되었을 때 Vue가 알아차리고 UI를 자동으로 업데이트한다. 
   2. 즉, Vue 3에서 **`reactive`로 만든 객체는 내부적으로 Proxy로 감싸져서, 속성이 변경되면 Vue가 반응**한다. 

   ```js
   import { reactive } from 'vue';
   
   const state = reactive({
     count: 0
   });
   
   // `count` 속성에 접근하거나 값을 변경하면 Proxy를 통해 Vue가 자동으로 반응함
   state.count++;
   ```

   