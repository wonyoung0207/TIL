# 17일차
---

> SQL 문에 대해 공부한다. 
>

## 1. SELECT 문

   1. **Group By**

      + 컬럼 데이터를 그룹화 할 수 있는 명령어 

      + **" ~별 "**이 나오면 무조건 그룹 해야함

        + ex) groupName 별 구매 고객의 수를 구하시오. 

      + 해당 명령어 사용 하면 select * 의 형식이 아닌 그룹바이 한 "**필드명 , 그룹바이 함수**" 형식으로 입력해야한다. 

      + group by에서 조건을 사용하려면 where 이 아닌 having을 사용한다. 

      + group BY 의 순서도 중요하다. 

        + ex) GROUP BY num. groupName 
          + num 으로 먼저 그룹화하고, 그다음 groupName으로 그룹화 한다. 

      + ```SQL
        SELECT userID, ROUND(AVG(price),1) AS pavg FROM buytbl
        GROUP BY userID
        HAVING pavg > 100;
        
        # userID 와 groupName을 이용해 그룹화 하고, userID 에 BBK, KBS 가 들어가있는 것만 출력 
        SELECT userID, groupName , SUM(price*amount) AS usum FROM buytbl
        GROUP BY userID, groupName
        HAVING userID IN ('BBK','KBS') 
        AND
        groupName IS NOT NULL
        ORDER BY userID;
        
        ```

   2. **COUNT **

      + 테이블의 숫자를 나타낼 수 있다. 중복제거**( distinct )** 를 잘 활용해야 한다. 

      + ```sql
        #사이트에서 구매한 고객의 구매건수 -> userID가 중복되어있어 Distinct 해야함
        SELECT count(DISTINCT(userID)) FROM buytbl;
        
        # groupName 별 구매 고객의 수 를 구하시오
        SELECT groupName, COUNT(DISTINCT(userID)) FROM buytbl
        GROUP BY groupName;
        
        # 회원 중 폰을 가지고 있는 회원의 수
        # count 함수 사용시 null 을 자동으로 제외하고 센다. 
        SELECT COUNT(mobile1) FROM usertbl;
        ```

   3. **CONCAT**

      + 두개의 필드를 더해서 가져온다..

      + ```sql
        SELECT CONCAT(prodName, ' | ' ,groupName ) from buytbl;
        
        ```

   4. IF절

      1. IF(조건, 수식1, 수식2)

         ```sql
         SELECT userID, price*amount AS tt, IF(price*amount > 500, 'Hight', 'low') FROM buytbl;
         ```

   5. CASE 문

      + ```sql
        SELECT userID, amount, 
        CASE
        	WHEN amount >= 1 AND amount < 2 THEN 'C'
        	WHEN amount >= 2 AND amount < 4 THEN 'B'
            WHEN amount >= 4 AND amount < 6 THEN 'A'
            ELSE 'NONE'
        END AS level
        FROM buytbl;
        ```

   6. FORMAT

      + 소수점 아래 4에서 반올림 하고 3자리마다 , 표시

      + ```SQL
        SELECT FORMAT(123456.12345,4);
        
        ```

   7. DATE

      + ADDDATE, SUBDATE

      + **DATE 자료형**으로 저장시 YYYY-MM-DD의 형태로 저장된다. 따라서 **시,분,초** 까지 저장하기 위해서는 **DATETIME 자료형**을 사용해야 한다. 
      
        ```sql
        SELECT mDate,adddate(mdate, interval 30 DAY),
        subdate(mDate, INTERVAL 30 DAY)
         FROM usertbl;
        
        
        SELECT CURDATE();# 날짜만
        SELECT CURTIME(); #시간만
        SELECT NOW(); #날짜 시간 둘다
        SELECT SYSDATE();#날짜 시간 둘다
        SELECT YEAR(sysdate());
        ```

      + datediff() 와  period_diff
      
        + ```sql
          # 오늘 날짜를 기준으로 몇일이 지났는지 날짜1- 날짜2 의 일수를 계산 
          SELECT mdate, DATEDIFF(NOW(), mDate) FROM usertbl;
          
          # 오늘 날짜를 기준으로 몇달이 지났는지 날짜1- 날짜2 의 개월 수를 구한다. 
          # 이 함수를 사용하려면 형태가 YYYYmm 이여야 한다. 
          SELECT mdate,DATE_FORMAT(mdate,'%Y%m')  FROM usertbl;
          SELECT mdate, 
          period_diff(DATE_FORMAT(NOW(),'%Y%m') , 
          date_format(mDate,'%Y%m'))
          FROM usertbl;
          ```

## 2. **ALTER** 문

   + 테이블 안의 속성을 변경하고자 할때 사용한다. 

## 3. **제약조건 **

   + 제약조건을 이용해 테이블 삽입시 알맞은 데이터만 사용할 수 있게 한다 

   ```sql
   DROP TABLE IF EXISTS itemtbl;
   
   CREATE TABLE itemtbl(
   	id int PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(20) NOT NULL UNIQUE,
       # 값이 반드시 들어가야 하며, 중복되어서는 안된다. 
       price INT NOT NULL,
       regdate DATE
   );
   ```

## 4. SQL문 사용시 **VARCHAR** 과 **CHAR** 의 차이점

   + 사이즈가 다르다.

     + **한국어**는 한글자에 **3byte**를 사용한다. 

     + ```sql
       # char_length 는 문자의 갯수, length는 byte 수를 리턴 
       SELECT char_length('abc'), length('abc');
       SELECT char_length('가나다'), length('가나다');
       
       ```

     + varchar 가 더 많은 글자를 담을 수 있다. 

   + 불변 , 가면

     + varchar 는 **가변적**이고, char는 내부 크기를 변경 못한다. 
     + VAR는 불변이여서 변경되지 않는 값을 사용할 때 사용한다. 

## 5. 가상 테이블 **WITH** 만들기

   + 가상의 테이블 한개를 만들어 데이터를 정제한다. 

   + ```sql
     WITH temp(addr, max)
     AS
     (SELECT addr, MAX(height) FROM usertbl
     GROUP BY addr)
     SELECT AVG(max) FROM temp;
     
     # 위에것을 서브쿼리를 이용해서 구현 
     SELECT AVG(a.hmax) FROM(
     SELECT addr, MAX(height) AS hmax FROM usertbl
     GROUP BY addr) a
     
     ```
