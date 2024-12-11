# vue3 pinia 개념정리

---

>

## Pinia

## 구조

1. state
   1. 컴포넌트의 상태, data 역할을 한다. 
2. getter
   1. 컴포넌트의 computed 역할을 한다.
3. actions 
   1. 컴포넌트의 메소드 역할을 한다. 

## 반응형

1. `const { counter } = store;`에서 `counter` 의 값은 처음 한번만 할당되기 때문에 이벤트에 의해 값이 변경되어도 값이 변경되지 않는다. **즉, 반응형으로 동작하지 않는다.** 
   1. 따라서 반응형으로 만들어줘야 하는데, 이때 사용되는 것이 pinia의 `storeToRefs()` 함수이다. 
2. 구분
   1. actions 
      1. 그냥 가져와 사용하면 됨
   2. state, getters
      1. storeToRefs 를 사용해 가져와야 반응형으로 사용할 수 있다. 

```js
// state, getters 
const { counter } = storeToRefs(store);

// actions
const { counter } = store;
```

## 사용예시

1. 관례
   1. Store의 이름은 앞에 `use` 를 붙여주는 것이 관례이다. 
   2. Store의 정의는 pinia 에서 defineStore 메소드를 이용한다. 
   3. 고유 이름은 defineStore 의 첫 매개변수에서 지정할 수 있다. ('counter' 가 Store의 고유 이름이 된다. ) 
2. 주의할점
   1. **getters와 actions에서 `=>` 문법을 사용하지 않는 이유는 `this.`의 의미가 달라지기 때문이다.** 

```js
import { defineStore } from 'pinia';

export const useCounterStore = defineStore('counter', {
	state: () => ({
		counter: 1,
	}),
	getters: {
		doubleCount: state => state.counter * 2,
		doubleCountPlusOne() {
			return this.doubleCount + 1;
		},
	},
	actions: {
		increment() {
			this.counter++;
		},
	},
});
```

```vue
<template>
	<div>
		<h2>About View</h2>
		<p>{{ $route.path }}</p>
		<button class="btn btn-primary" @click="$router.push('/')">
			Home으로 이동
		</button>
		<h2>Store</h2>
		<p>counter: {{ counter }}</p>
		<p>doubleCount: {{ doubleCount }}</p>
		<p>doubleCountPlusOne: {{ doubleCountPlusOne }}</p>
		<button @click="increment()">Click!!</button>
	</div>
</template>

<script setup>
import { useRoute } from 'vue-router';
import { useCounterStore } from '@/stores/counter';
import { storeToRefs } from 'pinia';

const route = useRoute();
console.log('route.path: ', route.path);

const store = useCounterStore();

const { counter, doubleCount, doubleCountPlusOne } = storeToRefs(store); // state, getters 인 경우 
const { increment } = store; // actions 인 경우 
counter.value = 100; // 직접 값을 수정할 수도 있다. 

</script>

<style lang="scss" scoped></style>
```

