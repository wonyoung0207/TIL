# Ms SQL 개념정리

---

>

## Ms SQL (Microsoft SQL Server)

### 정의

- Microsoft SQL Server의 줄임말로, 관계형 데이터베이스 관리 시스템(RDBMS)이다. 

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

- MS SQL 이 사용하는 고유 쿼리 언어 

  - 예를 들어, MSSQL은 TOP N 문법을 사용하여 결과 집합의 상위 N 개의 행을 선택한다.
  - 반면 MySQL은 LIMIT 문법을 사용하여 비슷한 작업을 수행한다. 

- 문자열 조합 

  - MySQL

    ```SQL
    SELECT CONCAT('Hello', ' ', 'World');
    ```

  - T-SQL

    ```SQL
    SELECT 'Hello' + ' ' + 'World';
    ```



