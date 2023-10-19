# store 사용법

---

1. ### store 생성

   ```js
   // Store.js
   import { setCodeToSelectBoxItems } from "@/@core/utils/utils";
   
   export default {
     namespaced: true,
     state: {
       // 변수
       sbDirection: null, // 방면
       sbEqpmntSe: null, // 장비 종류
     },
     getters: {
       // 변수 get
       getSbDirection(state) {
         return state.sbDirection;
       },
       getSbEqpmntSe(state) {
         return state.sbEqpmntSe;
       },
     },
     mutations: {
       //변수 set
       setSbDirection(state, val) {
         state.sbDirection = val;
       },
       setSbEqpmntSe(state, val) {
         state.sbEqpmntSe = val;
       },
     },
     actions: {
       // 변수 set 처리 전 데이터 가공
       setSbDirection(context) {
         setCodeToSelectBoxItems("001", 0).then((result) => {
           context.commit("setSbDirection", result);
         });
       },
       setSbEqpmntSe(context) {
         setCodeToSelectBoxItems("003", 0).then((result) => {
           context.commit("setSbEqpmntSe", result);
         });
       },
       },
     },
   };
   ```

2. ### action 호출

   ```js
   // vue 컴포넌트의 created 
   async created(){
       // 값 세팅 
       this.$store.dispatch("equipInterfaceStore/setSbDirection");
       this.$store.dispatch("equipInterfaceStore/setSbEqpmntSe");
       this.refreshFunction();
   }
   ```

3. ### getter 호출 

   ```vue
   <!-- vue 컴포넌트의 태그 -->
   <b-button
       v-for="item in $store.getters['equipInterfaceStore/sbSrchDirection']"
       :key="item.data"
       v-model="searchSeType"
       @click="searchSeType = item.data"
       variant="outline-primary"
       :pressed="searchSeType === item.data"
       style="width: 90.16px"
   >
   ```

   



---

### store get/set 반복 줄이는 방법

```js
// 로그인 사용자 데이터 
export default {
  namespaced: true,
  state: {
    userId: null,
    siteId: null,
    userNm: null,
    authorId: null,
    role: null,
    ability: null,
    extras: null,
  },
  getters: {
    // 동적 속성을 사용하여 여러 상태의 값을 가져오기
    getUserState: (state) => (key) => {
      return state[key];
    },
  },
  mutations: {
    // 동적 속성을 사용하여 여러 상태를 한 번에 업데이트
    setUserState(state, payload) {
      state[payload.key] = payload.value;
    },
  },
  actions: {},
};
```

### store get/set 호출

```js
this.$store.getters['userData/getUserState']('userId'));
```

