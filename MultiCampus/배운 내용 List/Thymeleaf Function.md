# Thymeleaf  함수

---

> thymeleaf를 이용한 함수 종류 정리 

## Select

```html
<select id="cid" name="cid" class="form-control">
    <option th:each="cate : ${catelist}" th:value="${cate.id}"
            th:text="${cate.name}" th:selected="${cate.id} == ${product.cid}">catename</option>
</select>
```

## Image

```html
<!-- 이렇게 작성하는 것이 더 좋다 . -->
<img th:src="'/img/' + ${product.imgname}">

<!-- 이 방법도 가능  --> 
<img th:src="@{'/img/'+${product.imgname}}">
```

## 링크

```html
<a th:href="@{/product/detail(id=${product.id})}"></a>
<a href="/login" th:href="@{/login}"></a>

<!-- 링크로 두개의 매개변수 날리기  -->
<a href="#" th:href="@{/getproduct(id=${cate.id},name=${cate.name})}"
   th:text="${cate.name}">main1</a>

<!-- 기본적인 링크 삽입-->
<a th:href="@{/index}"/>

<!--  고정된 url과 변수를 함께 사용할 때-->
<a th:href="${'http://www.naver.com' + sports}" target="_blank">링크</a>

<!-- 링크에 파라미터를 보낼 때-->
<a th:href="@{/index(id=${id})}"/>
```

## DateFormat

```html
<div th:text="${#dates.format(product.regdate,'yyyy-MM-dd')}"></div>
```

## Text & Value

```html
<!-- th:text 에 문자열 사용시 공백넣으면 에러남 -->
<!-- 따라서 공백 넣고싶으면 ' ' 안에 넣줘야 함  -->
<div th:text="'$' + ${product.price}" th:value="${product.price}">Price</div>
```

##  if문 (Session 정보이용)

```html
<!-- 자바의 if else와는 다르게 조건문이 동일해야 한다. -->
<li th:if="${session.loginadmin == null}"></li>

<!-- else와 같은 의미. -->
<li th:unless="${session.loginadmin == null}"></li>

<!-- and 나 or 조건 추가 -->
<span th:if="${sports['name'] != null and sports['name'] != ''}">
```

## 파일 Insert

```html
<!-- 넘겨받은 model 객체 중 center가 설정되있으면 해당 controller를 호출하고, 없다면 디폴트로 center controller를 호출  -->
<div th:insert="${center} ? ${center} : center"></div>
```

## 반복문 each

```html
<!-- 반복할 태그에 each문을 사용한다.  -->
<!-- 이때 catelist는 model 로 넘겨받은 List형태이다.  -->
<li th:each="cate : ${catelist}">
    <a href="/" th:href="@{getcate(id=${cate.id})}" th:text="${cate.name}">CateName</a>
</li>
```

## Modal 사용 : data-target & id

```html
<!-- Modal : Day44.md -->
<!-- th:data-target -->
<button type="button" class="btn btn-info btn-lg" data-toggle="modal" th:data-target="'#'+${product.id}">
    Order
</button>
<!-- th:id -->
<div class="modal fade" th:id="${product.id}" role="dialog">
```

## 함수 이용시 매개변수 전달 

```html
<!-- thymeleaf사용시 함수에 값을 넣어줄때 대괄호를 2개 사용한다.  -->
<button th:onclick="addcart([[${p.id}]])" />

<tr th:each="buy : ${buylist}" id="buy" th:onclick="javascript:buy_click([[${buy.pid}]])">
```

## utext ( 사용할 text가 태그형식일 경우 사용 )

```html
<!-- sports = "<span>TEST</span>"; -->
<!-- html 태그가 들어있는 텍스트를 태그로 삽입하고 싶을 때 -->
<p th:utext="${sports}"></p>
```

## 문자열 합치기

```html
<div th:text="'Hello, ' + ${name} + '!'"></div><div th:text="|Hello, ${name}!|"></div>
```

## Switch

```html
<!-- *주의 : 서로 다른 케이스별로 딱 한번씩만 적용이 됨. --> 
<td th:switch="${join.gender}">
<span th:case="'M'" th:text="Male"/>
<span th:case="'F'" th:text="Female"/></td>
```

## 숫자로 반복문 돌리기

```html
<!-- 참고 사이트 : https://ifuwanna.tistory.com/200-->
<i class="fa fa-star" th:each="num : ${#numbers.sequence(1,review.star)}"></i>
```

## onclikc으로 다른주소 바로 보내기

```html
<tr th:each="buy : ${buylist}" id="buy" th:onclick="location.href='/productdetail?id=[[${buy.pid}]]'">
```

## onclick으로 객체와 해당 태그 정보 넘기기 

```html
function update_num(c,count) {
	$('#total'+c.id).text(c.pprice * $(count).val());
}

<input th:id="count" class="form-input stepper-input" type="number" data-zeros="true" th:value="${c.count}" th:onclick="update_num([[${c}]],this)" min="1" max="1000">

<td th:id="'total'+${c.id}">10000</td>
```

## if문으로 null , ' '  처리

```html
<!-- 문자열일 경우 null , '' 처리  -->
<div th:if="${not #strings.isEmpty(addr_list)}"> ... </div>

<!-- list일 경우 null , '' 처리  -->
<div th:unless="${not #lists.isEmpty(addr_list)}">
```

## javascript 안에서 thymeleaf 사용

```javascript
var id = [[${session.loginid}]];
```

## 반복문에서 index 뽑기

```html
<div class="movie-beta__item third--item" th:each="movie,index : ${starSortList}">
    <p th:text="${movie.rcnt} + ' Comments '"></p>
    <p th:text="${index.index}"></p><!--0부터 시작  -->
</div>
```

## JavaScript에서 Thymeleaf 사용하기

```javascript
// javascript에서 thymeleaf 사용하기 위해서는 script태그에 th:inline="javascript" 를 사용해 thymeleaf 를 사용하겠다고 명시해야 한다. 
// 또한 스크립트 내에 '  /*<![CDTATA[*/ /*]]>*/   '를 명시해주어야 하며 타임 리프 변수도 '  /*[[]]*/  '로 감싸주어야 한다.

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

## Select 태그 사용

```html
<!-- select 태그에 thymleaf를 사용할때는 ${} 안의 값에 ? 를 꼭 붙여줘야한다.  --> 
<!-- utext : 사용자에게 보이는 값 , value : 전송시 보내지는 값  --> 
<select th:onchange="totalPrice([[${price}]])" id="selectCoupon">
    <option value="선택"> 선택 </option>
    <option th:each="mycoupon,index : ${mycouponlist}" 
            th:value="${mycoupon?.sale}" th:id="'coupon '+${mycoupon.id}" th:utext="${mycoupon?.text}+'( '+${mycoupon?.sale}+'원 )'"
            ></option>
</select>
```

