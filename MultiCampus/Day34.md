# 34일차

------

> Spring 과 MyBatis를 이용해 데이터 베이스와 연결하여 데이터를 출력한다.
>
> CRUD 및 다양한 SQL문을 추가해본다. 

# Product Class

1. CRUD 와 searchName, getRate 기능 구현 

   1. searchName

      - database에 저장되어있는 name 중 글자를 입력한것이 포함된 모든 것을 가져온다. 

      - code

        ```xml
        <!-- MyBatis에서는 특수문자 사용시 CONCAT으로 하나의 String으로 만들어서 보내줘야한다.  -->
        <select id="searchName" parameterType="String" resultType="product">
            SELECT * FROM product WHERE NAME LIKE CONCAT('%',#{name},'%')
        </select>
        ```

   2. getRate

      - 입력한 rate보다 높은 rate를 가져온다. 

      - code

        ```xml
        <select id="getRate" parameterType="Double" resultType="product">
            SELECT * FROM product WHERE RATE > #{rate}
        </select>
        ```


# Item Class

## 제품 금액이 특정 구간인 item 검색

- ### parameterType 갯수 오류

  - mapper.xml 파일에서 select, delete, update, insert 할때 parameterType으로는 한개의 매개변수밖에 받지 모한다. 

  - 해결방법

    - [참고사이트](https://heewon26.tistory.com/26)

    1. map 으로 넣어주기 (좀더 쉬움)
       - parameterType="Map" 으로 받아서 Map의 Key로 SQL문을 불러주면 된다. 

    2. vo객체에 담아서 객체로 넘겨주기 (객체를 만들어서 번거로움)

    3. param 이용하기 

       ```xml
       <select id="searchPrice1" parameterType="int" resultType="item">
           SELECT * FROM item WHERE PRICE BETWEEN #{param1} AND #{param1} 	
       </select>
       ```

  - code

  ```java
  public static Map<String,Integer> setSearchPrice() {
      Map<String,Integer> map = new HashMap<String,Integer>();
      Scanner input = new Scanner(System.in);
      int price1=0 ,price2=0, priceSwitch=0;
  
      System.out.println("찾을 가격의 범위를 설정해주세요.(최소와 최대값을 입력해주세요)");
      price1 = input.nextInt();
      price2 = input.nextInt();
      if(price1 > price2) {// 순서 맞춰주기 
          priceSwitch = price1;
          price1 = price2;
          price2 = priceSwitch;
      }
  
      map.put("price1", price1);
      map.put("price2", price2);
  
      return map;
  }
  ```

  ```xml
  <select id="searchPrice" parameterType="Map" resultType="item">
      SELECT * FROM item WHERE PRICE BETWEEN #{price1} AND #{price2} 	
  </select>
  ```



## 날짜를 입력하면 해당 날짜 이후에 등록된 제품 검색

1. ### 원하는 날짜 지정하는 방법

   - Calendar을 사용해서 날짜를 지정 

   - code

     ```java
     public static Date setDate() {
         Scanner input = new Scanner(System.in);
         int year=0, month=0, day=0;
     
         System.out.println("년, 월, 일 순으로 입력해 주세요.");
         year = input.nextInt();
         month = input.nextInt();
         day = input.nextInt();
     
         Calendar cal = Calendar.getInstance();
         cal.set(year, month-1, day);
     
         return new Date(cal.getTimeInMillis());
     }
     ```

     ```xml
     <!-- Date형은 Date_format으로 해야 비교가 가능하다.  --> 
     <select id="searchDate" parameterType="Date" resultType="item">
         SELECT * FROM item WHERE DATE_FORMAT(REGDATE,'%YYYY %mm %dd') > DATE_FORMAT(#{date},'%YYYY %mm %dd') 
     </select>
     ```
