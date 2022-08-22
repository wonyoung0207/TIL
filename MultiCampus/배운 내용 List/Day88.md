# Day88

---

# Final Project

>INSERT INTO 에 다른 테이블 값 적용시키기

## 사이트 방문자 수 체크 

#### mysql 이벤트 설정

- [참고사이트1](https://jungeunpyun.tistory.com/64)
- [참고사이트2](https://linuxism.ustd.ip.or.kr/854)

#### INSERT INTO 에 다른 테이블 값 적용시키기

- 다른 테이블값을 가져오고 싶을경우 사용 

  ```sql
  INSERT INTO 넣을 테이블이름 (넣을 속성이름)
  SELECT 가져올 속성이름 
  FROM 가져올 테이블이름
  WHERE 조건값; 
  ```

- ```sql
  -- event 2 추가 
  CREATE EVENT today_visit_save
  ON SCHEDULE EVERY 1 DAY
  STARTS '2022-08-09 23:59:00'
  COMMENT 'visit테이블 정보 저장'
  DO
  INSERT INTO visitList(date, count) 
  SELECT sysdate() as date,sum(v.count) as count 
  FROM visit as v;
  -- visit 테이블의 값을 가져와 저장한다. 
  ```

