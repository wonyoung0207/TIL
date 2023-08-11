## fetch API

---

>

## fetch ApI

### 정의

- Axios와 같은 것으로, 둘다 내부에서 **HTTP 요청을 보낼 때 사용**한다. 
  - 즉, 페이지를 새로고침 하지 않고, 애플리케이션을 종료 및 재시작 하지도 않는다. 

### 장점

- **fetch API**는 브라우저에 내장되어 있으므로 이를 사용하기 위해 추가 패키지를 설치할 필요가 없다. 
  - 즉, **내장 API 로 제공된다.** 

### 사용법

```js
// 형태 : fetch( 요청 URL 주소 ,  js 객체, )
fetch('http://naver.com/surveys.json', {
    method: 'POST', // HTTP 요청 메소드 
    header : {
        'Content-Type' : 'application/json',
    },
    body : JSON.stringify({ // 객체를 JSON 형식으로 변경해줌 
        name : this.enteredName, // 보낼 데이터
        rating : this.chosenRating,
    }),
} )

// 위 코드를 axios로 변경 
import axios from 'axios'; // at the start of your <script> tag, before you "export default ..."
...
axios.post('http://naver.com/surveys.json', {
  name: this.enteredName,
  rating: this.chosenRating,
});
```

### axios와 공통점과 차이점

1. 공통점
   - 둘다 요청 결과값으로 Promise를 반환한다. 
2. 차이점 
   - **axios**는 자동으로 `Content-Type`헤더를 설정하고 본문 데이터를 JSON으로 **자동 변환**해준다. 
     - 즉, fetch api는 사용자가 모두 설정해줘야한다. 
     - 하지만 axios의 단점은 라이브러리를 설치해야하므로 웹앱의 크기가 증가한다. 

