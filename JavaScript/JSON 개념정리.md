# JSON 개념정리 

---

>[참고 사이트1](https://namu.wiki/w/JSON)
>
>[참고 사이트2](https://velog.io/@surim014/JSON%EC%9D%B4%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80)
>
>[참고 사이트3](https://araikuma.tistory.com/339)

## JSON

### 정의

- **JavaScript Object Notation** 의 약어이다. 
- Javascript 객체 문법으로 구조화된 데이터를 표현하기 위한 **경량의 DATA문자기반** 데이터 포멧이다.
  - 웹 어플리케이션에서 데이터를 전송할 때 일반적으로 사용
  - 용량이 작아 XML을 대체해서 데이터 전송 등에 많이 사용
- XML파일과 달리 태그를 사용하지 않아서 용량이 적고 직관적이다. 

### XML vs JSON

- 데이터 표현방법은 많지만, 대표적으로 XML과 JSON을 비교해본다. 

1. XML
   - 데이터 값 양쪽으로 태그가 있다.
     - HTML을 근본으로 했기에 태그라는 것이 없을 수가 없는데, 그 태그를 줄인다 해도 최소한 표현하려면 양쪽에 몇글자씩이 있어야 한다.
2. JSON
   - 태그로 표현하기 보다는 중괄호({}) 같은 형식으로 하고, 값을 ','로 나열하기에 그 표현이 간단하다.

### 문법

```json
{
  "employees": [
    {
      "name": "Surim",
      "lastName": "Son"
    },
    {
      "name": "Someone",
      "lastName": "Huh"
    },
    {
      "name": "Someone else",
      "lastName": "Kim"
    } 
  ]
}
{
    "book": [{
        "id": "01",
        "language": "Java",
        "edition": "third",
        "author": "Herbert Schildt"
    }, {
        "id": "07",
        "language": "C++",
        "edition": "second",
        "author": "E.Balagurusamy"
    }]
}

```

- JSON 형식은 자바스크립트 객체와 마찬가지로 **key / value가 존재**할 수 있으며 **key값이나 문자열은 항상 쌍따옴표를 이용하여 표기**해야한다.
- **객체, 배열 등의 표기를 사용**할 수 있다.
- 일반 자바스크립트의 객체처럼 **원하는 만큼 중첩시켜서 사용**할 수도 있다.
- JSON형식에서는 **null**, **number**, **string**, **array**, **object**, **boolean**을 사용할 수 있다.
  - number 데이터타입에는 " " 를 쓰지 않아도 된다. 

### 특징

1. **서버와 클라이언트 간의 교류**에서 일반적으로 많이 사용된다.
2. **자바스크립트 객체 표기법**과 아주 유사하다.
3. 자바스크립트를 이용하여 JSON 형식의 문서를 쉽게 **자바스크립트 객체로 변환**할 수 있는 이점이 있다.
   - **JSON 문서 형식은 자바스크립트 객체의 형식을 기반으로 만들어졌다.**
4. 자바스크립트의 문법과 굉장히 유사하지만 **텍스트 형식일 뿐**이다.
5. 다른 프로그래밍 언어를 이용해서도 쉽게 만들 수 있다.
6. 특정 언어에 종속되지 않으며, 대부분의 프로그래밍 언어에서 JSON 포맷의 데이터를 핸들링 할 수 있는 라이브러리를 제공한다.

### 단점 

1. 주석을 지원하지 않는다. 그래서 설정 파일을 JSON으로 작성한다.
2. 데이터 타입을 강제하려면 JSON 스키마로 보완해야 한다.
3. 날짜, 시간 데이터를 지원하지 않는다

### JSON으로 가져올 수 있는 데이터 

- JSON으로 가져올 수 있는 데이터는 해당 자바스크립트가 로드된 서버의 데이터에 한정된다.
  - 예를 들어, http://kwz.kr/json.js에서 불러올 수 있는 데이터는 kwz.kr 서버에 존재하는 것만 가능하다. (구글 데이터를 불러온다거나 네이버 데이터를 불러온다거나 할 수 없다.)
- JSON은 단순히 **데이터 포맷**일 뿐이며 그 데이터를 불러오기 위해선 **XMLHttpRequest()라는 JavaScript 함수를 사용**해야 하는데 이 함수가 **동일 서버에 대한 것만 지원**하기 때문이다. ( JSONP 또는 프락시 역할을 하는 서버쪽 Script 파일로 가능하게도 할 수 있다.)

### JSON 을 JavaScript Object으로 변경하는 방법

```javascript
var jsonText = '{ "name": "Someone else", "lastName": "Kim" }';  // JSON 형식의 문자열
var realObject = JSON.parse(jsonText);
var jsonText2 = JSON.stringify(realObject);

console.log(realObject);
console.log(jsonText2);
```

1. **JSON.parse( JSON 형식의 문자열 )** : JSON 형식의 텍스트를 자바스크립트 객체로 변환한다.
2. **JSON.stringify( JSON 형식의 문자열로 변환할 값 )** : 자바스크립트 객체를 JSON 텍스트로 변환한다.