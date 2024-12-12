# vue3 ref에 따른 value 처리조건

---

>

##  ref의 기본 원리

1. `ref`는 Vue 3에서 **반응형 데이터를 생성**할 때 사용된다. 

   1. `ref`로 생성된 변수는 항상 `value` 속성 안에 실제 값이 저장된다. 
   2. 이를 통해 Vue가 내부적으로 값의 변화를 추적하고 반응형 시스템을 업데이트된다. 

   ```js
   const count = ref(0);
   console.log(count); // { value: 0 }
   ```

   

## `.value`를 사용해야 하는 경우

1. composition API 내부에서 `ref`를 직접 사용할 때

   1. `ref`로 생성된 변수를 사용할 때는 **`.value`**를 통해 실제 값을 참조하거나 수정해야 한다. 

   ```js
   import { ref } from 'vue';
   
   const count = ref(0);
   
   // 값을 읽을 때
   console.log(count.value); // 0
   
   // 값을 수정할 때
   count.value = 10;
   console.log(count.value); // 10
   ```

## `.value`를 사용하지 않아도 되는 경우

1. vue 템플릿에서 사용할 때 

   1. Vue의 **템플릿(template)** 내에서는 `.value`를 생략할 수 있다. 
   2. 왜냐하면 Vue가 자동으로 `ref` 객체의 `value`를 접근하도록 처리한다. 

   ```vue
   <template>
     <div>
       <p>Count: {{ count }}</p> <!-- .value 생략 가능 -->
       <button @click="count++">Increase</button>
     </div>
   </template>
   
   <script lang="ts">
   import { ref } from 'vue';
   
   export default {
     setup() {
       const count = ref(0);
       return { count };
     },
   };
   </script>
   ```

   

## `reactive`와의 차이

1. `ref`는 단일 값에 반응성을 추가하는 데 사용되고, `reactive`는 객체 또는 배열처럼 복잡한 구조에 반응성을 추가한다. 
2. reactive는 `.value` 불필요하다. 

```js
// reactive 사용 예시
import { reactive } from 'vue';

const state = reactive({ count: 0 });

// 값을 읽거나 수정할 때
console.log(state.count); // 0
state.count = 10;
console.log(state.count); // 10

```

```js
// ref 사용 예시
import { ref } from 'vue';

const count = ref(0);

// 값을 읽거나 수정할 때
console.log(count.value); // 0
count.value = 10;
console.log(count.value); // 10
```

