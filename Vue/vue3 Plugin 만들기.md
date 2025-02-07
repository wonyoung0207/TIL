# vue3 Plugin 만들기

---

>[짐코딩- 플러그인](https://gymcoding.notion.site/Plugins-89791ce78af04abf9e8aae2a886ddf68)
>
>[unplugin-vue-components 인스톨](https://www.npmjs.com/package/unplugin-vue-components)

## 플러그인

플러그인(Plugin)은 일반적으로 Vue에 전역 수준의 기능을 추가할 때 사용하는 기능을 말합니다. 플러그인에 대해 엄격하게 정의 된 범위는 없습니다. 일반적으로 플러그인이 유용한 시나리오는 다음과 같습니다.

- `app.component()`
  - **전역 컴포넌트를 등록** 하고자 할 때
  - 전역 애플리케이션 인스턴스에 속성 또는 메서드를 추가하고자 할 때 `app.config.globalProperties`에 연결하여 추가할 수 있습니다.
- `app.directive()` 
  - **커스텀 디렉티브를 등록** 하고자 할 때
- `app.provide()`
  - **앱 전체에 리소스(메서드 또는 데이터)를 주입**할 때

## app 에 추가

1. `vue.app()`을 이용해 `app.config.properties()` 와 `app.component()` 등을 전역 인스턴스에 추가할 수 있다.

2. 예시 ) **전역 객체 등록**

   ```js
   // person.js
   
   // 1. app.config.properties 사용 
   export default {
       install(app){
   		app.config.globalProperties.$personObj = person;
       }
   }
   ```

   ```js
   // app.use() 사용
   import personObj from 'person.js'
   
   const app = createApp(App)
   // 객체 전역 등록 
   app.use(personObj, { name : 'wony'}) // personObj 를 글로벌 컴포넌트로 사용 
   ```

## 플러그인 형태

1. 플러그인의 형태는 함수, 객체 2가지의 형태가 존재한다. 

##### 함수 형태

```js
// 단순히 설치 함수
function funcPlugin(app, options) {

}
export funcPlugin;
```

##### 객체 형태

1. 객체의 형태는 install() 메서드를 가지고 있어야 한다. 
2. install() 매개변수
   1. 애플리케이션 인스턴스 ( createApp() 으로 vue에서 가장 처음 만든 인스턴스 )
   2. options ( app.use 에서 넘겨준 객체 값 )
3. 애플리케이션 인스턴스에 추가된 객체는 모든 자식 컴포넌트에서 사용할 수 있다. 
4. 주의할점
   1. **`setup` 에서는 사용 불가 -> 사용하려면 provide()을 사용해 inject() 로 받아서 사용하면 된다.** 
   2. 즉, `<script setup >` 안에서는 사용하지 못한다. 

```js
// install() 메서드를 갖고 있는 객체
const personObj = {
	install(app, options) { 
        const person = {
            name : options.name,
            haha(){
                alert('haha wony')
            }
        } 
        // 전역 프로퍼티로 등록 
		app.config.globalProperties.$personObj = person;
        
        // setup 에서 inject() 로 사용하기 위해 주입
		app.provide('personObj' , person);
	}
}
```

```js
import personObj from './plugins/personObj';

const app = createApp(App)
app.use(personObj, { name : 'wony'}) // personObj 를 글로벌 컴포넌트로 사용 
```

```vue
<template>
	<!-- 전역 인스턴스 직접 호출해 사용 -->
	<h2>
        {{ $personObj.name }}
    </h2>

	<!-- inject()로 받아와 객체 사용  -->
	<button @click="person.say">
        say button
    </button>
</template>

<script>
export default {
    created(){
        this.$personObj.say(); // 전역 인스턴스: this 
    }
}
</script>

<script setup>
// provider() 사용해 주입해서 사용 
const person = inject('personObj');
</script>
```

## Global Component 등록

##### 전역 컴포넌트 등록

1. 주의할점
   1. **전역 컴포넌트들은 `components.d.ts` 로된 파일에다가 `interface` 로 등록해줘야 전역적으로 컴포넌트 사용시 인식할 수 있다.** 
   2. 이런 번거로운 작업을 하기 싫다면 `unplugin-vue-components` plugin을 사용하면 된다.  

```js
// globalComponents.js

// app.component() 사용
import WonyAlert from '@/components/app/WonyAlert.vue';

export default {
    install(app){
        app.component('WonyAlert', WonyAlert)
    }
}
```

```js
// app.use() 사용
import globalComponents from 'globalComponent.js'

const app = createApp(App)

// component 전역 등록
app.use(globalComponents)
```

```ts
// components.d.ts

// interface 등록 
declare module '@vue/runtime-core' {
    export interface GlobalComponents {
        WonyAlert : typeof import('@/components/app/WonyAlert.vue')['default'];
    }
}
```

