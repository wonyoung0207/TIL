# JOIN 

- JOIN 이용방법

  1. INNER JOIN 테이블 ON A필드 = B필드
  2. INNER JOIN 테이블 USING (공통필드)

- JOIN 종류

  - **INNER JOIN**

    + JOIN한 테이블의 모든 필드 정보를 이용할 수 있다. 

      ```sql
      # 세개의 테이블에 있는 정보를 가져옴
      SELECT c.id, cu.name, i.name, i.price, (c.num * i.price) AS totalPrice FROM cart c 
      INNER JOIN cust cu ON c.custid = cu.id
      INNER JOIN item i ON c.itemid = i.id;
      ```

  - **OUTER JOIN = ( LEFT, RIGHT JOIN )** 

    + 어느 한쪽에만 데이터가 존재할 떄 사용한다. 

    + 기준을 정해 출력할 수 있다. 

      ```sql
      # outer join -> 한쪽에 있는 것을 꺼낼때 사용한다. 
      SELECT * FROM emp e
      RIGHT OUTER JOIN title t ON e.titleno = t.titleno;# 오른쪽 title을 기준으로 출력한다. 
      
      SELECT * FROM emp e
      LEFT OUTER JOIN title t ON e.titleno = t.titleno;# 왼쪽 emp 기준으로 출력한다. 
      
      # FULL OUTER : 첫번째 결과와 두번째 결과를 합친다. 
      SELECT * FROM emp e
      LEFT OUTER JOIN title t ON e.titleno = t.titleno
      UNION
      SELECT * FROM emp e
      RIGHT OUTER JOIN title t ON e.titleno = t.titleno;
      ```

