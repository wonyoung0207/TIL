# SQL 사용자 권한 부여 및 회수 

---

>[참고 사이트1](https://m.blog.naver.com/wlstncjs1234/221762257613)
>
>[참고 사이트2](https://nowes00.tistory.com/8)

## Grant

### 정의

1. 사용자(User)에게 접속권한, 오브젝트 생성권한, DBA 권한 등을 부여

### 형식

1. grant <권한 리스트> on <객체명> to <사용자 리스트>
   1. 권한 리스트 : select, create 등의 명령어에 관한 리스트 
   2. 객체명 : 테이블 명으로, 어떤곳에 권한을 줄것인지 결정
   3. 사용자 리스트 : 권한을 줄 사용자의 이름 및 Role 을 사용할 수 있다. 

### 예시

```sql
-- kim에게 student 테이블에 대한 select와 delete 권한을 동시에 부여
grant select, delete on student to kim

-- student에 대해 모든 사용자들에게 select 권한을 부여
grant select on student to public

-- student 테이블에 대한 모든 권한을 lee에게 부여
grant all privileges on student to lee
```



---

## Revoke

### 정의

1.  사용자(User)에게 부여한 권한을 다시 회수

### 형식

1. revoke <권한리스트> on <객체명> from <사용자 리스트>

### 예시

```sql
-- kim에게 부여되었던 student 테이블에 대한 select 권한을 회수
revoke select on student from kim
```



---

## Role ( 역할 )

### 정의

1. 권한들의 집합(역할)으로, 집합을 만들어 해당 집합에 권한을 부여한다. 
2. 권한을 부여받은 집합(Role)을 사용자에게 설정한다. 
3. Role을 얻은 사용자는 Role 에 설정된 권한의 범위만큼 사용할 수 있게 된다. 
4. 따라서 데이터베이스 관리자만이 생성 가능

### 형식

1. create role <롤이름>

### 순서 

1. ROLE 생성 (CREATE ROLE)
2. 사용자에 ROLE 부여 (GRANT)
3. ROLE에 권한 할당 (GRANT)

- 2번과 3번의 순서는 바뀌어도 된다. 
  - Role에 권한을 먼저 주느냐, 사용자에게 Role을 먼저 주느냐의 차이일 뿐이다. 

### 예시

```sql
-- 1. 집합 생성 
create role employee
create role manager

-- 2. 집합에 사용자 배정 (lee, kim 은 employeee 집합에 속하게 된다. )
grant employee to lee, kim
grant manager to chang, choi

-- 3. 집합에 권한부여 
grant select on student to employee
grant select, insert on student to manager

-- 집합에 권한 회수 
revoke insert on student from manager

-- 집합에서 사용자 배제 
revoke manager from choi

-- 집합 삭제 
drop role manager
```

