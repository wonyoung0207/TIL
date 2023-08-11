# Vue js 규칙

---

## 규칙

### 1. root 태그인 App.vue는 **싱글 컴포넌트** 이다. 

   - root태그의 template 태그안는 하나의 태그 밑으로 구현해야한다

     ```VUE
     <template>
     	<div>
             /* 여기 안에 하위 태그 구현해야 함  */
         </div>
     	<div>
             // 에러남. root의 template 태그는 단일태그로 이루어져 있어야한다. 
         </div>
     </template>
     ```

### 2. 컴포넌트 이름은 2단어 이상의 **`파스칼 케이스`** 로 만든다. 

   - 컴포넌트와 기존 사용되는 태그를 구분하기 위해서 이렇게 쓴다. 

     ```vue
     <main> </main> 
     <MyMain> </MyMain>
     태그가 컴포넌트인지 아니면 기존 html 제공 태그인지 구분하기 위해 사용한다. 
     ```
     
   - 컴포넌트의 이름은 2단어로 사용하는것이 권장된다. 

     - 따라서 한단어 컴포넌트의 경우에는 이름에 'The' 를 붙인다. 

     1. TheHeading.vue
     2. TheSidebar.vue

### 3. import 할 경우 **파일 확장자**까지 써주는것이 좋다. 

   - 확장자인 `html, vue` 를 써주지 않으면 깨질수도 있기 때문에 써주는것이 좋다. 

     ```javascript
     // 안좋은 예시 
     import AppHeader from './components/AppHeader'
     // 좋은 예시
     import AppHeader from './components/AppHeader.vue'
     ```

### 4. 컴포넌트 단위 개발에서의 data 속성 

   - 하나의 html 파일로 vue를 사용헀을 때는 `data : { }` 의 형태를 사용했었다. -> 객체로 사용 

   - **하지만 컴포넌트 단위**로 vue 를 개발할때는 **`data : function() { } `** 의 형태를 사용한다. 

   - 왜냐하면 **data를 객체**로 사용하게 되면 **하위의 컴포넌트**에서 **참조**하여 해당 **data를 공유및 사용**할 수 있게 된다. 

     - 따라서 해당 컴포넌트 안에서만 사용할 수 있도록 함수화 시켜줘야한다. 

     ```vue
     <script> 
         // 객체 기반 개발에서의 data 사용 
     	new Vue({
             data : { }
         })
         
         // 컴포넌트 단위 개발에서의 data 사용 
         export default {
             data : function (){
                 return ;
             }
         }
     </script>
     ```

### 5. `{{     }}`을 사용한다. 

   - 해당 문법을 사용해 **vue와 바인딩**하여 값을 표시할 수 있다. 
   - 이때 `{{  }}` 안에는 간단한 javascript ( 삼항연산자 정도 )와 **vue의 인스턴스인 data, methods의 값**들이 들어갈 수 있다. 
   - 생각할점은 javascript 에 어떤 내용을 넣을 것이냐이다. 
     - **html태그**는 **화면만 신경**쓰고 값의 변화같은 **복잡한 로직**은 **JavaScript**에서 처리하는게 좋은 방법이다. 

### 6. 표기법

   - HTML 태그의 옵션에서 props 로 넘겨줄때는 케밥 케이스를 사용해 넘겨주고, 

     - vue의 props 컴포넌트에서 받을때는 카멜 케이스를 사용한다. 

     - **vue가 자동으로 html의 props값인`케밥 케이스`의 변수 형태를 `카멜 케이스` 로 인식해 props 에 데이터를 전달한다.** 

       ```html
       // 케밥 케이스 
       <friend-contect 
       	name ='wy' 
           phone-number='010-1111-1111'
           email-address='abc@naver.com'
       >
       </friend-contect>
       
       // 카멜 케이스 
       export default{
       	props : ['name', 'phoneNumber', 'emailAddress'],
       	data(){
       		...
       	}
       }
       ```

### 7. 단방향 데이터 플로우

   - 상위 컴포넌트에서 하위 컴포넌트로 넘겨준 데이터의 값은 상위 컴포넌트에서밖에 변경할 수 없다. 
     - 즉, 하위 컴포넌트에서 props로 받아온 데이터는 화면 표시만 가능하지 변경할 수 없다. 
   - 변경방법
     1. 부모에게 변경하고싶은 값을 전달. 
     2. 상위에서 전달받은 값을 다른 변수에 저장해서 화면 및 데이터 변경에 사용하기. 

### 8. **셀프 클로징** 동작 

   - 컴포넌트 이름 사이에 ' - ' 가 있다면(케밥 케이스 표기법) 셀프 클로징을 사용할 수 없다. 

   - 따라서 파스칼 케이스에서는 사용가능 하지만, 케밥 케이스에서는 사용불가이다. 

     - vue에서는 대부분 파스칼 케이스로 많이 사용한다. 

   - 예시

     ```vue
     <TheHeader /> <!-- 셀프 클로징 사용 -->
     <the-header> </the-header>  <!-- 셀프 클로징 사용 불가 -->
     ```

### 9. vue는 HTML  태그 이름 **대소문자 구별 안함** 

- 태그 이름에서 대소문자를 구별하지 않는다. 
- 따라서 `<the-header>`, `<TheHeader>`, 그리고 `<THEHEADER>`은 모두 동일하게 동작한다. 

### 10. 컴포넌트의 이름 사용법

- 하지만 **컴포넌트를import할 때는 파스칼 케이스**로 가져오되, vue의 t**emplate 안에서 html 태그**로써 컴포넌트를 사용할 때는 **'케밥 케이스 '** 를 사용하는것이 **권장**된다. 
- 따라서 **파스칼 케이스로 import 해서** 컴포넌트에 등록해 놓으면, vue는 해당 **컴포넌트 이름**을 자동으로 '**케밥 케이스' 로도 인식**할 수 있다. 

```vue
// 다른 Vue 파일 (예: App.vue)
<template>
  <div>
    <!-- 케밥 케이스  -->
    <!-- vue가 파스칼 케이스로 등록해놓은 컴포넌트 이름을 자동으로 케밥 케이스로 인식할 수 있음 -->
    <the-header></the-header> 
  </div>
</template>

<script>
import TheHeader from './TheHeader.vue';

export default {
  components: {
    TheHeader // 파스칼 케이스 
  },
  // 다른 내용
}
</script>
```


​      





