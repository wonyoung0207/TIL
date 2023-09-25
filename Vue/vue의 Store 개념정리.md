# Vuex 의 Store(저장소) 기능

---

>[Vue - Store Docs](https://vuex.vuejs.org/guide/mutations.html#committing-mutations-in-components)

## Store

### 정의

- vuex에서 제공하는 **전역 저장소** 기능 이다. 
  - 애플리케이션에서 상태 관리 패턴을 지원하는 라이브러리
- 해당 컴포넌트 안에 데이터를 저장해놓고, 어떤 컴포넌트 에서든 데이터를 가져와 사용할 수 있다. 

### 사용 이유

- 컴포넌트 간 데이터를 공유하기 위해서는 props 나 (Provide, Inject) 를 사용했어야 했다. 
  - 해당 기능들을 사용하면 컴포넌트 간 연결점이 생겨 데이터를 관리하기 어렵다. 
- 이걸 사용하기 쉽게 해주는 것이 vuex의 Store 기능이다. 

### 주요 개념

<img src="./images/vuex의 Store.png" width="500">

1. **State** ( 상태 )
   - 전역 변수로 사용될 변수값
2. **mutation** ( 변이 )
   - 상태값을 변경하는 함수 같은존재 
   - Store의 상태 값은 Mutation(변의) 애 의해서만 변경되는 것이 좋다. 

### State

- Store의 핵심 요소로, 애플리케이션에서 관리해야할 중요한 **데이터**이다. 
- 즉, **전역으로 사용하고자 할 데이터**가 State에 들어간다. 

#### 사용법

```js
// main.js 
import { createApp } from './App.vue';
import { createStore } from 'vuex';

const store = createStore({ // 전역 저장소 선언 
    // 방법1
	state(){
		return {
			counter : 0 // 전역으로 사용할 변수 
		}
	}
    
    // 방법2
    state : {
    	list : [
    		{todo : "영봐보기" , done : "false"},
             {todo : "주말 산책 " , done : "false"},
    	]
	}
})

const app = createApp(App);

app.use(store); // store 사용할 수 있도록 app에 추가 

app.mount('#app');
```

```vue
<template>
    <component1>
	<h3>
        {{ $store.state.counter }}
    </h3>
    <button @onClick="addOne">
        addButton
    </button>
    </component1>
</template>

<script>
import Component1 from './components/component1.vue';

export default {
    methods : {
        addOne(){
            this.$store.state.counter++;
        }
    },
    computed : {
        counter(){
            return this.$store.state.counter;
        }
    }
    components : {
        Component1
    }
}
</script>
```

### Mutation

- 저장소의 핵심 요소로, **상태를 변경하는 함수**들을 보유하고 있는 객체이다. 
  - 즉, **저장소의 데이터들을 일정하게 변형하고 싶을 때 사용**되는 객체이다. 
- Store의 상태 값은 Mutation(변의) 애 의해서만 변경되는 것이 좋다. 

#### 사용법

```js
// main.js 
import { createApp } from './App.vue';
import { createStore } from 'vuex';

const store = createStore({ // 전역 저장소 선언 
	state(){
		return {
			counter : 0 // 전역으로 사용할 변수 
		}
	},
    mutations : {
        // 방법1
        increment(state) {
            state.counter = state.counter + 2;
        },
        
        // 방법2
        increment(state, payload){
    		state.counter = state.counter + payload.value;
		}
    }
})

const app = createApp(App);

app.use(store); // store 사용할 수 있도록 app에 추가 

app.mount('#app');
```

```vue
<template>
    <component1>
	<h3>
        {{ $store.state.counter }}
    </h3>
    <button @onClick="addOne">
        addButton
    </button>
    </component1>
</template>

<script>
import Component1 from './components/component1.vue';

export default {
    methods : {
        addOne(){
            // 방법1
            this.$store.commit('increment', {value : 10}); // commit 을 통해 mutations의 함수를 호출할 수 있다.
            
            // 방법2
            this.$store.commit({
                type : 'increment',
                value : 10,
            })
        }
    },
}
</script>
```
