SELECT m.id, m.gid, m.title, m.director, m.mainactor, m.releasedate, m.posterimg1, m.posterimg2, m.country, m.runningtime, m.text,
g.name as gname, g.tid as hgid,
ROUND(AVG(r.star),1) as star 
FROM movie m
INNER JOIN genre g ON m.gid=g.id
INNER JOIN reviews r ON r.mid=m.id
WHERE g.name LIKE '%판타지%'
GROUP BY id;
-- WHERE g.name LIKE CONCAT('%',로맨스,'%') -> 공포 , 로맨스 , 판타지 3중 하나 

SELECT m.id, m.gid, m.title, m.director, m.mainactor, m.releasedate, m.posterimg1, m.posterimg2, m.country, m.runningtime, m.text,
g.name as gname, g.tid as hgid,
ROUND(AVG(r.star),1) as star 
FROM movie m
INNER JOIN genre g ON m.gid=g.id
INNER JOIN reviews r ON r.mid=m.id
WHERE m.director LIKE '%victor fleming%'
GROUP BY id;

-- <select id="selectall" resultType="movieVO">
SELECT m.id as id,m.title, g.name as gname, m.director as director, m.mainactor as mainactor,
m.releasedate as releasedate, m.posterimg1 as posterimg1,m.posterimg2 as posterimg1, m.country as country,
m.runningtime as runningtime, m.text as text, ROUND(AVG(star),1) as star FROM movie m
INNER JOIN genre g ON g.id = m.gid
INNER JOIN reviews r ON r.mid=m.id
GROUP BY id;
-- </select>

-- 평점순 정렬 
SELECT m.id, m.gid, m.title, m.director, m.mainactor, m.releasedate, m.posterimg1, m.posterimg2, m.country, m.runningtime, m.text,
g.name as gname, g.tid as hgid,
ROUND(AVG(r.star),1) as star 
FROM movie m
INNER JOIN genre g ON m.gid=g.id
INNER JOIN reviews r ON r.mid=m.id
GROUP BY m.id
ORDER BY star DESC
LIMIT 0, 6;

-- 리뷰 갯수 
SELECT * FROM reviews;

SELECT r.mid, COUNT(mid) FROM reviews r
INNER JOIN movie m ON r.mid=m.id
GROUP BY mid;

SELECT m.id, m.gid, m.title, m.director, m.mainactor, m.releasedate, m.posterimg1, m.posterimg2, m.country, m.runningtime, m.text,
g.name as gname, g.tid as hgid,
ROUND(AVG(r.star),1) as star ,COUNT(mid) as rcnt
FROM movie m
INNER JOIN genre g ON m.gid=g.id
INNER JOIN reviews r ON r.mid=m.id
GROUP BY m.id
ORDER BY star DESC
LIMIT 0, 6;




-- 오늘 상영하는 영화 
SELECT * FROM schedules
WHERE sdate = DATE_FORMAT(sysdate(), '%Y-%m-%d');
-- INSERT INTO schedules VALUES (null,1,1002,sysdate());
-- UPDATE schedules SET sdate=sysdate() WHERE id=2010;

SELECT m.id, m.gid, m.title, m.director, m.mainactor, m.releasedate, m.posterimg1, m.posterimg2, m.country, m.runningtime, m.text,
g.name as gname, g.tid as hgid,
ROUND(AVG(r.star),1) as star,
s.id as sid, s.sdate as sdate
FROM movie m
INNER JOIN schedules s ON s.mid=m.id
INNER JOIN genre g ON m.gid=g.id
INNER JOIN reviews r ON r.mid=m.id
WHERE s.sdate = DATE_FORMAT(sysdate(), '%Y-%m-%d')
GROUP BY m.id;


-- pageing 처리 방법
select * from 
        (select @rownum := @rownum + 1 as rn, id, title 
        from movie, (select @rownum := 0)  as rowcolumn order by id desc) as rownum_table  
where rn > 0 and rn <=10;
-- where rn between 10 and 20;

select * FROM(
	SELECT title, id FROM movie order by id asc
) m
LIMIT 0, 3;

-- review rank 페이지 
SELECT m.id, m.gid, m.title, m.director, m.mainactor, m.releasedate,
m.posterimg1, m.posterimg2, m.country, m.runningtime, m.text,
g.name as gname, g.tid as hgid,
ROUND(AVG(r.star),1) as star ,COUNT(mid) as rcnt
FROM movie m
INNER JOIN genre g ON m.gid=g.id
INNER JOIN reviews r ON r.mid=m.id
GROUP BY id
ORDER BY star DESC
LIMIT 3,3;
-- 시작위치 , 반환갯수 

