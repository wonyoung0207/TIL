# 16일차
---

> SQL 에 대해 학습한다. 

1. **SQL**

   + 데이터베이스는 **schema (db)** 로 되어있다. 
   + 이러한 schema는 sql 문을 통해 제어할 수 있다. 
   + **DDL** 과 **DML**로 나뉜다. 
     + DDL : **Create, Drop, Alter **
       + 테이블 생성, 드랍 제약상황 
     + DML : **Select, Insert, Delete, Update**
       + 테이블의 내용들을 다루는 명령어
   + **ERD**
     + 테이블의 설계를 하는 것. 
     + 요구사항 정의 
   + 따라서 **ERD**를 사용해 요구사항 정의와 함께 테이블을 설계하고, **DDL** 로 구축 후, **DML**을 이용해 데이터를 가공한다. 

2. mysql 명령어

   1. CMD로 접속 
      1. **mysql** -u admin1 -p
      2. **use** databaseName
      3. **show tables**
   2. 접속 후 domp.sql 가져오기
      1. **source** C:\Users\82102\Desktop\backup\domp.sql
         + mysql 에러
           1. **한글이 들어간 sql 파일을 import 하는 경우**
              1. mysql 에서는 한글파일을 import하면 incorrect 에러가 난다. 
              2. 파일의 인코딩 타입을 ANSI로 변경한다 . -> 다름이름으로 저장 -> 타입변경 
   3. 데이터베이스 변경
      1. use databaseName;

3. SQL 사용이유

   1. **데이터의 무결성 체크 **
      + 제약조건을 통해 데이터의 오류를 조기에 찾을 수 있다.  
      + ex) 학생의 학번을 제약조건으로 걸면, 학번 없는 학생이 들어오면 오류낸다. 
   2. **데이터의 독립성**
      + 데이터베이스의 크기, 저장소 변경하더라도 기존에 작성된 응용프로그램은 전혀 영향받지 않음 
      + **따라서 데이터를 독립적으로 보관한다. **
   3. **보안**
   4. **데이터 중복 최소화**
      + 동일한 데이터가 여러개 중복저장되는 것을 방지한다. 
   5. 응용 프로그램 제작 및 수정 간단해짐
   6. **데이터의 안전성 **
      + DBMS가 제공하는 백업. 복원 기능을 이용하여 데이터가 깨지는 문제가 발생할경우 대처할 수 있다. 

4. DBMS 와 RDBMS

   1. **DBMS**
      + Database Management System 의 약자
      + **데이터베이스를 관리하고 운영하기 위한 시스템 또는 소프트웨어**를 말한다. 
   2. **RDBMS**
      + **관계형 데이터베이스**를 뜻한다. 
      + **모든 데이터는 테이블**에 저장되므로 테이블이라는 구조가 RDBMS에서 가장 중요하다.
      + 단점 : 시스템 자원을 많이 차지해서 시스템이 전반적으로 느려지는 것이다.

5. **시스템 구축 절차**

   1. 분석
      + 시스템 분석 또는 요구사항 분석
      + 많은 시간을 필요로 하고 가장 중요한 단계 
   2. 설계 
      + 어떻게 시스템을 구축할 것인지 설계서를 만든다. 
      + 언어, 도구 등 시스템 설계, 프로그램 설계를 진행한다. 
   3. 구현
      + 프로그래머가 코딩을 한다. 
   4. 시험 
   5. 유지보수 

