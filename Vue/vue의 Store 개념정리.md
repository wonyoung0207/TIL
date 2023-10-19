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

### 주의할점

- Refresh 하면 store가 초기화된다. 

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
   - State의 값을 변경하는것이 아닌 가공할 때 사용  
   - computed 에서 사용됨 
   - `$store.getters['GetterName']` 를 이용해 호출한다. 
3. **mutation** ( 변이 )
   - State 값을 변경할때 사용 
   - Store의 상태 값은 Mutation(변의) 애 의해서만 변경되는 것이 좋다. 
   - methods에서 사용됨 
   - `$store.commit('funcName')`을 통해 호출한다. 
4. **Action** 
   - 컴포넌트와 mutation 사이에 존재하는 객체로, 비동기 처리를 할 떄 사용 
   - mutations과 연결되기 때문에 methods 에서 사용됨
   - `$store.dispatch("funcName")` 를 통해 호출

## 1. State

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

## 2. Getter

- Vuex 의 데이터를 접근할 때 중복된 코드로 반복호출하는것을 간략화 하기 위해 사용된다. 
  -  여기서 주의할점은 getter를 사용하는 곳은 Method가 아닌 **Computed** 이다. 
  -  따라서 Computed의 장점인 Caching 효과를 이용할 수 있다. 
- getter를 사용하면 store 에서 여러줄의 조건문을 작성할 수 있다는 장점이 생긴다.
- 매개변수
  - 인자값으로 (state, getters) 를 받아 사용한다. 
  - state : 현재 store의 state 값을 사용할 수 있게 됨 
  - getters : getters 에 선언되어있는 다른 함수를 사용할 수 있음. 



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
    getCounter: function (state) { // getter의 기본 사용 예시 
      return state.counter;
    },
	finalCounter : function(state, getters){ // getter 의 매개변수 사용 예시 
        const finalCounter = getters.getCounter;
        if(finalCounter > 100){
            return 100;
        }
        return finalCounter
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

- 요약
  - Mutations는 state의 데이터 값을 변경할 때 사용
  - Getters는 state의 데이터 값을 변경하진 않고 가공할 때 사용 

- **Mutations** 
  1. 보통 데이터를 변경하는데 사용된다. 예를 들어, 데이터를 추가, 수정 또는 삭제할 때 사용한다.
     - 즉, **state에 있는 데이터의 값을 변경할 때 사용한다.** 
  2. 인자를 받아 Vuex 에 넘겨줄 수 있다. 
  3. 컴포넌트 옵션의 computed 가 아닌 **methods** 에서 사용
  4. **동기 작업만 수행**할 수 있으며, 비동기 작업을 수행하기에 적합하지 않다. 
  5. **`commit` 메서드를 사용하여 호출**하며, 인자로 뮤테이션 함수와 데이터를 전달
  6. **캐싱을 하지 않는다**. 매번 호출될 때마다 새로운 값을 반환
- **Gettter** 
  1. 주로 상태(`state`)의 **데이터를 조회**하고 **필요한 형태로 가공**하여 컴포넌트에서 사용하기 위해 사용한다. 
     - 즉, **state의 데이터를 변경하는것이 아닌 가공할때 사용한다.** 
  2. 인자를 받아 vuex 의 store 로 넘길 수 없다.
  3. 컴포넌트 옵션의 **computed** 에서 사용된다. 
     - 즉, methode 에서 사용 못함 
  4. **동기 작업을 수행**하거나 **계산된 속성을 반환**할 때 사용하며, 비동기 작업은 직접 수행하지 않는다.
  5. 일반적으로 **`mapGetters` 헬퍼 또는 `store.getters` 속성을 사용하여 접근**하여 호출한다. 
  6. 계산된 속성이므로, 같은 입력값에 대한 **출력값을 캐싱**하여 성능을 향상시킨다.

## 3. Mutation

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

## 4. Actions

- 컴포넌트와 Mutations 사이에서 동작한다. 
- Mutations 는 동기작업을 기본으로 하기 때문에 , 비동기 작업으로 처리하기 위해서는 Actions를 사용해야 한다. 
  - **actions를 이용해 비동기적으로 작업을 처리할 수 있다.** 

<img src="./images/vuex-actions.png" width="500">

### 특징

1. mutations에서 사용된 메소드의 이름을 그대로 사용할 수 있다. 
   - 만약 increment() 메소드를 사용했다면, actions에서도 동일한 이름으로 메소드를 선언해 사용한다. 
   - 다른 이름으로도 사용 가능. 
2. actions의 메소드 매개변수로 context 인자값을 받는다. 
   - 컴포넌트와 mutations 중간에서 context.commit() 을 이용해 mutations 의 함수를 호출한다.
3. dispatch 를 이용해 호출된다. 
   - 커밋과 비슷하게 사용한다. 
     - mutations와 동일하게 매개변수로 (액션의 이름 , payload) 을 보낸다. 

### 사용방법

1. dispatch로 actions 호출
2. actions에서 commit() 으로 mutations 호출
3. mutations에서 state 값 변경 

```js
// main.js 
import { createApp } from './App.vue';
import { createStore } from 'vuex';

const store = createStore({ // 전역 저장소 선언 
	...
    mutations : { 
        increment(state, payload){ //동기 작업 실행
    		state.counter = state.counter + payload.value;
		}
    },
    actions : {
        increment(context){ // 비동기 작업으로 동작하도록 함 . 
            setTimeout(function(){
                context.commit('increment', {value : 2 });
            }, 2000 ) 
        }
    }
})
```

```vue
<template>
    <component1>
        <!-- 방법1 -->
        <button @onClick="addOne"> btn1 </button>
        
        <!-- 방법2 -->
        <button @onClick="increment({value : 10 })"> btn2 </button>

        <!-- 방법3 -->
        <button @onClick="inc({value : 10})"> btn3 </button>

    </component1>
</template>

<script>
import Component1 from './components/component1.vue';
import mapActions from 'vuex';

export default {
    methods : {
        // 방법 1
        addOne(){
            this.$store.dispatch('increment', {value : 10}); 
        },
        
        // 방법 2
        ...mapActions(['increment', 'finalCounter']);
        
        // 방법 3
        ...mapActions({
        	inc : 'increment', // increment 메소드 호출 
        	fc : 'finalCounter', 
	    });
    },
}
</script>
```

