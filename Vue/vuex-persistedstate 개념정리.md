# vuex-persistedstate 개념정리

---

>[참고 사이트1](https://light9639.tistory.com/entry/Vuex-persistedstate-%EC%82%AC%EC%9A%A9%EB%B2%95)
>
>[참고 사이트2](https://kyounghwan01.github.io/blog/Vue/vuex/vuex-persistedstate/#vuex-persistedstate-%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%87%E1%85%AE%E1%84%86%E1%85%A1%E1%86%AB-%E1%84%8C%E1%85%A5%E1%84%8C%E1%85%A1%E1%86%BC)
>
>[vuex-persistedstate 제공 git](https://github.com/robinvdvleuten/vuex-persistedstate#readme)

## vuex-persistedstate

### 정의

- **vuex**의 자료들을 **localstorage 로 자동 저장** 시키고 다시 값을 사용할 수 있게 해주는 **플러그인**이다. 
- 즉, vuex-store 에 저장한 값을 localStorage에 저장해놨다가 사용한다. 

### 사용이유

- vuex-store 를 사용할때, 화면을 refresh 하게 되면 vuex의 상태중 변경된 모든 값들이 초기화 된다. 
  - **vuex의 특징**인거같음. ( **주의! vue 특징 아님** )
- 따라서 이때 **변경된 상태들을 기억**해 다시 사용할 수 있게 도와주는 플러그인이라고 생각하며 된다. 

### 사용 방법 종류

1. 가장 쉬운 방법 
   - **localStorage**에 저장한다. 
2. cookie에 저장 
   - 값을 **cookie**에 저장한다. 
3. secure-js 이용 저장 
   - 값을 **암호화**해 **localStorage**에 저장한다. 

### 기본적인 사용방법

1. 플러그인 설치 

   ```terminal
   $ npm install --save vuex-persistedstate
   # or
   $ yarn add vuex-persistedstate
   ```

2. vuex-store에 해당 플러그인 추가 

   ```js
   import Vuex from "vuex";
   import createPersistedState from 'vuex-persistedstate';
   import Cookies from 'js-cookie'; // 방법2 를 사용하기 위해 필요 
   
   // 방법3을 사용하기 위해 필요
   import SecureLS from "secure-ls";
   const ls = new SecureLS({ isCompression: false });
   
   export default new Vuex.Store({
       modules: { // 모듈로 store 관리 
           app,
           appConfig,
           settingConfig,
           userData,
       },
       plugins: [
           // 방법1-1. 모듈로 선언한 전체 store 를 localStorage에 저장 -> 속도 느려질 수 있음. 
           createPersistedState();	
   
           // 방법1-2. 원하는 store모듈만 localstorage에 저장 -> 이 방법이 더 좋은 방법임. 
           createPersistedState({
               // paths 에 넣어준 module만 localstorage에 저장되어 관리된다. 
               paths: ["settingConfig", "userData"],
           }),
   
           // 방법2. cookie에 저장하는 방법 
           storage: {
             getItem: key => Cookies.get(key),
             setItem: (key, value) => Cookies.set(key, value, { expires: 3, secure: true }),
             removeItem: key => Cookies.remove(key)
           }
   
   		// 방법3. localStorage에 암호화된 데이터로 저장하는 방법 
   		storage: {
               getItem: key => ls.get(key),
               setItem: (key, value) => ls.set(key, value),
               removeItem: key => ls.remove(key)
           }	
       ],
   });
   ```

3. localStorage에 저장된것을 확인 

   - localStorage의 vuex에 store 값이 저장된것을 확인할 수 있다. 



---

## vuex-persistedstate 문제 발생

> [해결 방안](https://github.com/robinvdvleuten/vuex-persistedstate/issues/88)

### 문제점

- **vuex-persistedstate 로 store를 localStorage에서 관리**하면 , 애플리케이션 즉, 프론트가 꺼지기 전까지 persistedstate **라이브러리가 localStorage에 저장한 값을 기억**하고 있다. 
  - localStorage에서 **vuex** 로 관리하고 있는데, 해당 **vuex 를 removeItem 해도 남아있음**. 
- 따라서 이것을 **아예 지워주기** 위해 `createPersistedState` 의 `**reducer**` 메소드를 이용해 **로그인시에만 localStorage로 관리**하고 logout이나 새로 접속한 경우에는 **vuex를 `{}` 로 초기화**했다. 

### 벙법

```js
export default new Vuex.Store({
  modules: {
    app,
    appConfig,
    verticalMenu,
    commonCodeStore,
    settingConfig,
    equipInterfaceStore,
    userData,
  },
  plugins: [
    createPersistedState({
      // <쉬운방법>
      // localStorage에서 관리하고싶은 store Module
      paths: ["settingConfig", "userData"],

	   // <업그레이드 방법>
       // login이 허락됐을때만 vuex-persistedstate가 localStorage로 관리할 수 있게 함 
      reducer(state) {
        // state => store Modules 
        // authentication = ture 인 경우가 로그인 성공
        if (
          state.userData.userData.authentication === false ||
          state.userData.userData.authentication === null
        ) {
          return {};
        }
        return {
          // localStorage에서 관리하고싶은 store Module
          settingConfig: state.settingConfig,
          userData: state.userData,
        };
      },
    }),
  ],
  strict: process.env.DEV,
});

```

```js
computed : {
    userId(){
      return this.$store.getters['userData/GET_USER_DATA'].userId;
      // return  this.$store.getters['userData/getUserState']('userId');
    },
    userNm(){
      return this.$store.getters['userData/GET_USER_DATA'].userNm
      // return  this.$store.getters['userData/getUserState']('userNm');
    },
}
```

