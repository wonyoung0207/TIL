# Apache Derby 개념정리

---

>

## Apache Derby

### 정의

- **관계형 데이터베이스**의 하나로 Apache에 오픈소스로 공개되면서 Derby라는 이름을 갖게 되었다. 
-  자바로 작성되었기 때문에 **자바 가상 머신(JVM)**이 있는 **어느 곳에서나 실행**될 수 있다
- 오픈소스 DB로, 성능은 다른 오픈소스 DB보다 떨어지지만 안정성이 우수하다. 

### 연결방식

1. 임베디드 데이터베이스(embedded database)
   - 하나의 JVM 내에 어플리케이션과 DB가 함께 존재하도록 구성하는 방식으로 다른 JVM이나 머신에서는 이 DB에 접근이 불가능하다.
2. 클라이언트-서버 연결(client-server connection)
   - 어플리케이션과 DB가 다른 JVM 또는 프로세스에 존재하도록 구성하는 방식으로 각 인스턴스가 하나의 JVM 내에서 동작하고, 일반적인 JDBC와 똑같은 방식으로 접근이 가능하다.

- embedded 방식과 Network Server 방식은 JVM 외부에서의 접근가능여부와 Connection URL에서 IP가 들어가는 것 빼곤 사용방법이 동일하다.

### SQL 문

### OFFSET

- OFFSET 절은 **가져올 행의 시작 위치**를 지정합니다. 이를 사용하여 처음 몇 개의 행을 건너뛰고, 그 이후부터 가져올 수 있습니다.

```sql
SELECT * FROM your_table_name 
OFFSET 5 ROWS;
```

### FETCH

- FETCH 절은 가져올 행의 수**를 지정합니다. 이를 사용하여 결과에서 원하는 개수의 행만 가져올 수 있습니다.

```sql
SELECT * FROM your_table_name 
OFFSET 10 ROWS FETCH NEXT 10 ROWS ONLY;
```

### FETCH NEXT

- FETCH NEXT 구문은 **특정 행 다음에 나오는 N개의 행**을 가져옵니다. 

```sql
SELECT * FROM your_table_name 
OFFSET 10 ROWS FETCH NEXT 10 ROWS ONLY;
```

