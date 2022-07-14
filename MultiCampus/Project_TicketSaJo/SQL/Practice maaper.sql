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
-- INSERT INTO schedules VALUES (null,1,1000,sysdate());

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


SELECT * FROM theater;
SELECT * FROM schedules;
SELECT * FROM detail_schedules;
SELECT * FROM reviews;
SELECT * FROM movie;
SELECT * FROM genre;
SELECT * FROM cust;