-- 갯수 구하기 
SELECT COUNT(*) FROM (
SELECT m.id, ROUND(AVG(r.star),1) as star 
FROM movie m
INNER JOIN reviews r ON r.mid=m.id
GROUP BY id) c;


SELECT m.id, m.gid, m.title, m.director, m.mainactor, m.releasedate,
m.posterimg1, m.posterimg2, m.country, m.runningtime, m.text,
ROUND(AVG(r.star),1) as star ,COUNT(mid) as rcnt, COUNT(m.id) as cnt
FROM movie m
INNER JOIN reviews r ON r.mid=m.id
GROUP BY id;


-- review rank
SELECT m.id, m.gid, m.title, m.director, m.mainactor, m.releasedate,
m.posterimg1, m.posterimg2, m.country, m.runningtime, m.text,
g.name as gname, g.tid as hgid,
ROUND(AVG(r.star),1) as star ,COUNT(mid) as rcnt, rn
FROM(select @rownum := @rownum + 1 as rn, id, gid, title, director, mainactor, releasedate, posterimg1,posterimg2,country,runningtime,text
        from movie, (select @rownum := 0)  as rowcolumn order by rn desc) m
INNER JOIN genre g ON m.gid=g.id
INNER JOIN reviews r ON r.mid=m.id
GROUP BY id
ORDER BY star DESC;
        

-- 영수증 적립금 추가 
INSERT INTO pointlist VALUES (null, 'awy', -500, sysdate(), '쿠폰5');
        
        
        
        
-- 쿠폰 할인가격 순 정렬
SELECT * FROM coupon ORDER BY sale;        
        
        
-- 유저의 쿠폰 유무 확인 
SELECT * FROM mycoupon where uid='kms' and cid='8004';

-- 쿠폰 이벤트 날짜 확인 
SELECT * FROM coupon 
WHERE date_format(sysdate(),'%Y%m%d') >= sdate
and date_format(sysdate(),'%Y%m%d') <= edate
ORDER BY sale;

-- 쿠폰 사용여부 + 사용기간 
SELECT mc.id, mc.uid, mc.cid, mc.used, mc.udate, mc.rdate,
c.name as cname, c.sdate, c.edate, c.sale, c.text
FROM mycoupon as mc
INNER JOIN coupon as c ON mc.cid=c.id
WHERE date_format(sysdate(),'%Y%m%d') >= sdate
and date_format(sysdate(),'%Y%m%d') <= edate
and uid='awy' and used=1
ORDER BY c.sale DESC;


-- 영수증 바코드 번호 저장 
SELECT * FROM receipt;
INSERT INTO receipt VALUES ('580807201121',sysdate(),'영수증 테스트 ' );
DELETE FROM receipt WHERE id='580807201122';




-- 방문자수 체크 
DROP TABLE IF EXISTS visit;       
CREATE TABLE visit(
   id VARCHAR(100),
   count INT
);

INSERT INTO visit (id, count) VALUES('Unknown',0);
INSERT INTO visit (id, count) VALUES('awy',2);

DELETE FROM visit WHERE id='awy';
UPDATE visit SET count=count+1 WHERE id='Unknown';

SELECT * FROM visit;
SELECT sum(count) FROM visit;


-- mysql event schedular 사용 가능한지 확인 
show variables LIKE 'event%';
-- value 가 off라면 on으로 변경해야 사용할수 있다. 
SET GLOBAL event_scheduler = ON;
-- 등록된 event 목록
SELECT * FROM information_schema.events;
-- event 추가 
CREATE EVENT today_visit_reset
ON SCHEDULE EVERY 1 DAY
COMMENT 'visit테이블 정보 삭제'
DO
TRUNCATE TABLE visit;
-- 이벤트 삭제 
-- DROP event today_visit_reset;



SELECT * FROM theater;
SELECT * FROM schedules;
SELECT * FROM detail_schedules;
SELECT * FROM reviews;
SELECT * FROM movie;
SELECT * FROM genre;
SELECT * FROM cust;
SELECT * FROM coupon;
SELECT * FROM mycoupon;
SELECT * FROM pointlist;
SELECT * FROM visit;