## ChatGPT 에 있는 SqlSession 내용 정리 

- `SqlSession.insert()`를 호출할 때 사용되는 SQL문은 MyBatis XML 매퍼 파일에 정의
- 형태
  - sqlSession.select ( mapper 되어있는 namespace 이름 ,  db정보, SQL문 실행 결과 처리하는곳인 handler )
- ResultHandler
  - `ResultHandler` 인터페이스에는 `handleResult`라는 메서드가 정의되어 있습니다. 이 메서드는 `ResultSet` 객체를 매개변수로 받아서 `SELECT` 쿼리의 결과를 처리한다. 
  - `ResultSet` 객체는 SQL 쿼리의 실행 결과를 담고 있는 데이터셋입니다. `handleResult` 메서드를 구현하여 `ResultSet` 객체를 처리하면, `SELECT` 쿼리의 결과를 원하는 대로 가공하거나 처리할 수 있습니다.
  - . 이때 `ResultSet` 객체를 순회하면서, `ResultHandler` 객체의 `handleResult()` 메서드를 호출
    - `ResultSet` 객체의 각 행을 `ResultHandler` 객체로 전달하여 처리하므로, `ResultHandler` 객체는 SQL 쿼리의 실행 결과를 반복적으로 처리