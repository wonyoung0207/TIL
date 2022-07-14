# Day65

---

> Final Project 

# Final Project

>개발 환경 셋팅 마무리 
>
>개인 개발 시작 - 메인페이지 오늘 상영영화 ,review 별점 제작 
>

## 날짜 형태 지정 

- sysdate() 에서 년, 월 , 일 의 정보만 꺼내온다. 

  ```sql
  SELECT m.id, m.gid, m.title, m.director, m.mainactor, m.releasedate,
  		m.posterimg1, m.posterimg2, m.country, m.runningtime, m.text,
  		g.name as gname, g.tid as hgid,
  		ROUND(AVG(r.star),1) as star,
  		s.id as sid, s.sdate as sdate
  		FROM movie m
  		INNER JOIN schedules s ON s.mid=m.id
  		INNER JOIN genre g ON m.gid=g.id
  		INNER JOIN reviews r ON r.mid=m.id
  		WHERE s.sdate = DATE_FORMAT(sysdate(), '%Y-%m-%d')
  		GROUP BY m.id
  ```

  

## Mysql only_full_group_by 에러

- mysql은 기본적으로 group 절에 포함되지 않는 Column을 select 할 경우, 컬럼의 어느 	부분에 표시해야 할 지 애매하여 발생하는 에러이다. 
- 해결방법
  1. mysql 설정 상태 확인
     - select @@sql_mode;
  2. mysql 설정이 되있음을 확인 (아래 출력 처럼 only_full_group_by이 나오면 설정이 필요)
     - ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION 
  3. my.cnf 설정파일의 해당 부분을 추가 (linux 기준 /etc/my.cnf)
     - [mysqld]에 아래 설정 추가
     - sql_mode=STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
  4. mysql 재기동
     - service mysqld restart
