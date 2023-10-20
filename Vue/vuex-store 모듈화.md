## Vue의 Store 모듈화

---

>

## Store 모듈화

### 사용 이유 

- Store 에서 관리해야할 값들이 많아짐에 따라 모듈화 관리가 필요해졌다. 
- 각각의 기능별로 state, getter, mutations, actions 들을 모듈화하여 편리하게 관리할 수 있다. 

### 주의 사항

1. 지역변수, 전역변수 
   - 모듈화를 하면 모듈별로 지역변수처럼 관리되기 때문에 사용시 유의해서 사용해야 한다. 
   - 즉, numberModules 안에 State로 counter 가 있다고 한다면, loginModules 에서 counter의 상태를 직접 변경할 수 없다.  
   - 대부분 모듈안에 있는 상태만 접근해 관리하지만 특수한 경우 getters 의 메소드 매개변수에 `rootState, rootGetter` 를 추가로 받아 접근할 수 있다. 

### 사용방법

```js
// main.js 
import { createApp } from './App.vue';
import { createStore } from 'vuex';

const numberModules = {
    state() {},
    mutations : {},
    getters : {},
    actions : {}
}

const loginModules = {
    state() {},
    mutations : {},
    getters : {},
    actions : {}
}


const store = createStore({ // 전역 저장소 선언 
	modules : {
        numbers : numberModules,
        ligins : loginModules, 
    }
})
```



## 모듈화의 네임스페이스

### 사용 이유

- 모듈화했을 경우, store 에서 **중복되는 메소드명**이나 state명이 존재할 수 있다. 
- 이때, 모듈들을 **namespace화 하여 관리**해 호출할 수 있다. 

### 주의사항

1. 메소드 호출 이름 변경
   - namespace 를 사용하게되면 **getter와 actions의 메소드 호출 이름이 변경되야 한다**. 
   - store의 옵션인 `namespace : true` 를 이용해 vuex에게 네임스페이스로 관리한다고 알린다. 

### 사용법

```js
// main.js 
import { createApp } from './App.vue';
import { createStore } from 'vuex';

const numberModules = {
    state() {},
    mutations : {},
    getters : {},
    actions : {}
}

const loginModules = {
    state() {},
    mutations : {},
    getters : {},
    actions : {}
}


const store = createStore({ // 전역 저장소 선언 
	namespace : true; // 네임스페이스별로 관리한다고 vuex에  알린다. 
	modules : {
        numbers : numberModules,
        ligins : loginModules, 
    }
})
```

```vue
<!-- 컴포넌트 -->
<script>
export default {
    methods : {
        // 1. 기본 사용법
        addCounter(){
            // 1. 네임스페이스 사용 전 호출 방법
            this.$store.getters.finalCounter;
            this.$store.commit('finalCounter');
            this.$store.dispatch({
                type : 'increment',
                value : 10,
            })

            // 2. 네임스페이스 사용 후 호출 방법
            this.$store.getters['numbesr/finalCounter'];
            this.$store.commit('numbers/finalCounter');
            this.$store.dispatch({
                type : 'numbers/increment',
                value : 10,
            })
    	}
    }
    
    // 2. maphelper 사용법
    // 네임스페이스 사용 전 호출 방법
    ...mapActions({
    	inc : 'increment',
    	fc : 'finalCounter',
	})
    
    // 네임스페이스 사용 후 호출 방법
    ...mapActions('numbers',{
    	inc : 'increment',
    	fc : 'finalCounter',
	})
    
}
</script>
```

