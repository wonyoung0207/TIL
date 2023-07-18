# Watch 옵션 개념정리

---

>

## Watch 

### 정의

- 데이터의 변화에 따라 특정 로직을 수행하는 vue의 옵션 중 하나이다. 
  - 따라서 데이터의 변화를 감지하는 속성이다. 

- 값의 변화를 추적하기 때문에 이전값(oldValue)과 새로 변화된 값(newValue) 에 대한 정보를 가진다. 

### computed  vs   watch

1. computed
   - 주로 자주 변경되고 헤비하지 않은 데이터를 바인딩 할 때 사용한다.
   -  vue에서 사용되는 컴포넌트인 vee-validation 의 대부분이 computed 로 구현되어있다.
   - 사용처
     - 간단한 text 변경 
     - validation 체크   
2. watch 
   - 주로 computed 에서 사용되지 않는 무거운 로직을 포함해야할 때 사용한다. 
   - 이전값과 새로운 값의 정보를 토대로 다른 로직을 실행할 댸 사용 
   - 사용처 
     - 데이터 요청 ( axios 로 post나 get 등등 ...)
3. 결론
   - 거의다 computed로 구현하는 것이 권장된다. 
   - 따라서 watch로 구현할 수 있는 내용들은 computed 로 구현하는 것이 로직에 더 도움이 되고 가독성도 좋다. 

### 사용법

```vue
new vue ({
	created : {
		시작
	},
	data : {
		데이터...
		num : 10, 
	},
	watch : {
		num : function(){
			this.textLog();
		}
	}
})
```

