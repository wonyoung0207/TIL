# Vue의 transition 컴포넌트 태그

---

>

## transition 컴포넌트

### 정의

- vue 에서 지원하는 컴포넌트 중 하나로, **해당 태그 안에 있는 요소들에 애니매이션 효과를 적용시키고 싶을 때 사용**한다. 

### 주의할점

- 해당 태그 안에 있는 **직계 자식 요소 1개**에만 애니매이션 효과가 적용된다. 
  - 따라서 자식 요소 1개로만 사용해야한다. 
- 만약 2개 태그를 사용하고 싶다면 v-if 와 v-else를 사용해야한다. 

### mode

- 요소의 등장과 퇴장 시점에 애니매이션을 적용하는 방식을 적용할 수 있다. 
  - 즉, **요소의 나타남과 사라짐의 순서**를 지정할 수 있는 속성 
- 종류
  1. in-out
     - 등장하는 element의 애니메이션이 끝나기 전에 퇴장하는 element의 애니메이션을 적용
  2. out-in
     - 퇴장하는 element의 애니메이션이 끝나면 등장하는 element의 애니메이션을 적용
       - 즉, 기존의 element가 완전히 퇴장한 후에 새로운 element가 등장
     - in-out보다 많이 사용됨 

### 사용방법

<img src="./images/transition컴포넌트 동작방법.png" width="500px">

```vue
<template>
	<transition>
        <div> 안녕  </div>
    </transition>
	<transition name="wony">
        <div> 안녕  </div>
    </transition>
</template>

<style>
.v-enter-from /* 요소가 나타나기 시작할 때 적용 */ 
.v-enter-active /* 요소가 나타나는 트랜지션이 진행되는 동안 적용 */ 
.v-enter-to /* 요소가 나타나는 트랜지션이 완료될 때 적용 */ 

.v-leave-from /* 요소가 사라지기 시작할 때 적용 */ 
.v-leave-active /* 요소가 사라지는 트랜지션이 진행되는 동안 적용 */ 
.v-leave-to /* 요소가 사라지는 트랜지션이 완료될 때 적용 */ 
    
.wony-enter-from /* name을 지정해 v 부분을 대체하여 사용할 수 있다.  */
</style>
```

### 사용 예시

```vue
<template>
  <div>
    <transition name="fade" mode="out-in">
      <!-- 애니메이션을 적용할 요소 -->
      <div class="box"></div>
    </transition>
  </div>
</template>

<style>
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>

```

