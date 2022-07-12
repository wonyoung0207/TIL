SELECT m.id, m.gid, m.title, m.director, m.mainactor, m.releasedate, m.posterimg1, m.posterimg2, m.country, m.runningtime, m.text,
g.name as gname, g.tid as hgid,
ROUND(AVG(r.star),1) as star 
FROM movie m
INNER JOIN genre g ON m.gid=g.id
INNER JOIN reviews r ON r.mid=m.id
WHERE g.name LIKE '%판타지%'
GROUP BY id;
-- WHERE g.name LIKE CONCAT('%',로맨스,'%') -> 공포 , 로맨스 , 판타지 3중 하나 



-- <select id="selectall" resultType="movieVO">
SELECT m.id as id,g.name as gname, m.director as director, m.mainactor as mainactor,
m.releasedate as releasedate, m.posterimg1 as posterimg1,m.posterimg2 as posterimg1, m.country as country,
m.runningtime as runningtime, m.text as text, ROUND(AVG(star),1) as star FROM movie m
INNER JOIN genre g ON g.id = m.gid
INNER JOIN reviews r ON r.mid=m.id
GROUP BY id;
-- </select>


SELECT * FROM reviews;
SELECT * FROM movie;
SELECT * FROM genre;