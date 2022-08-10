# Day87

---

# Final Project

>admin page 의 center 꾸미기
>
>쿠키와 Ajax 이용 해당 사이트 방문자 수 채크 

## 사이트 방문자 수 체크 

#### mysql 이벤트 설정

- [참고사이트1](https://jungeunpyun.tistory.com/64)
- [참고사이트2](https://linuxism.ustd.ip.or.kr/854)

1. 이벤트 스케쥴러를 사용할 수 있도록 바꿔야 한다. 

   ```sql
   -- 이벤트 스케쥴러 사용 유무 확인 
   SHOW VARIABLES LIKE 'event%';
   
   -- value 가 off라면 on으로 변경해야 사용할수 있다. 
   SET GLOBAL event_scheduler = ON;
   ```

2. 이벤트 목록 확인 

   ```sql
   SELECT * FROM information_schema.events;
   ```

3. 이벤트 추가 

   ```sql
   -- event 추가 
   CREATE EVENT ip_reset
   ON SCHEDULE 
   EVERY 1 MINUTE -- 1분마다 이벤트 실행 
   STARTS CURRENT_TIMESTAMP -- 현시간부로 이벤트를 실행한다. 
   COMMENT 'visit테이블 정보 삭제'
   DO
   TRUNCATE TABLE visit;
   
   -- starts 로 언제부터 해당 이벤트를 실행할지 결정
   -- 매일 정시 시작 하도록 함 
   STARTS '2022-08-09 00:00:00'
   -- ON SCHEDULE EVERY 자주쓰이는 종류
   1 MINUTE, 1 SECOND
   1 WEEK ,1 DAY
   ```

   - CREAT EVENT 이벤트명
   - ON SCHEDULE EVERY 반복주기 : MONTH, WEEK, DAY, HOUR 등 사용 가능
   - STARTS '시간' : 해당 시간부터 시작, 시작시간 설정하지 않는 경우 이벤트 생성 후 다음 주기부터 실행
   - COMMENT '주석'
   - DO 동작 : 반복적으로 진행할 동작 입력

4. 이벤트 삭제 

   ```sql
   DROP event ip_reset;
   ```

#### 발생오류

1. 테이블 내용을 다 삭제했을 경우 SUM() 함수를 적용못시킴 

   - TRUNCATE : 테이블을 드롭한다음 다시 Create 한다. 따라서 AutoIncreaments 도 1부터 다시 시작한다. 

   - 해결방법 

     - visitList 로 모든 visit 테이블의 값을 받아온 후 "isEmpty()" 로 비어있는지 확인한다. 

     - 비어있으면 새로운 필드를 하나 넣어준다. 

     - List의 null 처리는 isEmpty() 함수를 사용해야 한다. 

       ```java
       visitList = vbiz.get();
       System.out.println("visitList : " + visitList );
       if(visitList.isEmpty()) {// 아무 필드도 없을 경우 
           vbiz.register(new VisitVO("Unknown", 0));// 필드 하나 추가 
       
       }
       ```

       

