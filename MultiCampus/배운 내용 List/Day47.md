# Day47

---

>주소 API 사용법
>
>JavaScript 정규식 사용법

## 주소 API

> [Kakao 주소 API](https://postcode.map.daum.net/guide)

- 카카오에서 제공하는 Daum 우편번호 서비스를 이용하여 간편하게 우편번호 검색, 도로명 주소 입력기능을 만든다. 

- 사용 방법

  1. 사용하고자 하는 곳에 해당 script를 넣는다. 

     ```javascript
     <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
     ```

  2. new daum.Postcode({ }).open()  함수를 사용한다. 

     ```javascript
     <script>
     function sample6_execDaumPostcode() {
         new daum.Postcode({
             oncomplete: function(data) {
                 // daum 에서 제공하는 우편번호 서비스를 이용하는 함수이다. 
                 // 팝업창으로 우편번호 검색창이 띄워지고 주소를 클릭하면 oncomplete 함수가 실행된다. 
                 // 따라서 data에는 유저가 선택한 주소의 모든 값이 들어가게 된다 
                             
                 var addr = ''; // 주소 변수
     
                 if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                     addr = data.roadAddress;
                 } else { // 사용자가 지번 주소를 선택했을 경우(J)
                     addr = data.jibunAddress;
                 }
                 // 주소 정보를 해당 필드에 넣는다.
                 document.getElementById("sample6_address").value = addr;
                 // 커서를 상세주소 필드로 이동한다.
                 document.getElementById("sample6_detailAddress").focus();
             }
         }).open();
     }
     </script>
     ```
  
  3. kakao 에서 제공하는 방법에는 5가지가 존재한다. 
  
     1. 팝업을 이용하여 도로명 주소와 지번 주소 모두 보여주기  (브라우저 팝업으로 띄워줌 ) 
     2. 사용자가 선택한 값 이용  ( 브라우저 팝업으로 띄워줌 )
     3. iframe을 이용하여 레이어 띄우기  ( iframe팝업으로 띄워줌 )
     4. iframe을 이용해 페이지에 삽입. ( 팝업이 아닌 페이지 자체에서 처리 )
     5. 주소를 선택하면 지도도 함께 보여줌. 

## 정규식

> [정규표현식 개념 정리](https://inpa.tistory.com/entry/JS-%F0%9F%93%9A-%EC%A0%95%EA%B7%9C%EC%8B%9D-RegExp-%EB%88%84%EA%B5%AC%EB%82%98-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-%EC%89%BD%EA%B2%8C-%EC%A0%95%EB%A6%AC)

### 정규표현식(Regular Expression) 정의

- 문자열에서 특정 내용을 찾거나 대체 또는 발췌하는데 사용

- 장단점
  - 장점
    - 반복문과 조건문을 사용한 복잡한 코드도 정규표현식을 이용하면 매우 간단하게 표현할 수 있다.
  - 단점
    - 주석이나 공백을 허용하지 않고 여러가지 기호를 혼합하여 사용하기 때문에 가독성이 좋지 않다.

### 사용방법

1. 예시 
   - /abc/i
     - / : 시작, 종료 기호
     - i : **flag** 로, 검색시 어떻게 검색할지를 정하는 것 . ( i : 대소문자를 구별하지 않고 검색한다는 의미 )
     - abc : 패턴을 의미 ( 찾는 문자열에서 'abc' 문자 패턴을 찾는다. )
2. 매칭 패턴 ( 문자, 숫자 , 기호 등 )
   - 0-9 
     - 숫자 범위 지정 ( 0 ~ 9 ) 사이의 값만 찾는다. 
   - \d
     - 숫자를 찾는다. 
   - []
     - 괄호안의 문자들 중 하나 . or 처리로 보면 됨
     - ex ) [다-바] => 다, 라, 마, 바 를 찾는다. 
     - [^문자] => [ ] 안에 들어간 문자를 제외하고 찾는다. 
   - \w
     - 모든 문자 [A-z0-9] 와 _(언더바) 까지 포함
   - +
     - 최소 한개 or 여러개 
   - \[ ] 
     - 그룹을 뜻함 
3. 메소드
   - match
     - ("문자열").match(/정규표현식/플레그)
     - "문자열"에서 "정규표현식" 에 매칭되는 항목들을 배열로 반환 
   - replace
     - ("문자열").replace(/정규표현식/, "대체문자열")
     - "정규표현식" 에 매칭되는 항목을 "대체문자열" 로 변환
   - split
     - ("문자열").split(정규표현식)
     - "문자열" 을 "정규표현식" 에 매칭되는 항목으로 쪼개에 배열로 반환
   - test
     - (정규표현식).test("문자열")
     - "문자열"이 "정규표현식"과 매칭되면 true, 아니면 false
   - exec
     - (정규표현식).exec("문자열")
     - match 메소드와 유사. (단 , 무조건 첫번째 매칭 결과만 반환 )

### 예제

- 이메일주소 정규표현식 

  ```javascript
  const text = 
  `http://dogumaster.com http://google.com 010-1111-2222 02-333-7777 curryyou@aaa.com`; 
   
  text.match(/[\w\-\.]+\@[\w\-\.]+/g); // [ 'curryyou@aaa.com' ]
  // "/" 는 시작과 끝을 나타냄
  // 맨 뒤의 g는 플래그를 나타냄 ( g : 문자열 내의 모든 패턴을 검색 )
  // [] 는 그룹을 뜻함 
  // + 는 최소한개 or 여러개 를 뜻한다. 
  ```

  - **따라서 @ 를 사이에 두고 있는 모든 문자열을 찾는다. -> 띄어쓰기 구분함 **

  - 1번그룹,2번그룹 조건 ( 모든문자(\w), -문자(\-), .문자(\.) ) 

    ```
    [\w\-\.]
    ```

- 전화번호 정규표현식

  ```javascript
  const text = 
  `http://dogumaster.com http://google.com 010-1111-2222 02-333-7777 curryyou@aaa.com`; 
   // 다른방법 :  /^\d{3}-\d{3,4}-\d{4}$/;
  text.match(/\d{2,3}-\d{3,4}-\d{4}/g); // [ '010-1111-2222', '02-333-7777' ]
  ```

- match되는게 없으면 null을 반환 .

  1) \d{2,3} => 숫자 2~3개로 시작하고,
  2) \- => 다음에 하이픈(-)이 오고
  3) \d{3, 4} => 다음에 숫자가 3~4개 오고,
  4) \- => 다음에 하이픈(-)이 오고,
  5) \d{4} => 다음에 숫자가 4개 온다.
  6) g => 매칭되는걸 모두 다 찾는다(플래그)



