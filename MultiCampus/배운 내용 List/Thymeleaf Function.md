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
<img id="main" th:src="@{'/img/'+${product.imgname}}">
```

## 링크

```html
<a th:href="@{/product/detail(id=${product.id})}"></a>
<a href="/login" th:href="@{/login}"></a>
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

## Session 정보이용 if문

```html
<!-- 자바의 if else와는 다르게 조건문이 동일해야 한다. -->
<li th:if="${session.loginadmin == null}"></li>

<!-- else와 같은 의미. -->
<li th:unless="${session.loginadmin == null}"></li>
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





