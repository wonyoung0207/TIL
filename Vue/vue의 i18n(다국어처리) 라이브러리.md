## vue의 i18n 라이브러리

---

>[참고 사이트1](https://developerjournal.tistory.com/10)
>
>[참고 사이트2](https://dong-queue.tistory.com/64)
>
>[참고 사이트3](https://cpdev.tistory.com/142)

## i18n

### 정의

- vue.js 에서 제공하는 다국어 라이브러리로, this.$t() 메소드를 이용해 다국어 처리를 진행한다. 

### this.$$t( '변경 텍스트', '텍스트에 원하는 변수값 넣기 ' )

- vue에서 사용되는 i18n(다국어 지원) 라이브러리인 Vue I18n 에서 제공하는 메서드 

  - 텍스트를 다국어로 번역할 수 있다. 

- 사용 예시 

  1. 다국어 변환 처리 할 message 생성

     ```js
     // 1. 메시지 등록 객체 형태
     const messages = {
       ko: {
         message: {
           hello: '안녕하세요. {user}님.환영합니다'
         }
       }
     }
     
     // 2. 메시지 등록 json 형태  
     // ko.json 
     // en.json은 한글 부분을 영문으로 번역하면 됨 
     {
         "hello" : "안녕하세요 {}님. 환영합니다. ",
         "textBanana" : "바나나",
         "title" : "타이틀",
         "road" : {
             "roadA" : "로드 에이",
             "roadB" : "로드 비 "
         },
         "login" : {
             "title" : "로그인",
             "errorMsg" : {
                 "emptyId" : "아이디를 입력하세요.",
                 "emptyPass" : "비밀번호를 입력하세요.",
             },
         },
     }
     ```

  2. i18n 을 vue에 등록 

     ```js
     // jsno 파일 형식의 파일 import -> message로 등록
     // i18n에 locale정보가 ko일때는 ko.json파일에서 키를 찾아서 반환하고, en일 경우 en.json에서 파일을 찾아 반환
     import en from '../messages/en.json'
     import ko from '../messages/ko.json'
     
     // 객체 생성 
     // message : json 형식으로 다국어 처리할 값들이 들어간 객체. ( key : value 형식으로 저장된다.  )
     const i18n = new VueI18n({
       locale: 'ko', // 기본 locale
       fallbackLocale: 'ko', // locale 설정 실패시 사용할 locale
       messages, // 1번 방법 이용 ( 다국어 메시지)
       messages : {en, ko} , // 2번 방법 이용 (jsno 형식으로 message 등록 )
       silentTranslationWarn: true // 메시지 코드가 없을때 나오는 console 경고 off
     })
     
     // vue에 등록 
     new Vue({
       i18n, 
       router,
       store,
       render: h => h(App)
     }).$mount('#app')
     ```

  3. 다국어 처리 `$t`  사용 

     ```html
     <!-- message에 등록해놓은 hello 라는 key값에 설정되어있는 text 출력 -->
     <!-- 두번째 매개변수는 text에 설정되어있는 {user} 안에 들어갈 텍스트 값이다. -->
     <p>{{ $t('message.hello', { user: '원영' }) }}</p>
     
     <!-- 바나나 출력  -->
     <div>{{ $t("textBanana") }}</div>
     ```

  4. 출력값 

     1. 안녕하세요 원영님. 환영합니다. 
     2. 바나나 

### `$t("key")`  와 `this.$i18n.t("key") ` 의 사용

1. `$t("key")` 

   - template 태그 안에서 사용된다. 

     ```vue
     <template>
       <v-app>
         <div>{{ $t("textBanana") }}</div>
         <div>{{ $t("textApple") }}</div>
       </v-app>
     </template>
     ```

2. `this.$i18n.t("key") ` 

   - script 내부에서 사용된다. 

     ```html
     <script>
     export default {
       data() {
         return {
           stringBanana: this.$i18n.t("textBanana"),
           stringApple: this.$i18n.t("textApple"),
         };
       },
     };
     </script>
     ```



