# Vuex 의 Store(저장소) 기능

---

>[Vue - Store Docs](https://vuex.vuejs.org/guide/mutations.html#committing-mutations-in-components)
>
>[vuex getter - 캡틴장기효](https://joshua1988.github.io/web-development/vuejs/vuex-getters-mutations/)

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
   - `$store.state.nummber`로 사용한다. 
2. **Getter** 
   - state의 반복되는 호출을 간략화 하기 위해 사용 
   - computed 에서 사용됨 
   - `$store.getter` 를 이용해 호출한다. 
3. **mutation** ( 변이 )
   - 상태값을 변경하는 함수 같은존재 
   - Store의 상태 값은 Mutation(변의) 애 의해서만 변경되는 것이 좋다. 
   - method에서 사용됨 
   - `$store.commit('funcName')`을 통해 호출한다. 
4. **Action** 
   - 컴포넌트와 mutation 사이에 존재하는 객체 

## State

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

## Getter

- Vuex 의 데이터를 접근할 때 중복된 코드로 반복호출하는것을 간략화 하기 위해 사용된다. 
  -  여기서 주의할점은 getter를 사용하는 곳은 Method가 아닌 **Computed** 이다. 
  -  따라서 Computed의 장점인 Caching 효과를 이용할 수 있다. 


```vue
<script> // getter 사용 안했을 경우 state 값 변경 방법 
// App.vue
computed: {
  doubleCounter() {
    return this.$store.state.counter * 2;
  }
},

// Child.vue
computed: {
  doubleCounter() {
    return this.$store.state.counter * 2;
  }
},
</script>
```

```vue
<script>// getter 사용 했을 경우 state 값 변경 방법 
export const store = new Vuex.Store({
  // ...
  getters: {
    getCounter: function (state) {
      return state.counter;
    }
  }
});

// App.vue
computed: {
  doubleCounter() {
    return this.$store.getters.getCounter; // 함수만 호출하면 됨 
  }
},

// Child.vue
computed: {
  doubleCounter() {
    return this.$store.getters.getCounter;
  }
},
</script>
```

### getter의 이용 방법

1. computed 에 직접 mapGetters imort 해서 사용

   - 단점은 컴포넌트 자체에서 사용할 Computed 속성과 함께 사용하지 못하게 된다. 
   - 따라서 2번 방법으로 많이 사용한다. 

   ```vue
   <!-- App.vue -->
   <div id="app">
     Parent counter : {{ getCounter }}
     <!-- ... -->
   </div>
   <script>
   // App.vue
   import { mapGetters } from 'vuex'
   
   computed: mapGetters([
     'getCounter'
   ]),
   </script>
   ```

2. ES6 의 문법을 통해 Computed에 선언해서 사용

   ```vue
   <script>
   // App.vue
   import { mapGetters } from 'vuex'
   
   computed: {
     ...mapGetters([
       'getCounter'
     ]),
     anotherCounter() {
       // ...
     }
   }
   </script>
   ```

### Getter 와 Mutations 차이점

- **Mutations** 
  1. 인자를 받아 Vuex 에 넘겨줄 수 있고
  2. computed 가 아닌 methods 에 등록
- **Gettter** 
  1. 인자를 받아 vuex 의 store 로 넘길 수 없고
  2. computed 와 함께 사용되야 한다. 
     - methode 에서 사용 못함 

## Mutation

- 저장소의 핵심 요소로, **상태를 변경하는 함수**들을 보유하고 있는 객체이다. 
  - 즉, **저장소의 데이터들을 일정하게 변형하고 싶을 때 사용**되는 객체이다. 
- Store의 상태 값은 Mutation(변의) 애 의해서만 변경되는 것이 좋다. 

<img src="./images/vuex-mutations.png" width="500">

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

### Mutations 와 Actions 의 차이점 

- Mutations 동기적 로직을 정의
  - Actions 는 비동기적 로직을 정의
