# Day51

---

> 세미프로젝트 Day08
>

# 세미프로젝트 Day08

> 개인 개발 
>
> 개발 내용 : Mypage ( 활동정보-Review, 회원정보-회원정보관리, 비밀번호 변경, 배송지 관리 UI   )
>

## table의 tr 에 click 이벤트 적용

- \<a> 태그 말고는 click 이벤트가 적용되지 않는다. 

- 따라서 onclick 이벤트를 적용시켜야 한다. 

  ```html
  <!-- javascript 의 buy_click() 함수를 호출한다. -->
  <tr th:each="buy : ${buylist}" id="buy" th:onclick="javascript:buy_click([[${buy.pid}]])">
  ```

- onclick으로 바로 다른 주소로 보내기 

  ```html
  <tr th:each="buy : ${buylist}" id="buy" th:onclick="location.href='/productdetail?id=[[${buy.pid}]]'">
  ```


## a태그 동작 못하게 하는 방법

- [참고 사이트 ](https://jrabbit.tistory.com/78)

- href에서 발생한 액션을 실행시키지 않는다'라는 의미를 가진다. 

  ```html
  <a href="#" onclick="return false;">test</a>
  ```

## Confirm

- 예/아니오 의 선택을 할 수 있는 기능 

- 예를 누르면 true, 아니오를 누르면 false를 리턴한다. 

  ```javascript
  var remove = confirm('정말로 삭제하시겠습니까?');// 취소를 누르면 false 리턴 
  ```
