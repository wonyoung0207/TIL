# 14일차
---

> 자바를 이용해 sql문 처리 

1. db의 값 변경 vs 값 출력

   + sql을 사용해서 db와 연동시 주의할점은 요청 결과를 받아올 떄, 해당 결과가 테이블이 변경된 값을 가져오면 **executeUpdate()** 이고, 변경하지 않고 가져온다면 **executeQuery()** 를 사용한다. 

   + 이떄,**executeQuery() **를 사용하면,**ResultSet**에 결과를 담고 결과 담은 곳에 **next()** 하여 한칸 이동한 뒤에 써야한다. 

     + ```java
       // executeUpdate 사용할때 사용. ( insert, delete, update 쿼리 사용시 사용됨)
       PreparedStatement ps = null;
       int result = ps.executeUpdate();
       
       // executeQuery 사용할때 사용. ( Select )
       ResultSet rs = null;
       rs = ps.executeQuery();
       rs.next();//한칸을 이동하고 꺼내야 한다.
       //next 를 통해 행을 바꿀 수 있다.
       //바로 사용할 경우, 아무것도 가르키지 않기 때문에 next를 해서 행을 이동시켜야 한다.  
       ```

     + **결과를 가져올경우, ResultSet을 이용해서 받아야 출력할 수 있다. executeUpdate는 값을 가져오는 것이 아닌 변경만 하기 때문에 성공 여부만 리턴하여 int로 받아도 된다**. 

     + **ResultSet**

       + 모든 데이터를 한번에 가져올 수 없기 때문에 cursor 개념을 가진다. 
       + 커서란, resultSet객체가 가져올 수 있는 행의 위치를 지정한다. 
       + 처음 커서의 위치는 필드값 전에 위치하기 때문에 next() 메소드로 이동후 출력해야한다. 
       + next()의 리턴타입은 boolean으로, 다음 값이 있으면 true, 없으면 false를 리턴한다. 

   1. **INSERT INTO** table VALUES(?,?,?)
   2. **DELETE FROM** table WHERE id =?
   3. **UPDATE** table **SET** id=?,name =?
   4. **SELECT** * **FROM** table WHERE id=?

2. SQL 문 숫자 자동입력 & 날짜 입력 

   + ```sql
     CREATE TABLE product(
     	id INT primary KEY auto_increment,
         regdate DATE
     );
     
     INSERT INTO product VALUES(NULL, SYSDATE());
     ```

   + auto_increment 

     + 숫자를 증가시키면서 자동으로 입력된다. 

   + SYSDATE()

     + sql에 내장되어있는 시간을 출력하는 함수이다. 

3. 기본키 vs 외래키

   1. **기본키(privary key)**
      + 해당 테이블의 **유일한 값**으로 , **중복이 불가**하다. 
      + 주로 기본키를 기준으로 값을 찾고 변경힌다.
      + premary key로 지정하더라도 값을 변경할 수 있다. 중복만 안될 뿐이다. 
   2. **외래키(poreign key)**
      + 두개의 테이블을 연관지을 떄 사용된다. 
        + ex) 도시와 나라, 고객과 주문정보
      + **외래키**의 값이 부모테이블의 기본키일 필요는 없지만 **테이블에서 값이 유일**해야한다. 
      + 외래키의 값은 **부모 테이블에 존재하는 키 값만** 넣을 수 있다. 
      + 외래키는 부모 테이블의 유일한 값을 참조한다. (주로 부모 테이블의 기본키를 참조)
