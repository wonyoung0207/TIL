# vue의 $options

---

>[참고 사이트1](https://velog.io/@devmin/Vue.js-options-use)
>
>[공식문서 options 종류](https://vuejs.org/api/options-state.html)

## $options 

### 정의 

1. 뷰 인스턴스에 제공되는 속성이다. 
   1. 컴포넌트 옵션으로써 읽거나 변경하는 것은 추천되지 않는다. 
   2. 주로 디버깅에 사용 됨 
2. 즉, vue 컴포넌트를 구현할 때 data, methods, computed, watch 같은것들을 options 로 읽어올 수 있다.
   1. created, mounted 등은 vue의 라이프싸이클 훅이기 때문에 options 로 불러와 사용할 수 없다. 

### 라이프 싸이클 훅 종류 

1. `beforeCreate`, `created`, `beforeMount`, `mounted`, `beforeUpdate`, `updated`, `beforeDestroy`, `destroyed`, `activated`, `deactivated`

### options 종류 

1. components
   1. 현재 vue 인스턴스에 등록된 컴포넌트의 목록을 가진다. 
2. data
   1. data 목록을 가진다. 
3. methods
   1. 메소드 목록을 가진다. 
4. computed 
   1. computed 목록을 가진다. 
5. watch 
   1. watch 목록을 가진다. 

### 사용방법

- this.$options 안에 속성값들을 이용하면 된다. 

```js
this.$options.data // -> 컴포넌트 안에 선언한 data의 값들이 출력된다. 해당 data의 값은 시점에 따라 초기값에서 변경된값으로 표현될 수 있다. 
this.$options.methods
this.$options.computed 
```





