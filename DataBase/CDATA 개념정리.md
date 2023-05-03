 # CDATA 개념정리
---
 >

## CDATA


- \<![CDATA[ < ]]> 는 '<'를 일반 문자열로 인식시키는 표현 방법이며, <로 대체하여 사용할 수 있다.

  - 하지만 일반적으로 CDATA Section을 사용하여 특수 문자를 포함하는 문자열을 처리하는 것이 권장된다. 
- Mybatis 같은 xml 에서 sql문을 날릴 때 사용한다. 

### <![CDATA\[ "부등호" ]]>사용 하기 

```sql
# 사용하고자 하는 부등호를 <![CDATA[ ]]> 로 감싸준다. 
<select id="selectsortall" resultType="couponVO">
	SELECT * FROM coupon 
	WHERE <![CDATA[ date_format(sysdate(),'%Y%m%d') >= sdate ]]>
	and <![CDATA[ date_format(sysdate(),'%Y%m%d') <= edate ]]>
	ORDER BY sale;
</select>
```

### Thymeleaf를 JavaScript에서 사용하기

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
