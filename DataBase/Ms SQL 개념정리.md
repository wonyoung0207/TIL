# Ms SQL 개념정리

---

>

## Ms SQL (Microsoft SQL Server)

### 정의

- Microsoft SQL Server의 줄임말로, 관계형 데이터베이스 관리 시스템(**RDBMS**)이다. 

### 특징

- 상용 데이터베이스 관리 시스템으로, 기업용 애플리케이션에서 자주 사용된다. 
- 대용량 데이터 처리에 강점이 있으며, 고급 보안 기능도 제공한다. 
- 윈도우즈 운영체제와의 완벽한 통합을 제공한다. 
- 인덱스를 지원하여 데이터 검색 속도를 높일 수 있다.

### 단점

- 비용이 높은 라이선스 구매가 필요
- 다른 RDBMS에 비해 오픈소스화가 되어 있지 않아 커뮤니티 기반 개발이 어렵다.

### MySQL vs MsSQL

- MSSQL은 비용이 높은 라이선스를 구매해야 하지만, MySQL은 무료로 사용할 수 있다. 
- MySQL은 트랜잭션 처리가 빠르지만, MSSQL은 대규모 데이터베이스 처리에 강점이 있다. 

### T - SQL   (Transact-SQL)

1. **MS SQL 이 사용하는 고유 쿼리 언어** 

   - 예를 들어, MSSQL은 TOP N 문법을 사용하여 결과 집합의 상위 N 개의 행을 선택한다.
   - 반면 MySQL은 LIMIT 문법을 사용하여 비슷한 작업을 수행한다. 

2. **문자열 조합** 

   - MySQL

     ```SQL
     SELECT CONCAT('Hello', ' ', 'World');
     ```

   - T-SQL

     - '+' 를 사용하여 문자열을 조합할 수 있다. 
     
     ```SQL
     SELECT 'Hello' + ' ' + 'World';
     
     <isNotEmpty property="name">
         AND name LIKE '%' + #{name} + '%'
     </isNotEmpty>
     ```
     

3. `<isNotEmpty>` 태그 대신에 `IF` 문을 사용하는 것이 일반적이다. 

   - `<isNotEmpty>` 태그는 iBATIS에서 사용하는 기능 중 하나인 동적 쿼리를 생성하는 기능 중 하나인데, MSSQL에서는 동적 쿼리를 생성할 때 `IF`문을 사용하여 조건절을 처리하는 것이 일반적이다. 

     ```mssql
     <!-- 다른 SQL 문에서 사용되는 동적 태그 --> 
     <isNotEmpty property="search_val" prepend="AND">
         AND EQUIP_TYPE = 1
     </isNotEmpty>
     
     <!-- MSSQL 에서 사용되는 동적 태그 --> 
     <if test="search_val != null and search_val != ''">
         AND EQUIP_TYPE = 1
     </if>
     ```


4. \<isEqual> 태그와 \<iterate> 태그

   - \<isEqual> 테그 대체

     ```xml
     <choose>
       <when test="EQUIP_TYPE == 1">
         AND EQUIP_TYPE = 1
       </when>
       <when test="EQUIP_TYPE == 2">
         AND EQUIP_TYPE = 2
       </when>
       <otherwise>
         AND EQUIP_TYPE = 3
       </otherwise>
     </choose>
     ```

   - \<iterate> 대체 

     - `for xml path`와 `stuff` 함수를 이용하여 리스트를 문자열로 변환한 후 `IN` 연산자를 이용하여 쿼리를 작성할 수 있다.

     ```xml
     <iterate property="list" open="(" close=")" conjunction=",">
       #list[]#
     </iterate>
     
     <!--  -->
     WHERE column_name IN (
       SELECT STUFF(
     (
         SELECT ',' + CAST(list_column AS varchar(MAX))
         FROM list_table
         FOR XML PATH(''), TYPE).value('.', 'nvarchar(MAX)'
     )
     , 1, 1, '')
     )
     ```

     - STUFF() 함수 

       - STUFF() 함수는 문자열을 변경하고 조작하는 데 사용되는 함수이다.
       - 함수는 지정된 시작 위치에서 지정된 문자열 수를 제거한 후, 다른 문자열로 대체한다. 
         - character_expression: 대상 문자열
         - start: 문자열을 대체할 시작 위치
         - length: 대체할 문자열의 길이
         - replaceWith_expression: 대체할 새 문자열

       
       ```xml
       STUFF ( character_expression , start , length , replaceWith_expression )
       ```
       
       ```sql
       SELECT STUFF('ABCD', 2, 1, 'XX') + 'EF'
       
       -- 문자열 'ABCD'에서 2번째 위치부터 1개의 문자를 제거하고 'XX'로 대체한 후 그 문자열의 뒤에 'EF'를 추가합니다.
       -- 결과는 'AXXCD' + 'EF'가 되어 'AXXCDEF' 이다 
       ```

5. LIMIT 없이 TOP 사용

   1. MySQL에서는 LIMIT 없이 TOP을 사용하여 결과 집합의 일부분만 반환

      ```mssql
      -- mysql
      SELECT *
      FROM my_table
      LIMIT 10;
      
      -- mssql
      SELECT TOP 10 *
      FROM my_table;
      ```

