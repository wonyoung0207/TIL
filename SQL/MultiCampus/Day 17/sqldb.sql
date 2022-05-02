use sqldb;

SELECT * FROM usertbl
WHERE addr = '서울'
AND birthYear >= 1940
AND mobile1 IS NULL;#IS NOT NULL : 널이 아닌 자료만 가져옴 


SELECT * FROM usertbl
WHERE height > 170 OR birthYear < 1970;

SELECT * FROM usertbl
WHERE height = 180;


SELECT * FROM usertbl
WHERE height BETWEEN 180 AND 183;

SELECT * FROM usertbl
WHERE height IN (182.170,172);

SELECT * FROM usertbl
WHERE mDate = '2010=10=10';

SELECT * FROM usertbl
WHERE date_format(mDate,'%Y') < '2010';
#날짜 타입같은 경우 년월일 이 완벽하게 있으면 비교가 가능하지만, 따로 있으면 연산하지 못한다. 
# 따라서 date_format() 함수를 이용해 비교할 수 있다. 

SELECT * FROM usertbl
WHERE date_format(mDate,'%Y') > '2005' AND date_format(mdate,'%Y') < '2008';
SELECT * FROM usertbl
WHERE date_format(mDate,'%Y') BETWEEN '2005' AND '2008';

SELECT * FROM usertbl
WHERE date_format(mDate,'%Y') = '2007' or date_format(mdate,'%Y') = '2009';
SELECT * FROM usertbl
WHERE date_format(mDate,'%Y') IN ('2007','2009');
SELECT * FROM usertbl
WHERE date_format(mDate,'%m') IN ('04','07');


SELECT * FROM usertbl
WHERE name LIKE '%김%';
# 이름이 김으로 시작하는 사람 출력

# 김으로 시작하는 외자 
SELECT addr FROM usertbl
WHERE name = '윤종신';

#서브쿼리 이용
# 윤종신 회원이 사는 동네의 회원 정보를 출력 
SELECT * FROM usertbl
WHERE addr = (SELECT addr FROM usertbl
WHERE name = '윤종신');

SELECT * FROM usertbl
WHERE height > (SELECT height FROM usertbl WHERE name = '윤종신');

# 경남 지역의 회원 키와 동일한 회원들을 조회
SELECT * FROM usertbl
WHERE height IN (SELECT height FROM usertbl
WHERE addr='경남');

SELECT * FROM usertbl;

SELECT * FROM usertbl ORDER BY height;# ASC와 같음 
SELECT * FROM usertbl ORDER BY height DESC;# ASC와 같음 

SELECT * FROM usertbl ORDER BY height DESC,name DESC;# ASC와 같음 

SELECT DISTINCT addr FROM usertbl;#중복을 제거 

SELECT * from usertbl
ORDER BY height
LIMIT 0, 5;# 위에서부터 0부터 5개만 출력

SELECT * from usertbl
ORDER BY height
LIMIT 2,5;# 2에서부터 5개 출력


SELECT * FROM buytbl;

SELECT userID, SUM(price) FROM buytbl
GROUP BY userID;

# group by에서 조건을 사용하려면 where 이 아닌 having을 사용한다. 
SELECT userID, ROUND(AVG(price),1) AS pavg FROM buytbl
GROUP BY userID
HAVING ROUND(AVG(price),1) > 100;

SELECT userID, ROUND(AVG(price),1) AS pavg FROM buytbl
GROUP BY userID
HAVING pavg > 100
ORDER BY pavg DESC;


SELECT userID, ROUND(AVG(price*amount),1) AS pavg FROM buytbl
GROUP BY userID
HAVING pavg > 100
ORDER BY pavg DESC;

#사이트에서 구매한 고객의 구매건수 -> userID가 중복되어있어 Distinct 해야함
SELECT count(DISTINCT(userID)) FROM buytbl;

# groupName 별 구매 고객의 수 를 구하시오
SELECT groupName, COUNT(DISTINCT(userID)) FROM buytbl
GROUP BY groupName;

# usertbl 회원 들의 평균 키보다 큰 회원을 조회하시오.
SELECT AVG(height) FROM usertbl;#평균 키
SELECT * from usertbl;

SELECT * FROM usertbl
WHERE height > (SELECT AVG(height) FROM usertbl);

# 회원 중 폰을 가지고 있는 회원의 수
SELECT COUNT(mobile1) FROM usertbl;

SELECT userID, groupName , SUM(price*amount) AS usum FROM buytbl
GROUP BY userID, groupName
HAVING userID IN ('BBK','KBS') 
AND
groupName IS NOT NULL
ORDER BY userID;


SELECT num,groupName, SUM(price*amount) AS sum FROM buytbl
GROUP BY num, groupName;



