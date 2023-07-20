# template 개념정리 

---

>

## template

### 정의

- **컴포넌트의 시작 태그**로, 해당 앱에서 사용할 **HTML 코드를 정의**한다. 
  - 주로 태그가 여러줄이기 때문에  ' ' 가 아닌  \` ` (백틱) 으로 감싼다. 


### 예시

```js
app.component('appName', {
	template : '<div> </div> ' //  ' ' 는 한줄만 가능 
    template : 
    `
    	<div> </div> 
    	<span> </span> 
    	<input /> 
    ` // ` ` 은 여러줄 가능 
    data(){
		return {};
    }
})
```

