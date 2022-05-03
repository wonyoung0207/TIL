# 19일차
---

> SQL 문에 대해 공부한다. 
>
> 

1. **ALTER TABLE**  제약조건
   + **기본키**
     
     + 데이블에 존재하는 많은 행의 데이터를 구분할 수 있는 식별자를 기본키 라고 한다. 
     
     + ```sql
       ALTER TABLE product ADD CONSTRAINT PRIMARY KEY(id);
       ```
     
   + **외래키**
     
     + 두 테이블 사이의 관계를 선언함으로써 데이터의 무결성을 보장해주는 역할을 한다. 
     
     + ```sql
       ALTER TABLE product ADD CONSTRAINT FOREIGN KEY(cid) REFERENCES cate(id);
       ```
     
   + UNUQUE 
     + 중복되지 않는 유일한 값이다. 

     + primary key외 다른점은 NULL을 허용한다는 것이다. 
   
     + ```sql
       ALTER TABLE cate ADD CONSTRAINT UNIQUE(name);
       ```
   
   + **CHECK**
     
     + **입력되는 데이터를 점검**하는 기능을 한다. 
     
     + EX ) 키에 마이너스 값이 들어올 수 없게 만드는 것 
     
     + ```sql
       ALTER TABLE product ADD CONSTRAINT CHECK (price > 0);
       # 제약조건으로, 마이너스가 들어오지 않게 한다. 
       ```
     
   + default 정의
     + 값을 입력하지 않았을 때**자동으로 입력되는 기본값**을 정의 
   
     + ```sql
       ALTER TABLE product MODIFY id INT AUTO_INCREMENT;
       ALTER TABLE product AUTO_INCREMENT = 1000;
       
       # cate 의 name 필드를 varchar(30)으로 변경한다. 
       ALTER TABLE cate CHANGE COLUMN name name VARCHAR(30) NOT NULL ; 
       
       # 컬럼을 수정하겠다. 
       ALTER TABLE cust ALTER COLUMN addr SET DEFAULT 'Seoul';
       
       ```
   
2. **CASCADE**

   + 어떤 테이블의 내용을 변경할 때, foregin key로 연결되어있는 다른 테이블에도 영향을 줄수 있게 만든다. 

   + ```sql
     ALTER TABLE cart ADD CONSTRAINT FOREIGN KEY(uid) REFERENCES cust(id)
     ON DELETE CASCADE
     ON UPDATE CASCADE;
     ```

3. **VIEW**

   + **가상테이블**로, 정제된 데이터를 가지고 있어 편리하게 사용할 수 있다. 
   + 테이블의 내용이 변경되었을 때, **가상테이블의 내용**은 자동으로 변경되어 보여진다. 
   + 또한 가상테이블의 내용을 변경했을때 타겟 테이블의 내용도 바뀐다. 