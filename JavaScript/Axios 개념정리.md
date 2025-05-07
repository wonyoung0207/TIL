## axios 개념정리 

---

>[참고 사이트1](https://mishka.kr/17)
>
>[참고 사이트2](https://developerjournal.tistory.com/8)

## axios 

### 정의

- javascript 어플리케이션에서 **서버 통신**을 하기 위한 HTTP **통신 라이브러리**이다. 
  - **HTTP 클라이언트 라이브러리**이다. 
  - **비동기 방식**으로 HTTP 데이터 요청을 실행한다. 
  - **JavaScript 또한 비동기 방식을 따른다. **
- 따라서 서버와 통신하기 위한 라이브러리이다. 
  - 주의할점은 비동기 방식이기 때문에 async 와 await 를 이용해 

### 사용방법

1. 기존 프로젝트에서 axios로 서버와 통신 하기 위해서는 이를 node_modules에 설치해야한다. 이때 다음과 같은 명령어를 사용하면 된다.

   ``` 
   npm install axios
   ```

2. 이후 /node_modules에 axios가 추가된 것을 확인할 수 있다

3. axios를 vue에서 사용

```vue
// axios를 import
import axios from 'axios'

Vue.prototype.$eventBus = new Vue(); // 전역으로 axios를 사용하기 위해서 Vue.prototype.$axios를 정의
Vue.prototype.$axios = axios // 다른 컴포넌트에서는 import 없이 this.$axios로 사용가능

export default new Vuetify({
})

Vue.use(Vuetify)

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  vuetify: new Vuetify(),
})
```

```vue
<script>
const HOST = "";

export default {
  methods: {
    getData() {
      this.$axios
        .get(HOST + "/api/getData", {
          headers: { "X-AUTH-TOKEN": "인증 받음을 증명하는 토큰" },
        })
        .then((res) => {// 통신 성공
          console.log(res.staus);
          console.log(res.data);
        })
        .catch((error) => {// 통신 실패
          console.log(error);
        })
        .finally(() => {
          console.log("항상 마지막에 실행");
        });
    }
  },
};
</script>
```

### $axios 메소드 종류 

1. **get**
   - 서버로부터 데이터 **가져오기** => Read
   - 파라미터
     - 첫번째 : axios 에 전달할 서버의 URL
     - 두번째 : config 객체 선택가능 
2. **post**
   - 서버로 데이터 보내서 **저장** => Create
   - 파라미터
     - 첫번째 : axios 에 전달할 서버의 URL
     - 두번쨰 : 입력할 데이터 
3. **put**
   - 서버의 데이터를 **수정** => Update
   - 파라미터
     - 첫번째 : axios 에 전달할 서버의 URL
     - 두번쨰 : 수정할 데이터 
4. **delete**
   - 서버의 데이터 **삭제** => Delete
   - 파라미터
     - 첫번째 : axios 에 전달할 서버의 URL
     - 두번쨰 : 입력할 데이터 
5. 위에 자주 쓰이는 4가지 이외에도 여러가지 를 지원 하고 있다.
   - axios.request(config)
   - axios.head(url[, config])
   - axios.options(url[, config])
   - axios.put(url[, data[, config]])

```vue
this.$axios
    .put(HOST + "/api/putData" + number, JSON.stringify(updateData))
	// .get(HOST + "/api/getData"
	// .post(HOST + "/api/postData", JSON.stringify(saveData))
	// .delete(HOST + "/api/deleteData" + deleteKey)
    .then((res) => {
      console.log(res.staus);
      console.log(res.data);
    })
    .catch((error) => {
      console.log(error);
    })
    .finally(() => {
      console.log("항상 마지막에 실행");
    });
```

- 비동기 해결책은 (async/await) 를 사용해서 처리 순서를 정해주면 된다. 
  - async
    - 함수 앞에 위치한다. 
    - `async`가 붙은 함수는 반드시 **프라미스**를 반환하고, 프라미스가 아닌 것은 프라미스로 감싸 반환
  - await
    -  `await`는 `async` 함수 안에서만 동작
    -  자바스크립트는 `await` 키워드를 만나면 프라미스가 처리될 때까지 기다린다. 
- 따라서 해당 함수가 비동기 함수라는 것을 async 로 알리고, 그 안에서 await를 써서 결과값을 기다린 후 함수의 값으로 Promise를 반환한다.  

---

## 비동기 처리 (Asynchronous)

### 정의

- axios를 사용하면서 주의할 점이 있다. 이는 axios가 **Promise 기반의 자바스크립트 비동기 처리방식**을 사용한다는 것이다.
  - 이때의 문제점은 **처리 순서를 지정하지 않으면 request 요청을 보내고 나서 response로 응답도 받기 전에 바로 다음 구문을 수행해 버리기 때문에 원하는 결과를 받아오지 못한다는 점이다.**

### Promise

- **Promise**는 비동기 연산이 종료된 이후의 **결과값**이나 **실패 이유**를 처리하기 위한 처리기를 연결할 수 있도록 한다. 

  - 다시말해 비동기 연산 종료 후에 해야할 행동들을 **약속**한다고 보면 된다.

  - Promise는 다음 중 하나의 상태를 가진다.
    1. **Pending(대기):** 이행하거나 거부되지 않은 초기 상태
    2. **Fulfilled(이행):** 연산이 성공적으로 완료됨
    3. **Rejected(거부):** 연산이 실패함

### 사용법

- async
  - 함수 앞에 위치한다. 
  - `async`가 붙은 함수는 반드시 **프라미스**를 반환하고, 프라미스가 아닌 것은 프라미스로 감싸 반환한다. 
- await
  -  `await`는 `async` 함수 안에서만 동작
  -  자바스크립트는 `await` 키워드를 만나면 프라미스가 처리될 때까지 기다린다. 

- 따라서 해당 함수가 비동기 함수라는 것을 async 로 알리고, 그 안에서 await를 써서 결과값을 기다린 후 함수의 값으로 Promise를 반환한다.  

```vue
<script>
const HOST = "";

export default {
  data() {
    return {
      allPeopleList: [],
      allAnimalList: [],
    };
  },
  created() {
    this.getMultiData();
  },
  methods: {
    async getMultiData() {
      try {
        this.allPeopleList = await axios
          .get(HOST + "api/getAllPeople")
          .then((res) => {
            console.log(res.staus);
            console.log(res.data);
          })
          .catch((error) => {
            console.log(error);
          })
          .finally(() => {
            console.log("항상 마지막에 실행");
          });
        console.log(this.allPeopleList);
      } catch (error) {
        console.error(error);
      }

      try {
        this.allAnimalList = await axios
          .get(HOST + "api/getAllAnimal")
          .then((res) => {
            console.log(res.staus);
            console.log(res.data);
          })
          .catch((error) => {
            console.log(error);
          })
          .finally(() => {
            console.log("항상 마지막에 실행");
          });
        console.log(this.allAnimalList);
      } catch (error) {
        console.error(error);
      }
    }
  },
};
</script>
```



