# vue의 외부환경변수 주입(nginx이용)

---

>

## 문제 발생

1. 같은 frontend build 파일을 사용하는 프로젝트가 있는데 netty 연결같은 프로젝트 고유 정보가 바뀌어야 했다. 
2. 빌드를 2번 하기 싫어서 외부 파일 주입하는 방법을 찾아보게 되었다. 
   1. 나는 frontend 의 WAS 에서 nginx 를 사용하기 떄문에 nginx 를 사용하는 방법으로 진행했다. 

## 방법

1. frontend 실행시 외부 파일 주입 구문 추가 
   1. index.html 에서 외부파일 호출 
   2. main.js 에서 Vue.prototype.$config 에 외부 파일 내용 주입 
   3. vue에서 글로벌로 사용 가능
2. nginx.conf 이용해 해당 파일에 대한 요청을 프록시
   1. 프록시할 파일위치에 env-config.js 를 생성 

## 예제 코드 

##### frontend 

```html
<!-- index.html -->

<head>
    ... 
	<!-- 환경변수 설정 스크립트 (nginx 절대경로 사용) -->
	<script src="/env-config.js"></script>    
</head>
```

```js
// main.js

// 환경변수 주입 
Vue.prototype.$config = window._env_ || {};
```

```vue
<!-- App.vue -->

<script>
    ... 
    created() {
        console.log("nginx test ::: Netty backHost:", this.$config.netty.backHost);
    }
    
</script>
```

##### nginx

```nginx
# nginx.conf 
http {
	server {
		listen       34050;
		server_name  site1;

		access_log C:/app/logs/nginx/access.log;
		error_log C:/app/logs/nginx/error.log;	

		location / {
			root C:/app/nginx/dist;
			index  index.html;
			charset utf-8;
		}

        # 환경변수 주입 파일 경로 (C:/app/nginx/conf/env-config.js 를 찾음 )
        location = /env-config.js {
            root C:/app/nginx/conf;
            add_header Cache-Control "no-cache";
        }
    }
}
```

```js
// env-config.js
window._env_ = {
    netty : {
        backHost: '111.111.111.11',
        backPort: 1111, 
    },
};
```



## window 키워드 사용 이유 

1. `window`는 왜 꼭 써야 하는걸까요? 그냥 `frontend.config = { ... }`라고 쓰면 안 되는 건가요?

   1. `window`는 생략할 수 있지만, "전역(global) 변수"로 만들고 싶다면 반드시 `window.`에 할당해야 한다. 

2. 자바스크립트에서 브라우저 환경의 **전역 객체**는 `window` 이다. 

   1. 따라서 window 이용하면 `frontend`는 **전역 어디에서나 접근 가능**

   2. Vue 앱, 외부 JS, console 등 어디서든 `window.frontend.config`로 사용할 수 있다. 

      ```js
      window.frontend = { config: { ... } };
      ```

3. 반대로 `frontend.config = {}`라고 쓰면?

   1. 에러 발생:

      ```js
      frontend.config = { ... };
      // ❗ ReferenceError: frontend is not defined
      ```

   2. 왜냐하면:

      1. **`frontend`라는 이름의 변수**를 선언한 적이 없고
      2. 브라우저는 자동으로 이걸 **전역 객체의 속성**이라고 간주하지 않음