6. **Select 문**

   1. VIew : 가상의 테이블을 생성한다. 

      + ```sql
        CREATE VIEW v_emp
        AS 
        SELECT empno, empname FROM emp;
        # empno와 empname 필드를 가져와 가상의 v_emp view를 만든다. 
        ```

   2. **AS**

      + Alias 의 약자로, **컬럼명을 변경** 

        ```sql
        SELECT empno,empname,salary, salary*2 AS fee FROM emp;
        # salart * 2 으로 출력되는 필드명을 AS를 이용해 fee 로 출력 
        
        ```

      + SELECT FROM WHERE

        ```sql
        SELECT * FROM usertbl
        WHERE addr = '서울'
        AND birthYear >= '1940'
        AND mobile1 IS NULL;
        # ';' 가 나올때까지 줄바꿈을 해도 된다. 
        # and 와 or 를 이용해서 조건을 추가할 수 있다. 
        # IS NULL : NULL 값만 가져온다. 
        ```

   3. Drop

      +  ```sql
      DROP DATABASE IF EXISTS sqldb; -- 만약 sqldb가 존재하면 우선 삭제한다.

   4. **equal**

      + sql 문에서 같다 ''== '' 는 '' = '' 로 써야한다. 

        ```sql
        SELECT * FROM usertbl
        WHERE height = 180;
        ```

        

   5. IN , BETWEEN

      + ```SQL
        # IN 은 해당 내용을 포함한 것을 가져오라고 한다. 
        SELECT * FROM usertbl
        WHERE height IN (182.170,172);
        
        # BETWEEN은 사이의 값을 출력한다. 
        SELECT * FROM usertbl
        WHERE height BETWEEN 180 AND 183;
        ```

        

   6. **Date**

      + ```sql
        SELECT * FROM usertbl
        WHERE date_format(mDate,'%Y') < '2010';
        #날짜 타입같은 경우 년월일 이 완벽하게 있으면 비교가 가능하지만, 따로 있으면 연산하지 못한다. 
        # 따라서 date_format() 함수를 이용해 비교할 수 있다. 
        
        # 2005 와 2008 사이를 출력
        SELECT * FROM usertbl
        WHERE date_format(mDate,'%Y') > '2005' AND date_format(mdate,'%Y') < '2008';
        # 같은 풀이 
        SELECT * FROM usertbl
        WHERE date_format(mDate,'%Y') BETWEEN '2005' AND '2008';
        
        # 2007 과 2009 가 들어가있는 데이터 출력 
        SELECT * FROM usertbl
        WHERE date_format(mDate,'%Y') = '2007' or date_format(mdate,'%Y') = '2009';
        # 같은 풀이 
        SELECT * FROM usertbl
        WHERE date_format(mDate,'%Y') IN ('2007','2009');
        
        
        ```

   7. LIKE

      + 문자열을 검색하기 위해서 사용한다. 
        
      + ```SQL	
        # LIKE는 검색할 때 많이 사용한다. 
        
        # 김이 들어간 모든 것을 출력 
        SELECT * FROM usertbl
        WHERE name LIKE '%김%';
        
        # 김으로 시작하는 외자 
        SELECT * FROM usertbl
        WHERE name LIKE '김_';
        
        # 가운데에 '종' 이 들어가고 3글자인 사람 
        SELECT * FROM usertbl
       WHERE name LIKE '_종_';
        ```

   8. **서브쿼리 ( 쿼리 안에 쿼리 있는것 )**

      + ```sql	
        #서브쿼리 이용
        # 윤종신 회원이 사는 동네의 회원 정보를 출력 
        SELECT * FROM usertbl
        WHERE addr = (SELECT addr FROM usertbl
        WHERE name = '윤종신');
        
        # 윤종신의 키보다 큰 사람을 출력 
        SELECT * FROM usertbl
       WHERE height > (SELECT height FROM usertbl WHERE name = '윤종신');
        ```

   9. 정렬 

      + 정렬은 모든것이 끝나고 맨 뒤에 들어간다. 

        ```sql
        SELECT * FROM usertbl ORDER BY height;# ASC와 같음 
       SELECT * FROM usertbl WHERE addr = '서울' AND birthYear < 1980 ORDER BY height DESC,name DESC;
        ```

   10. 중복 제거 후 출력

      + ```sql
       SELECT DISTINCT addr FROM usertbl;#중복을 제거 
        ```

   11. Limit

       + ```sql
         SELECT * from usertbl
         ORDER BY height
         LIMIT 0, 5;# 위에서부터 0부터 5개만 출력
         
         SELECT * from usertbl
         ORDER BY height
         LIMIT 2,5;# 2에서부터 5개 출력
         ```

