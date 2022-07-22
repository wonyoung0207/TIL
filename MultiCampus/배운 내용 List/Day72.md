# Day72

---

> 쿠폰기능 
>
> Thymeleaf를 JavaScript에서 사용하기
>
> modal 이용 가이드 

# Final Project

>쿠폰기능 
>
>Thymeleaf를 JavaScript에서 사용하기
>
>modal 이용 가이드 

## 쿠폰

- [쿠폰기능 구현 참고 사이트](https://pkgonan.github.io/2020/04/stock)

## 동기 VS 비동기 

1. 동기 ( synchronous : 동시에 일어나는 )
   - 동시에 일어나는 것으로, 요청과 그 결과가 동시에 일어난다는 약속이다. 
   - 요청을 하면 시간이 얼마나 걸리던지 요청한 자리에서 결과가 주어져야 한다. 
   - 장단점 
     - 설계가 매우 간단하고 직관적 이지만 결과가 주어질 때까지 아무일도 하지 못하고 대기해야 한다. 
2. 비동기 ( Asynchronous: 동시에 일어나지 않는)
   - 동시에 일어나지 않는다는 것으로, 요청한 결과는 동시에 일어나지 않을거라는 약속이다. 
   - 요청한 자리에서 결과가 주어지지 않는다. 
   - 장단점
     - 동기보다 복잡하지만 결과가 주어지는데 시간이 걸리더라도 그 시간 동안 다른 작업을 할 수 있으므로 자원을 효율적으로 사용할 수 있는 장점이 있다. 
3. 비동기의 예시
   - 시험날 학생과 선생님의 관계를 보면 된다. 
   - 시험지를 풀고 제출한 학생은 선생님이 체점을 다 할때까지 그자리에서 기다리는 것이 아닌 다른 공부를 하고있을 수 있다. 이것이 '비동기 ' 이다. 

## mysql Date 타입 오류

- [참고 사이트](https://binarywoo.tistory.com/97?category=881703)

- mysql 에 Date타입을 저장할때는 형식을 지정해줘야 sql문으로 저장이 가능하다.

- 따라서 저장할 값의 형태를 VO 객체의 변수에서 @DateTimeFormat(pattern = "yyyy-MM-dd")  를 이용해 어노테이션 형식을 지정해 줘야한다. 

  ```java
  @NoArgsConstructor
  @AllArgsConstructor
  @Getter @Setter
  public class BbsRequest {
      private Integer id =0;
      private String title;
      private String contents;
      private String password;
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      private Date fromDate;
  }
  ```


## Thymeleaf를 JavaScript에서 사용하기

- [참고사이트](https://mimah.tistory.com/entry/Thymeleaf-JavaScript%EC%97%90%EC%84%9C-%ED%83%80%EC%9E%84-%EB%A6%AC%ED%94%84-%EB%B3%80%EC%88%98-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0)

- 단순하게 \<script> 태그만 작성한 경우 타임 리프 변수를 자바 스크립트 코드 내에서 사용할 수는 없다.

- 따라서 타임 리프 변수 ${}를 \<script> 태그 내에서 사용하기 위해서는 **th:inline="javascript** 를 명시해주어야 하고, 스크립트 내에 '  /\*<![CDTATA[*/ /*]]>\*/   '를 명시해주어야 한다. 또한 타임 리프 변수도 '  /\*[[]]\*/  '로 감싸주어야 한다.

  ```javascript
  <script th:inline="javascript">
      /*<![CDATA[*/
      function search() {
          const searchWord = document.getElementById('searchWord').value;
          const page = /*[[${paging.page}]]*/;
  
          location.href='list?page=' + page + '&searchWord=' + searchWord;
      }
      /*]]>*/
  </script>
  ```

## BootStrap 의 Modal 함수이용해서 띄우기

- [참고사이트](http://daplus.net/javascript-jquery%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-%EB%B6%80%ED%8A%B8-%EC%8A%A4%ED%8A%B8%EB%9E%A9-%EB%AA%A8%EB%8B%AC-%EC%B0%BD%EC%9D%84-%EC%97%AC%EB%8A%94-%EB%B0%A9%EB%B2%95%EC%9D%80/)

- [modal에관한 가이드](https://getbootstrap.com/docs/3.4/javascript/#modals)

- BootStrap 에서는 Modal 을 간단하게 컨트롤 할 수 있는 함수를 제공한다. 

- 이때 주의할 점은 Bootstrap 에서 modal 사용에 필요한  \<script> 태그를 중복해서 사용하면 안된다. 

  ```javascript
  // 첫번째 방법 - on 함수 이용 
  $('#myform').on('submit', function(ev) {
      $('#my-modal').modal({
          show: 'false'
      });
  });
  
  // 두번째 방법 - 직접 show, hide 이용 
  $('#Loginerror').modal('show'); 
  $('#modal'+couponid).modal('hide');
  ```
