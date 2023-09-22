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

### 이벤트

##### 사용이유

- 애니매이션 효과 발생시 처리하고 싶은 Javascript 로직을 실행할 수 있다. 
- 즉, 애니매이션의 enter나 leave 시점에서 원하는 함수를 발생시킬 수 있다.

##### 사용할 수 있는 이벤트

1. befor-enter
2. enter
3. after-enter
4. before-leave
5. leave
6. after-leave

##### 사용 예시

```vue
<traisition>    
    name="wony"
    :css="false" // vue에 javascript만으로 동작한다고 알리는 것으로, css 를 찾는 수고로움을 덜 수 있다. 
    @befor-enter = "beforeEnter"
    @enter="enter"
    @after-enter="afterEnter"
    @before-leave="beforeLeave"
    @leave="leave"
    @after-leave="afterLeave"
    
    @enter-cancelled="" /* 이벤트가 끝났을 때마다 호출 된다. -> 기존 이벤트가 끝나고나서 다음 이벤트(애니매이션 효과)가 발생하게 하기위해 사용한다. */
    @leave-cancelled=""
</traisition>

<script>
methods: {
    enter(el, done){
        // el : 컴포넌트에 사용되는 엘리멘트 속성값
        // done : 해당 이벤트가 완전히 끝났다고 알려주는 특이 함수 -> 이거 안쓰면 enter에서 준 효과가 끝나기 전에 after-enter가 실행됨 
        let round = 1;
        const interval = setInterval(function(){
            el.style.opacity = round * 0.01;
            round++;
            if(round > 100) {
                clearInterval(interval);
                done(); // enter 이벤트가 끝났다는 것을 vue에 알려 enter-after 가 먼저 실행되는 것을 방지해준다. 
            }
        })
    }
	afterLeave(el){
        
    }
}
</script>
```



### transition-group

#### 정의

- transition 컴포넌트를 그룹형태로 사용할 수 있는 컴포넌트 이다. 

##### 사용이유

- 기존 trainsition컴포넌트는 하나의 자식 태그에만 애니매이션 효과를 적용할 수 있었다. 
- 이때 transition-gruop 컴포넌트를 이용해 여러 태그에 애니매이션 효과를 적용할 수 있다. 

##### 사용예시

```vue
<temaplate>
    <!-- tag에는 어떤 엘리맨트가 오던 상관없지만, tag로 알려줘야 vue가 인식한다.  -->
	<transition-group tag="ul" name="user-list"> 
    	<li v-for="user in users" :key="user" @click="removeUser(user)"> </li>
    </transition-group>
    <div>
        <button @click="addUser">
            Add user
        </button>
    </div>

</temaplate>
```





