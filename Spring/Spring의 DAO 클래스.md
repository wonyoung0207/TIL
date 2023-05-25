## Spring의 DAO 클래스 

### DAO란? 

- DAO(Data Access Object) 클래스는 **데이터베이스와의 상호작용을 담당하는 클래스**이다. 
  - Spring에서는 iBATIS(MyBatis)의 **`SqlMapClientTemplate`**을 사용하여 **SQL 맵퍼를 실행**하고 **결과를 반환하는 방식**으로 DAO 클래스를 작성할 수 있다. 
- DAO 클래스는 Spring의 **`@Repository`** 어노테이션으로 표시되어야 한다. 
  - **`@Repository`**어노테이션은 해당 클래스를 **데이터 액세스 객체로 식별**하고, **Spring이 자동으로 빈**으로 등록하여 DI(Dependency Injection)를 지원한다.
- **`SqlMapClientTemplate`**은 **iBATIS(MyBatis)와 Spring을 통합하기 위한 클래스**로, SQL 맵퍼를 실행하기 위한 다양한 메서드를 제공한다. 
  - SqlMapClientTemplate` 객체는 Spring의 DI를 통해 DAO 클래스에 주입되어야 한다. 
- DAO 클래스에서 `SqlMapClientTemplate`을 사용하여 SQL 맵퍼를 실행할 수 있다. 
  - 예를들어, getSqlMapClientTemplate().queryForList("ids_equipment.selectEquipList", paramMap)`와 같은 메서드를 사용하여 SQL 쿼리를 실행하고 결과를 반환할 수 있다.

### 정리 

- DAO 클래스는 Ibatis를 통해서 DB와 상호작용하는 클래스이다. 
  - Ibatis에는 sqlMapClientTemplate 라는 객체가 있어, 이 객체를 통해 사용자가 xml파일로 구현해놓은 네임스페이스(sqlMap 태그) 에 자동 Mapping된다. 
  - sqlMap 태그 안에는 DAO의 메소드 (selectEquipList) 이름과 메핑되는 id를 가진 태그가 있어 자동 Mapping된다. 

