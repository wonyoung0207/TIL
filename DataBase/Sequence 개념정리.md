

# 시퀸스 ( Sequence ) 개념정리

---

>

## 시퀸스 ( Sequence )

### 정의

-  데이터베이스에서 **고유한 순차적인 값을 생성**하기 위해 사용되는 개체
   - **주로 기본 키(primary key) 값을 자동으로 생성**하는데 활용
   - 특히 테이블에 새로운 레코드를 삽입할 때 기본 키 값을 자동으로 생성하는데 유용

### 특징

1. 고유한 값 생성
   - 시퀀스는 각 테이블에서 **고유한 순차적인 값을 생성**하며 중복되지 않는 값이 보장된다. 
2. 자동 증가
   - 시퀀스는 **값을 자동으로 증가**시켜 새로운 레코드를 추가할 때마다 다음 **순차적인 값으로 설정**한다. 
3. 독립적인 개체
   - 시퀀스는 테이블과 독립적으로 관리되며, 여러 테이블에서 동시에 사용할 수 있다.

### 예시

```mysql
CREATE SEQUENCE my_sequence START WITH 1 INCREMENT BY 1;

-- 테이블 생성 ( postgres )
CREATE TABLE my_table (
    id INT DEFAULT NEXTVAL('my_sequence') PRIMARY KEY, -- 이렇게하면 insert시 id값을 넣어주지 않아도 된다. 
    name VARCHAR(50)
);
-- 테이블 생성 ( oracle )
CREATE TABLE my_table (
    id NUMBER DEFAULT my_sequence.NEXTVAL PRIMARY KEY, 
    name VARCHAR2(50)
);

-- 새 레코드 추가
INSERT INTO my_table (name) VALUES ('Alice');
```

-  `my_sequence` : 시퀀스의 이름
-  `START WITH` :  시작 값
-  `INCREMENT BY` :  증가 값(스텝)
-  `IDENTITY` : 자동 증가 값을 생성하기 위해 사용