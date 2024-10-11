# SQL 테이블의 Key 관련 개념정리

---

>

## DB의 Key 종류

1. **Primary Key ( 기본키 )**
   1. 테이블에서 각 행을 **유일하게 식별**할 수 있도록 보장하는 키
2. **Foreign Key ( 외례키 )**
   1. 테이블 간의 **관계**를 정의할때 사용한다. 
   2. 참조된 테이블의 키와 동일한 데이터만 허용
3. **Unique Key ( 고유키 )**
   1. 테이블의 필드 값이 **고유함을 보장**한다.
   2. NULL 값을 가질 수 있으며, 중복되지 않도록 한다.
4. **Composite Key (복합키)**
   1. 여러 개의 컬럼을 조합하여 하나의 **고유 식별자**로 사용하는 키
   2. 데이터의 고유성을 보장하지 않으며, 중복된 값이 허용
5. **Candidate Key (후보키)**
   1. Primary Key로 사용할 수 있는 **잠재적인 키**
   2. 테이블에서 유일한 값을 가지며, 중복되지 않는다.
   3. 테이블에 여러 개의 후보 키가 있을 수 있으며, 그 중 하나를 Primary Key로 선택할 수 있다.
6. **Super Key (슈퍼키)**
   1. 테이블 내에서 한 행을 **유일하게 식별**할 수 있는 모든 컬럼의 집합
   2. Primary Key는 슈퍼 키의 부분 집합이다.
7. **Alternate Key (대체키)**
   1. Primary Key로 선택되지 않은 **후보 키**
   2. 후보 키 중 Primary Key가 아닌 키를 Alternate Key라고 부른다
8. **Index Key (인덱스 키)**
   1. 테이블의 특정 컬럼을 기준으로 **검색 속도를 향상**시키기 위한 키
   2. 중복 허용

### Key  정리

1. **Primary Key**: 유일하게 식별되며 NULL 값을 가질 수 없음
2. **Foreign Key**: 참조 무결성을 유지하는데 사용
3. **Unique Key**: 고유성 보장, NULL 허용 가능
4. **Composite Key**: 여러 필드로 조합된 유일 키
5. **Candidate Key**: Primary Key로 사용할 수 있는 후보 키
6. **Super Key**: 테이블 내에서 고유성을 보장하는 모든 컬럼의 조합
7. **Alternate Key**: Primary Key로 선택되지 않은 후보 키
8. **Index Key**: 검색 성능을 향상시키기 위한 키

## Primary Key 삭제

1. 만약 만들어져있는 테이블의 `Primary Key`를 삭제하고 싶다면 새로 만들지 않고 `Primary Key` 만 삭제 가능하다. 

2. 단, Primary Key가 Foreign Key로 다른 테이블과 연결되어 있다면, Primary Key를 삭제하기 전에 Foreign Key 제약을 먼저 제거해야 한다.

3. 여러 열로 이루어진 복합 Primary Key도 동일한 방식으로 삭제할 수 있다.

   1. 복합 키 역시 `DROP PRIMARY KEY`로 한 번에 삭제 가능 

   ```mysql
   ALTER TABLE table_name DROP PRIMARY KEY;
   ```

   