DROP TABLE IF EXISTS carttbl;
CREATE TABLE carttbl(
	id int PRIMARY KEY AUTO_INCREMENT,
    userID char(8) NOT NULL,
    itemID INT NOT NULL,
    qt INT NOT NULL,
    regdate DATE
);
SELECT * FROM carttbl;
INSERT INTO carttbl VALUES(NULL, 'BBK', 1000, 10, SYSDATE());
INSERT INTO carttbl VALUES(NULL, 'KBS', 1000, 10, SYSDATE());
INSERT INTO carttbl VALUES(NULL, 'JYP', 1000, 10, SYSDATE());
DELETE FROM carttbl WHERE id= 1;
DELETE FROM carttbl WHERE userID = 'BBK';
DELETE FROM carttbl WHERE userID = 'JYP';



DROP TABLE IF EXISTS itemtbl;
CREATE TABLE itemtbl(
	id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL UNIQUE,# 값이 반드시 들어가야 하며, 중복되어서는 안된다. 
    price INT NOT NULL,
    regdate DATE
);
ALTER TABLE itemtbl AUTO_INCREMENt = 1000;
INSERT INTO itemtbl VALUES(NULL, 'pants1', 10000, SYSDATE());# NULL부분에 자동으로 숫자가 들어간다. 
INSERT INTO itemtbl VALUES(NULL, 'pants2', 20000, SYSDATE());# NULL부분에 자동으로 숫자가 들어간다. 
INSERT INTO itemtbl VALUES(NULL, 'pants3', 30000, SYSDATE());# NULL부분에 자동으로 숫자가 들어간다. 
INSERT INTO itemtbl VALUES(NULL, 'pants4', 40000, SYSDATE());# NULL부분에 자동으로 숫자가 들어간다. 
INSERT INTO itemtbl VALUES(NULL, 'pants5', 50000, SYSDATE());# NULL부분에 자동으로 숫자가 들어간다.
INSERT INTO itemtbl VALUES(NULL, 'pants1', NULL, SYSDATE());# NULL부분에 자동으로 숫자가 들어간다.  
SELECT * FROM itemtbl;

# 가상의 테이블 temp를 userID, total 로 만들겠다. 
WITH temp(userID, total)
AS
(SELECT userID, SUM(price * amount) FROM buytbl
GROUP BY userID)
SELECT * FROM temp;

SELECT * from usertbl;
SELECT * from buytbl;


# 각 지역별 가장 키가 큰 키들의 평균을 구하시오
WITH temp(addr, max)
AS
(SELECT addr, MAX(height) FROM usertbl
GROUP BY addr)
SELECT AVG(max) FROM temp;

SELECT AVG(a.hmax) FROM(
SELECT addr, MAX(height) AS hmax FROM usertbl
GROUP BY addr) ;

SELECT CONCAT(prodName, groupName ) from buytbl;
# 두개의 스트링을 더해서 가져온다

SELECT userID, price*amount AS tt, IF(price*amount > 500, 'Hight', 'low') AS level FROM buytbl;

SELECT * FROM buytbl;

# null이 아니면 수식1, 널이면 수식2
SELECT prodName, IFNULL(groupName,'nullvalue') FROM buytbl;

#IFNULL 수식을 이용하면 count 함수 사용시 갯수를 셀 수 있다. 
SELECT COUNT(IFNULL(groupName,'nullvalue')) FROM buytbl;

SELECT userID, amount, 
CASE
	WHEN amount >= 1 AND amount < 2 THEN 'C'
	WHEN amount >= 2 AND amount < 4 THEN 'B'
    WHEN amount >= 4 AND amount < 6 THEN 'A'
    ELSE 'NONE'
END AS level
FROM buytbl;


SELECT char_length('abc'), length('abc');
SELECT char_length('가나다'), length('가나다');

SELECT FORMAT(123456.12345,4);

SELECT * FROM usertbl;

# 오늘 날짜를 기준으로 더하기 , 뺴기 해서 알려준다. 
SELECT mDate,adddate(mdate, interval 30 DAY),
	subdate(mDate, INTERVAL 30 DAY)
FROM usertbl;

SELECT CURDATE();# 날짜만
SELECT CURTIME(); #시간만
SELECT NOW(); #날짜 시간 둘다
SELECT SYSDATE();#날짜 시간 둘다
SELECT YEAR(sysdate());

# 오늘 날짜를 기준으로 몇일이 지났는지 날짜1- 날짜2 의 일수를 계산 
SELECT mdate, DATEDIFF(NOW(), mDate) FROM usertbl;

# 오늘 날짜를 기준으로 몇일이 지났는지 날짜1- 날짜2 의 개월 수를 구한다. 
# 이 함수를 사용하려면 형태가 YYYYmm 이여야 한다. 
SELECT mdate,DATE_FORMAT(mdate,'%Y%m')  FROM usertbl;
SELECT mdate, 
period_diff(DATE_FORMAT(NOW(),'%Y%m') , 
date_format(mDate,'%Y%m'))
FROM usertbl;




