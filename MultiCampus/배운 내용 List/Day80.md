# Day80

---

# Final Project

>숫자 텍스트에서 콤마 제거 후 숫자로 변경 
>
>ajax 를 동기로 변경하는 방법 
>
>동기와 비동기 차이점 

### 숫자 텍스트에서 콤마(,) 제거하는 방법

1. replace() 함수 사용하기

   - replace() 함수에 그냥 텍스트를 사용하게 되면 가장 첫번째 값만 변경된다. (replace() 함수는 첫번째로 첫번째로 발견한 값만 치환) 

   -  따라서 정규식을 사용해 변경해줘야한다. 

     ```javascript
     // 일반 텍스트 사용 
     const numberStr = "123,456,789";
     const number = numberStr.replace(",", ""); 
     // 결과값 : 123456,789
     
     // 정규식 사용 
     const numberStr = "123,456,789";
     const number = numberStr.replace(/,/g, "");
     // 정규식은 '/'로 감싸서 작성한다. 즉, '/,/'은 단순히 ','(콤마)를 찾는 정규식이다.
     // 뒤에 붙은 'g'는 문자열 전체에서 콤마를 찾도록 해주는 플래그이다.
     // 결과값 : 123456789
     ```

2.  split(), join() 함수 사용하기

   - split() 함수는 문자열을 첫번째 파라미터(여기서는 ' , ')로 잘라서 배열로 반환하는 함수

   - join() 함수는 배열의 모든 요소를 연결해 하나의 문자열로 만들어 준다.

     ```javascript
     const numberStr = "123,456,789";
     const number = numberStr.split(',').join("");
     // split() => ["123", "456", "789"] 
     // join() => 123456789
     // 결과값 : 123456789
     ```

### Ajax 는 비동기 이다. 

- javascript는 동기이지만 Ajax는 비동기이다. 
- 따라서  javascript 중간에 ajax 를 호출하면 두 함수는 비동기가 되어 어떤것이 먼저 실행되는지 랜덤하게 된다. 

#### ajax를 동기로 바꾸는 방법

```javascript
function barcodeCheck(bc){ 
    var result; 
    $.ajax({
        type : "POST",
        url : 'orcBarcodeCheck',
        async: false, // async를 이용해 동기로 변환한다. 
        data: {'barcode':bc},
        success : function(data) {
            result = data; 
        }
    });
    return result;
}
```

### 동기 VS 비동기 

#### 동기 

- 단일 스레드(싱글 스레드)  ==  동기(Synchronous)
- **한 작업이 실행되는 동안 다른 작업은 멈춘 상태를 유지하고 자신의 차례를 기다리는것** 을 뜻한다. 
-  javaScript는 동기식 언어이다. 자바스크립트는 한 번에 하나의 작업을 수행한다.따라서 **한 작업이 실행되는 동안 다른 작업은 멈춘 상태를 유지하고 자신의 차례를 기다리는**것을 뜻한다. 
- 동기에서 작동방식 
  - 코드가 실행되면 **순서대로** Call Stack에 실행할 함수가 쌓인다.(push)
  - **쌓인 반대 순서**로 함수가 실행된다.(LIFO)
  - 실행이 된 함수는 Call Stack에서 제거된다(pop)

#### 비동기 

- **어떠한 요청을 보내면 그 요청이 끝날 때까지 기다리는 것이 아니라, 응답에 관계없이 바로 다음 동작이 실행되는 방식**
- 비동기에서 작동방식 
  - Call Stack에서 비동기 함수가 호출되면 Call Stack에 먼저 쌓였다가 **Web API(혹은 백그라운드라고도 한다)**로 이동한 후 해당 함수가 등록되고 Call Stack에서 사라진다.
  - Web API(백그라운드)에서 **비동기 함수의 이벤트가 발생**하면, 해당 콜백 함수는 Callback Queue에 push(이동) 된다.
  - 이제 Call Stack이 비어있는지 이벤트 루프(Event Loop)가 확인을 하는데 만약 비어있으면, Call Stack에 Callback Queue에 있는 콜백 함수를 넘겨준다.(push)
  - Call Stack에 들어온 함수는 실행이 되고 실행이 끝나면 Call Stack에서 사라진다. 

#### 용어정리

- Memory Heap이란?
  - 변수와 객체의 메모리 할당을 담당하는 곳을 말한다.
- Call Stack이란?
  - 함수가 호출이 되면 쌓이는 곳
