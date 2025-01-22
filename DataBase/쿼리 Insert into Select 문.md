# insert into select 와 select into 구문

---

>[참고 사이트1](https://makand.tistory.com/entry/SQL-INSERT-INTO-SELECT-%EA%B5%AC%EB%AC%B8)
>
>[참고 사이트2](https://woogie-db.tistory.com/66)

## insert into select

### 정의

- 같은 내용의 테이블 데이터를 복사하는데 유용한 구문이다. 
  - 즉, 다른 테이블의 select 한 내용을 **대상 테이블의 데이터 복사**해 만들 수 있다. 


### 사용법

```sql
-- 형태
INSERT INTO table2
(col1, col2, col3...)
SELECT col1, col2, col3...
FROM table1;

-- 예시 1
INSERT INTO table2
SELECT * FROM table1;

-- 예시 2
INSERT INTO table1 
select * from table2 where col = 'value';

-- 예시 3
insert into 
	tb_ids_camera_info (camera_id, camera_view_id, equip_id , name, "position"   ) 
select 
	equip_id , equip_view_id, equip_id , equip_id , "position" 
from 
	tb_ids_equip e
where e.equip_id like '%CAM%';
```



## select into

### 정의

- 데이터를 포함한 테이블을 복사하는 것으로, 원본 테이블과 동일한 **테이블을 생성**할때 사용한다. 
  - 원본 테이블은 존재하고, **대상 테이블에 대해 새로 생성하고자 하는 경우** 사용
- 데이터를 제외하고 **테이블의 구조만 동일한 테이블을 만들고자 할 경우**에는 WHERE 조건을 추가해서 조회되는 데이터가 없도록 하면 된다. 
  - 즉, 테이블 구조만 다른 테이블로 복제하려면 다음 조건문을 사용한다. -> ( WHERE 1=2;)

### 사용법

```sql
-- 형태 
SELECT 컬럼명 INTO 생성 대상 테이블명
FROM 원본 테이블명
WHERE 조건 

-- 예시 1
SELECT * INTO table2
FROM table1

-- 원본 테이블 A의 특정 컬럼만 대상 테이블 B로 INSERT
SELECT * INTO B
FROM (
	SELECT 컬럼1, 컬럼2, 컬럼3, ...
    FROM A
    ) AS atb
```



## 정리 

- SELECT * INTO 구문과 INSERT INTO SELECT 구문의 차이점
  1. Select * INTO 구문은 동일한 테이블을 사용할 경우 사용한다. 
  2. INSERT INTO 구문은 원본 테이블과 복사 대상 테이블이 모두 존재하는 경우 사용한다. 

