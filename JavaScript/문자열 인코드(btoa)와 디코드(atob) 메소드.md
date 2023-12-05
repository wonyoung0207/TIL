## JS 문자열 인코드( btoa ) 와 디코드(atob) 

---

>[참고 사이트1](https://pro-self-studier.tistory.com/106)

## btoa 와 atob

### base64

- base64 는 64진법 데이터이다. 
- Base64는 데이터가 전송중에 수정되지 않고 그대로 전송되는 것을 보장
- 문자를 base64로 인코드, 디코드 하는 메소드가 btoa(), atob() 이다. 

### 인코딩과 디코딩

- 자바스크립트에서 문자열을 base64로 **인코드** 하려면 window 객체(전역객체) 의 **btoa 메소드**를 사용하면 되고

- base64 로 인코드된 문자열을 **디코드** 하려면 window 객체의 **atob** 메소드를 이용

  - 즉, 문자열을 바이트 코드로 만드는것이 인코드이고, 바이트 코드를 문자열로 변경하는것이 디코드이다. 

  ```js
  var str = "The quick brown fox jumps over the lazy dog.";
   
  var encodeStr = window.btoa(str); // 인코드 
  console.log(encodeStr);
  // VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZy4=
   
  var decodeStr = window.atob(encodeStr);
  console.log(decodeStr);
  // The quick brown fox jumps over the lazy dog.
  ```

  

