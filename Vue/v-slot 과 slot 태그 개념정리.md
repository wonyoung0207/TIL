## vue의 slot

---

> [참고 사이트1](https://v2.ko.vuejs.org/v2/guide/components-slots.html#%EC%8A%AC%EB%A1%AF%EC%97%90-%EB%93%A4%EC%96%B4%EA%B0%80%EB%8A%94-%EB%82%B4%EC%9A%A9-Slot-Content)

### 정의

- 부모 컴포넌트에서 자식 컴포넌트에게 컨텐츠를 전달하거나 재사용 가능한 템플릿을 정의하는 데 사용
  - 컨텐츠를 전달하거나 템플릿을 정의할 수 있다. 
  - 따라서 다른곳에서 \<slot name="abc"> 태그로 내용을 전달할 수 있다. 

### 방법

1. 컨텐츠를 받을 \<slot> 태그를 만든다. 

   ```vue
   <div class="container">
     <header>
       <slot name="header"></slot>
     </header>
     <main>
       <slot></slot> <!--default로 간주한다.  -->   
     </main>
     <footer>
       <slot name="footer"></slot>
     </footer>
   </div>
   ```

2. 전달할 컨텐츠를 생성한 후 v-slot으로 연결해준다. 

   ```vue
   <base-layout>
     <template v-slot:header>
       <h1>Here might be a page title</h1>
     </template>
   
     <template v-slot:default>
       <p>A paragraph for the main content.</p>
       <p>And another one.</p>
     </template>
   
     <template v-slot:footer>
       <p>Here's some contact info</p>
     </template>
   </base-layout>
   ```

3. 결과

   ```vue
   <div class="container">
     <header>
       <h1>Here might be a page title</h1>
     </header>
     <main>
       <p>A paragraph for the main content.</p>
       <p>And another one.</p>
     </main>
     <footer>
       <p>Here's some contact info</p>
     </footer>
   </div>
   ```

### 축약어

- ` v-slot : ` 을  `#` 으로 줄여 사용할 수 있다 .

  ```vue
  <div v-slot:"NyHeader"></div>
  <div #NyHeader></div>
  ```

### $slots

- $slots.MyHeader  로 해당 slot이 사용되는지 확인할 수 있다. 
