## Vue의 EventBus

>[참고 사이트1](https://song8420.tistory.com/379)
>
>[참고 사이트2](https://vuejs-kr.github.io/jekyll/update/2017/02/13/vuejs-eventbus/)

### 정의

- 비 부모-자식간 **이벤트 통신**을도와주는 컴포넌트이다.  

### 사용이유

- Vue는 컴포넌트 단위로 메소드/변수를 포함해서 구성한다. 
  - Vue에서 개발을 하다보면 데이터의 경우 vuex와 같은 라이브러리를 사용하면 컴포넌트간 데이터를 쉽게 가져올 수 있다.
  - 그리고 **컴포넌트간 메서드를 호출**해야할 경우가 있다.
  - 보통 emit 같은 기능을 사용해서 구현을 하겠지만 **비 부모-자식간 통신**을 할떄는 emit을 사용할 수 없다. 
- 이러한 경우 **EventBus를 활용**하여 컴포넌트간 메소드를 호출할 수 있다. 
  - EventBus라는 공간에 각 프로젝트에 맞는 규격을 만들어 규격에 따라 EventBus를 활용하면 된다.

### 사용예시

```vue
// 이벤트버스 생성
var EventBus = new Vue()

// 이벤트버스 발행 
export default EventBus

// 이벤트 발생 
EventBus.$emit('message', 'hello world');

// 이벤트 구독
// on으로 message 이벤트를 보고있다가 해당 이벤트 발생하면 실행됨 
EventBus.$on('message', function(text) { 
    console.log(text);
});

// 파라미터 이용 이벤트 발생 
this.$EventBus.$emit('fetchData', param)

// 파라미터 이용 이벤트 구독 
this.$EventBus.$on('fetchData', (param) => {
	this.fetchData(param);
})
```

