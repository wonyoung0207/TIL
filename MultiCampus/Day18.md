# 18일차
---

> SQL 문에 대해 공부한다. 
>
> ERD 에 대해 학습한다. 

## 1. ERD 그리기
   1. [ERD그리기 사이트](https://www.erdcloud.com/)

## 2. **JOIN**

   + 테이블에 서로 **연관관계가 존재**하면,  테이블들을 엮어 한번에 출력할 수 있다.
   + join을 사용하기 위해서는 테이블 사이에 관계가 있어야만 사용할 수 있다. 
   + 테이블을 **연관관계로 관리하는 이유**
     1. **데이터를 최적화** 하기 위해서
     2. **데이터의 일체화**

   + **INNER JOIN**

     + ```sql
       # 세개의 테이블에 있는 정보를 가져옴
       SELECT c.id, cu.name, i.name, i.price, (c.num * i.price) AS totalPrice FROM cart c 
       INNER JOIN cust cu ON c.custid = cu.id
       INNER JOIN item i ON c.itemid = i.id;
       ```


      + **OUTER JOIN**
       
        + 어느 한쪽에만 데이터가 존재할 떄 사용한다. 
       
        + 기준을 정해 출력할 수 있다. 
       
        + ```sql
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
       
      + CROSS JOIN 
       
        + 한쪽 테이블의 모든 행들과 다른쪽 테이블의 모든 행을 조인시키는 것 
        + 잘 사용하지 않는다. 

   + **SELF JOIN**
   
        + 테이블 하나를 이용해 자기 자신과 조인하는 것
     
        + 자기 자신을 가리키게 해서 상위 카테고리(어떤 부류인지)가 무엇인지 알게할 수있다.
     
        + ```sql
          # 사원 이름과 매니저 이름을 출력 단, 모든 직원을 출력
          SELECT e1.empname, e2.empname FROM emp e1
          LEFT OUTER JOIN emp e2 ON e1.manager = e2.empno;
          ```
        
   + **ALTER**
   
        + ```sql
             # 테이블 생성시 기본틀만 만들고, 그 후에 ALTER로 기능들을 추가해준다. 
             CREATE TABLE cart(
                      id INT,
                      custid CHAR(5),
                      itemid INT,
                      num INT,
                      regdate DATE
             );
             
             # alter를 이용해 기본키를 추가시켜준다. 
             ALTER TABLE cust ADD PRIMARY KEY (id);
             ALTER TABLE cart ADD PRIMARY KEY (id);
             ALTER TABLE cart MODIFY id INT AUTO_INCREMENT;
             ALTER TABLE cart AUTO_INCREMENT = 100;
             
             # 제약조건을 주는데, itemid를 추가하는데 이것을 item테이블의 id로 만든다. 
             ALTER TABLE cart ADD CONSTRAINT FOREIGN KEY (itemid) REFERENCES item(id);
             ```
      
             