6. 문자열 연결

   1. MySQL에서는 CONCAT 함수를 사용하여 문자열을 연결할 수 있지만, MSSQL에서는 '+' 연산자를 사용

      ```mssql
      -- mysql 
      SELECT CONCAT(first_name, ' ', last_name) AS full_name
      FROM my_table;
      
      -- mssql
      SELECT first_name + ' ' + last_name AS full_name
      FROM my_table;
      ```

7. 페이징 처리

   1. MySQL과 같은 다른 데이터베이스에서는 LIMIT와 OFFSET을 사용하여 페이징 처리를 할 수 있지만, MSSQL에서는 OFFSET-FETCH 또는 ROW_NUMBER() OVER() 함수를 사용

      ```sql
      -- mysql 
      SELECT *
      FROM my_table
      LIMIT 10 OFFSET 20;
      
      -- mssql
      SELECT *
      FROM (
          SELECT ROW_NUMBER() OVER (ORDER BY column_name) AS rownum, *
          FROM my_table
      ) AS A
      WHERE A.rownum BETWEEN 21 AND 30;
      
      ```

### 사용 메소드 

1. **날짜** 

   - GETDATE() : 현재 컴퓨터에 설정되어있는 시스템 시간을 불러와주는 함수

2. **Commit** 

   - **모든 작업을 정상적으로 처리하겠다고 확정**하는 명령어이다.

     - 트랜젝션의 처리 과정을 데이터베이스에 반영하기 위해서, **변경된 내용을 모두 영구 저장한다.**
     - COMMIT 수행하면, **하나의 트랜젝션 과정을 종료하게 된다.**
     - TRANSACTION(INSERT, UPDATE, DELETE)작업 내용을 실제 DB에 저장한다.
     - 이전 데이터가 **완전히 UPDATE**된다.
     - 모든 사용자가 변경한 데이터의 결과를 볼 수 있다.

   - 예시 

     - 100개의 데이터가 있었을 때 20개를 삭제하고 30개를 다시 입력한다.
     - 그러면 총 110개의 데이터가 나와야 한다.
     - 하지만, commit을 하지 않을 경우에는 기존의 100개만 데이터가 나온다.

   - **MSSQL**에서는 **autocommit 모드**가 기본적으로 활성화되어있어 **해당 명령어를 사용하지 않아도 된다**. 

     - 만약 autocommit 모드를 비활성화하고 싶다면 다음 명령어를 사용한다. 

       ```sql
       SET autocommit OFF;
       COMMIT; 
       -- 비활성화 하면 commit 명령어 실행해줘야함 
       ```

     - 다시 활성화 하고 싶다면 다음명령어를 사용한다. 

       ```sql
       SET autocommit ON;
       ```

3. **convert()**

   - 데이터 변환시 사용되는 함수이다. 

     - **expression :** 유효한 식
     - **data_type :** 대상 데이터 형식 별칭 데이터 형식은 사용할 수 없다. 
     - **length :** 대상 데이터 형식의 길이를 지정하는 선택적 정수. 
       - 기본값은 30이다.
     - **style :** Convert함수가 식을 변환하는 방법을 지정하는 정수 식
       - style이 Null이면 Null 값이 반환된다.

   - 예시 

     ```mssql
     -- 함수 형태 
     CONVERT(data_type[(length)], expression[style])
     
     -- 예시 
     SELECT CONVERT(NVARCHAR(10),칼럼) AS 칼럼명 FROM MY_TABLE --VARCHAR로 변환
     SELECT CONVERT(VARCHAR(30), FORMAT(GETDATE(), 'yyyyMMddHHmmssfff')); -- Format의 데이터를 varchar형태로 변환 
     ```

4. **format()** 

   - SQL Server 2012 버전부터 FORMAT 함수가 추가되었다. 

   - 날짜 데이터형식을 변환할 때 많이 사용한다. 

     - nvarchar 타입으로 리턴된다. 

   - 예시 

     ```mssql
     SELECT CONVERT(VARCHAR(30), FORMAT(GETDATE(), 'yyyyMMddHHmmssfff'));
     ```

   - **varchar와 nvarchar의 차이**

     - **varchar**는 **바이트 수**, **nvarchar**는 **글자 수**를 기준으로 한다. 
       - **varchar**는 **가변 문자열**이며, **nvarchar**는 **유니코드 지원 가변 문자열**이다. 

     - 비유니코드 지원인 **varchar**는 **영어, 숫자는 1byte, 그 외 한글, 한자 등은 2byte로 저장**한다. 
       - 반면, **nvarchar**는 유니코드 지원이므로 **모든 문자를 일괄적으로 2byte로 저장**한다. 

     - 예시
       - ''**MousaDembele**'라는 문자열은 **varchar**에서는 영문 12자리이기 때문에 **12byte의 공간**이 필요하다. 
       - 반면 **nvarchar**에서는 영문이든 한글이든 관계없이 글자당 2byte를 할당하기 때문에 **24byte의 공간**이 필요하게 된다. 

