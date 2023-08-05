# component 태그

---

>

## component 태그 

### 정의

- 동적으로 컴포넌트를 렌더링하기 위해 사용되는 특별한 태그로,  **다양한 컴포넌트**를 조건에 따라 **동적으로 변경**하거나 교체할 수 있다. 

  - 따라서 해당 태그의 **is 옵션**을 이용해 컴포넌트를 손쉽게 사용할 수 있다. 

    ```html
    <component :is="selectedComponent"> </component>
    ```

### 사용방법 

1. 조건부 렌더링 

   ```html
   <template>
     <div>
       <component :is="currentComponent"></component>
       <button @click="toggleComponent">Toggle Component</button>
     </div>
   </template>
   
   <script>
   import FirstComponent from './FirstComponent.vue';
   import SecondComponent from './SecondComponent.vue';
   
   export default {
     data() {
       return {
         currentComponent: 'FirstComponent',
       };
     },
     components: {
       FirstComponent,
       SecondComponent,
     },
     methods: {
       toggleComponent() {
         // 현재 컴포넌트를 토글하여 다른 컴포넌트로 변경
         this.currentComponent =
           this.currentComponent === 'FirstComponent'
             ? 'SecondComponent'
             : 'FirstComponent';
       },
     },
   };
   </script>
   ```

2. 배열 이용 동적 컴포넌트 생성 

   ```html
   <template>
     <div>
       <component v-for="componentName in componentNames" :key="componentName" :is="componentName"></component>
     </div>
   </template>
   
   <script>
   import FirstComponent from './FirstComponent.vue';
   import SecondComponent from './SecondComponent.vue';
   
   export default {
     data() {
       return {
         componentNames: ['FirstComponent', 'SecondComponent'],
       };
     },
     components: {
       FirstComponent,
       SecondComponent,
     },
   };
   </script>
   ```

   