# Day74

---

# Final Project

>쿠폰 기간 설정 및 로그인 모달 수정
>
>Ticket 구매에 쿠폰 연결 
>
>Thymeleaf에서 select 태그 사용 

## 쿠폰 기간 설정

- mybatis 에서는 비교등호를 그냥 사용하지 못한다. 

- 비교등호 사용 방법

  1. 특수기호로 비교등호 표시 ( lt , gt, ; 등)

     ```sql
     # < : lt
     # > : gt
     # <= : lte
     # <= : gte
     # 사용전 반드시 & 를 사용해 특수기호사용을 표시하고 ; 로 닫아줘야한다.  
     <select id="selectsortall" resultType="couponVO">
     	SELECT * FROM coupon 
     	WHERE date_format(sysdate(),'%Y%m%d') &gte; sdate
     	and date_format(sysdate(),'%Y%m%d') &lte; edate
     	ORDER BY sale;
     </select>

  2.  <![CDATA\[ "부등호" ]]>사용 하기 

     ```sql
     # 사용하고자 하는 부등호를 <![CDATA[ ]]> 로 감싸준다. 
     <select id="selectsortall" resultType="couponVO">
     	SELECT * FROM coupon 
     	WHERE <![CDATA[ date_format(sysdate(),'%Y%m%d') >= sdate ]]>
     	and <![CDATA[ date_format(sysdate(),'%Y%m%d') <= edate ]]>
     	ORDER BY sale;
     </select>
     ```

     

  3. when, if 절로 묶기 

     ```sql
     <select id="selectsortall" resultType="couponVO">
     	SELECT * FROM coupon 
     	WHERE 
     	<if test="sysdate() gt sdate">
     	ORDER BY sale;
     </select>
     ```

## Thymeleaf 에서 Select 태그 사용

- thymeleaf에서 select문을 사용하기 위해서는 value 와 utext 인자를 이용해야 한다. 

- value 는 보내지는 값이고 utext는 사용자에게 보여지는 값이다. 

  ```html
  <select th:onchange="totalPrice([[${price}]])" id="selectCoupon">
      <option value="선택"> 선택 </option>
      <option th:each="mycoupon,index : ${mycouponlist}" 
              th:value="${mycoupon?.sale}" th:id="'coupon '+${mycoupon.id}" th:utext="${mycoupon?.text}+'( '+${mycoupon?.sale}+'원 )'"
              ></option>
  </select>	    
  ```
  
  ```javascript
  var coupon = $("#selectCoupon option:selected").val();// select로 선택된 value를 가져온다. 
  var coupon_id = $("#selectCoupon option:selected").attr('id'); // select로 선택된 id를 가져온다. 
  ```
  
  
