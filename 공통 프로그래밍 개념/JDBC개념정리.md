# JDBC (Java Database Connectivity)

---

>JDBC 개념과 사용법에 대해 학습한다. 
>
>[참고 사이트 1 ](https://velog.io/@jungnoeun/JDBC%EB%9E%80)
>
>[참고 사이트 2](https://dyjung.tistory.com/50)
>
>[참고 사이트 3](https://shs2810.tistory.com/18)

## JDBC

### 정의

- 자바 프로그램이 데이터베이스와 연결되어 데이터를 주고 받을 수 있게 해주는 프로그래밍 인터페이스이다. 
- 따라서 자바에서 DB 프로그래밍을 하기위한  API이다. 

### 역할

- 응용프로그램과 DBMS간의 통신을 중간에서 번역해주는 역할을 한다. 

### 사용방법

#### 1. JDBC 드라이버 로드 

- DBMS와 통신을 담당하는 자바 클래스 
- DB와 연결을 위한 드라이버 로딩 

#### 2. DB Connection

- DB와 연결을 위해 DB의 URL과 계정정보가 필요

  - URL : DBMS와의 연결을 위한 식별 값 -> DB 주소 

- 연결 메소드로는  DriverManager.getConnection(url, id, pwd)  이 있다. 

```java
public static Connection getConnection() throws Exception{
    //오라클 사용시 정의되는 URL 형식
    String url = "jdbc:oracle:thin:@117.16.46.111:1521:xe"; 
    String user = "smu"; //DB의 user
    String password = "smu";//DB의 password
    Connection conn = null;
    //오라클 벤더가 제공하고 있는 클래스 이름
    Class.forName("oracle.jdbc.driver.OracleDriver"); 
    conn = DriverManager.getConnection(url, user, password);
    return conn;
}
```

#### 3. DB에 데이터를 읽거나 쓰기 ( SQL 문 )

```java
//Statement 생성
Statement stmt = con.createStatement();
//질의 수행
ResultSet rs = stmt.executeQuery("select no from user");
//any SQL
stmt.execute("query");
//Select
stmt.executeQuery("query");
//Insert,Update,Delete
stmt.executeUpdate("query");
```

- Statement 객체에 쿼리문을 작성하고 실행해달라는 메서드(execute)를 사용한다. 해당 메서드는 실행할 쿼리가 Select냐 Insert냐 Update냐 Delete냐 에 따라서 메서드가 달라진다.

- statement객체를 이용해서 ResultSet을 얻어낼 수 있다.

- resultSet 으로 결과받기 

  ```java
  ResultSet rs = stmt.executeQuery("select no from user");
  while(rs.next()) //레코드상에서 다음것이 있는 동안에 
    System.out.println(rs.getInt("no")); 
    //가리키고 있는 거에서 칼럼명이 no인애 값을 하나 꺼내와라
  ```

#### 4. DB 연결 종료

```java
rs.close();
stmt.close();
con.close();
```
