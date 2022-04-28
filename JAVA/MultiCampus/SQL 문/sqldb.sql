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


