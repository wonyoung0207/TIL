# 컴포넌트 재생성 (re create) 하는 방법

---

>[참고 사이트1](https://michaelnthiessen.com/force-re-render)
>
>[참고 사이트2](https://hyeonyeee.tistory.com/97)

## 재생성 방법

1. router 의 go 이용하는 방법
2. 컴포넌트의 속성 중 :key 를 이용한 방법 ( 더 좋은 방법 )



## :key 를 이용한 방법 

### 동작 원리 

1. vue의 컴포넌트에는 key 라는 속성이 존재한다. 
2. key 가 동일하면 컴포넌트가 변하지 않지만 , key가 변하면 Vue는 예전 컴포넌트를 지우고 새로운 컴포넌트를 만든다. 
   1. 즉, key가 변하면 컴포넌트의 destroyed() 가실행된다. 
3. key 속성의 값을 변경해 컴포넌트를 재생성 하는 방법이다. 

### 동작 방법 

1. 컴포넌트를 생성한다. 
   1. 생성시 속성으로 `:key` 값을 설정해준다. 
2. 어떠한 이벤트(지금은 click이벤트로 구현하겠음) 로 `:key` 값에 할당한 값을 변경해준다. 
   1. 컴포넌트를 재랜더링 또는 재생성 하고 싶다면 해당 이벤트에 설정해놓은 메소드를 호출하면 된다.
3. 변경된 `:key` 값으로 인해 컴포넌트가 destroyed 되고 created() 된다. 

```vue
<template>
	<div>
        <feather-icon icon="ExIcon" @click="settingBarRefresh"></feather-icon>  <!-- 누를때마다 setting-bar 컴포넌트가 destroyed 되고 create 된다.  -->
        <setting-bar :key="settingBarKey"></setting-bar>
        <!-- 다른 표기 방법 -->
        <SettingBar></SettingBar>
    </div>
</template>

<script>
import SettingBar from 'SettingBar.vue';
export default {
    components: {
      SettingBar
    },
    data: function() {
      return {
        settingBarKey : 0,
      }
    },
    methods: {
      settingBarRefresh() {
        console.log("settingBarRefresh... ");
        this.settingBarKey += 1;
      },
}
</script>
```

