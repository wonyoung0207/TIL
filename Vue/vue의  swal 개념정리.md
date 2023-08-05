# swal 개념정리

---

>

## swal

### 정의

- Vue.js와 SweetAlert2 라이브러리를 함께 사용할 때, SweetAlert2의 경량화된 버전인 **vue-sweetalert2에서 제공**하는 **`$swal` 객체**를 가리킨다.
  -  Vue.js에서 쉽게 사용하기 위해 만들어진 라이브러리로, `$swal` 객체를 Vue 인스턴스에 자동으로 추가해주어서 편리하게 SweetAlert2를 사용할 수 있게 도와준다. 

- **따라서 vue-sweetalert2 에서 제공하는 alert 창으로,  javascript 라이브러리라고 생각하면 된다.** 

### 호출 방법

1. vue-sweetalert2 라이브러리를 설치

2. Vue 인스턴스에 vue-sweetalert2 플러그인을 등록

   - package.json에 추가한다. 

3. vue에 추가한다. 

   ```js
   import Vue from 'vue'
   import VueSweetalert2 from 'vue-sweetalert2'
   
   Vue.use(VueSweetalert2)
   ```

4. 호출로 사용한다. 

   ```js
   async onBtnOk(data) {
         data.preventDefault();
         const success = await this.$refs.simpleRules.validate(); // validation체크 
         if (success) {
           if (!this.validationMileston) { 
             this.$swal({
               heightAuto: false,
               title: "시작이정은 종료이정보다 <br/> 클 수 없습니다. ", // alert 문구 
               icon: "info", // alert에 표시할 icon ( 경고, 확인, 성공 등의 다양한 메시지를 보여줄 수 있다. )
               buttons: true, // 버튼 유무 
               showCancelButton: false, // 취소 버튼 보일지 
               confirmButtonText: "확인", // 확인버튼
               cancelButtonText: "취소", // 취소버튼
               reverseButtons: true, 
               allowEscapeKey: false,
               allowOutsideClick: false,
               customClass: {
                 confirmButton: "btn btn-primary ml-1",
                 cancelButton: "btn btn-outline-danger",
               },
               buttonsStyling: false,
             });
             return "";
           } 
         }
   }
   ```

