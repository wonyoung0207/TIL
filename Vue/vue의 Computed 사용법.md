# Computed 사용법

---

>[참고 사이트1](https://velog.io/@jinsu6688/vuejs-computed)
>
>[참고 사이트2](https://mosei.tistory.com/entry/Vuejs-Computed%EC%9D%98-%EA%B8%B0%EB%8A%A5%EB%93%A4-%EC%BA%90%EC%8B%B1-GetterSetter)
>
>[참고 사이트3](https://kyounghwan01.github.io/blog/Vue/vue/computed/#computed-getter-setter)



## Computed

### 정의

- vue 의 데이터 가공시 유용한 기능이다. 
- method와 비슷하게 동작한다. 
  - computed 안에 선언되는 것들은 **메소드이다. 하지만 method 처럼 () 를 붙이지 않고 변수처럼 호출해 사용한다.** 

### 사용 이유 

- computed는 vue의 바인딩을 활용한 가장 좋은 기능중 하나이다. 
- 여러 변수들이 변화함에 따라 바인딩한 값이 동적으로 변할 수 있도록 한다. 
  - 즉, a 라는 바인딩 변수는 ( b + c ) 값으로 이루어져 있다. 
  - 이때 바인딩만 사용한다면 시점에 따라 b와 c 값이 변해도 a 의 값은 변하지 않는 일이 발생할 수 있다. 
  - computed로 사용하면 a 라는 값과 관련된 b와 c의 값이 변할때마다 업데이트를 진행한다. 

### 특징

1. 형태 

   - 동작방식이 method 처럼 동작하지만 사용은 변수처럼 사용된다. 

     - 즉, 선언과 동작을 method 처럼 한다. 

   - 예시 

     ```vue
     <div>
         <div>
             {{ a }} <!-- 연산작업을 진행하는 메소드처럼 보이지만 변수처럼 사용함 -->
         </div>
     </div>
     <script>
     computed : {
     	a () {
     		return b + c ;
     	}
     }
     </script>
     ```

2. **캐싱**

   - 계속해서 호출되는 method와는 달리 computed는 내부의 데이터가 변경될때만 호출되어 성능을 높일 수 있다. 
   - 또한 **method가 호출 시 값을 계산하는 것과는 다르게 computed는 값의 변화될 때 자동 계산되고 그 값을 가지고 있다가 호출 시 값을 바로 리턴**할 수 있다. 
     - 즉, 캐싱된 값(계산된 값) 을 가지고 있다가 바로 리턴해주는 방식이다. 

### 사용방법

- computed에 **설정되어있는 값(함수)**들은 기본적으로 **Getter 처럼 동작**한다고 생각하면 된다. 
  - 따라서 get() 과 set() 을 이용해 값을 가져오거나 설정할 수 있다. 
  - 하지만 get ,  set 을 사용하지 않고 더 간단히 사용하는것이 일반적이다. 

```vue
<template>
  <div id="demo">{{ fullName }}</div>
</template>

<script>
export default {
  computed: {
	// 방법1. 일반적으로 많이 사용
  	fullName () { // 하나의 함수임 . 하지만 사용은 변수처럼 사용됨 
        return this.firstName + " " + this.lastName; // 해당 리턴 값을 변수에 저장한것처럼 가지고있음
    }
      
  	// 방법2. get(), set() 이용 
    fullName: {
      // getter
      get: function() {
        return this.firstName + " " + this.lastName;
      },
      // setter
      set: function(newValue) {
      	// first와 last 네임이 달라져 get() 으로 인해 fullName이 임의의 값으로 바뀌게 되면 set이 실행
        var names = newValue.split(" ");
        this.firstName = names[0];
        this.lastName = names[names.length - 1];
      }
    }
  }
};
</script>
```

