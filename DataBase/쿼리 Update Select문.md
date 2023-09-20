# 쿼리 Update Select 문

>[참고 사이트1](https://shakddoo.tistory.com/entry/Mysql-Select%EA%B2%B0%EA%B3%BC%EB%A5%BC-Update-%EB%AC%B8%EC%97%90-%EB%B0%98%EC%98%81%ED%95%98%EA%B8%B0)

---

## Update Select문

### 사용 이유

- Select한 값으로 Update를 하기 위해 사용 

### 사용 예시

1. 일반 Update문

   ```sql
   UPDATE 
       [테이블명]
   SET
       [필드명 = 새로운 값]
   WHERE
       [조건문]
   ```

2. Mssql - Update Select문 

   ```sql
   UPDATE 
       [테이블A]
   SET
       [테이블A].필드 = [테이블B].필드
   FROM
       [테이블A],[테이블B]
   WHERE 
       [테이블A].id = [테이블B].id
   ```

3. Mysql - Update Select문 

   ```sql
   UPDATE
       [테이블A],[Select 질의] B
   SET
       [테이블A].필드 = B.필드
   WHERE
   	[테이블A].id = B.id
   ```

4. 오라클 - Update Select문 

   ```sql
   UPDATE
   	[테이블A] A
   SET 
       A.필드 = [SELECT B.필드 FROM 테이블B B WHERE B.id = ###]
   WHERE A.id = ###;
   ```

   



