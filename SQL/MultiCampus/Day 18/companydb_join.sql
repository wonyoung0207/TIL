 # 사원 정보 출력 사원번호, 사원 이름, 부서명, 직급명 -> inner join 이용  
 SELECT * FROM emp;
 SELECT * FROM dept;
 SELECT * FROM title;
 
 SELECT e.empname, d.deptname, t.titlename FROM emp e
 INNER JOIN dept d ON e.deptno = d.deptno
 INNER JOIN title t ON e.titleno = t.titleno;
 
  SELECT e.empname, d.deptname, AVG(e.salary) FROM emp e
 INNER JOIN dept d ON e.deptno = d.deptno
 INNER JOIN title t ON e.titleno = t.titleno
 GROUP BY d.deptname;

INSERT INTO title VALUES ('50','인턴');
INSERT INTO emp VALUES ('1011',NULL, NULL, '이말자',NULL,3000, SYSDATE());

# outer join -> 한쪽에 있는 것을 꺼낼때 사용한다. 
SELECT * FROM emp e
RIGHT OUTER JOIN title t ON e.titleno = t.titleno;# 오른쪽 title을 기준으로 출력한다. 

SELECT * FROM emp e
LEFT OUTER JOIN title t ON e.titleno = t.titleno;# 왼쪽 emp 기준으로 출력한다. 

# FULL OUTER : 첫번째 결과와 두번째 결과를 합친다. 
SELECT * FROM emp e
LEFT OUTER JOIN title t ON e.titleno = t.titleno
UNION
SELECT * FROM emp e
RIGHT OUTER JOIN title t ON e.titleno = t.titleno;

# 사원 정보를 출력
# 이름, 부서명, 직급명 을 출력한다. 
# 단, 이말자도 출력한다. 
 SELECT e.empname, d.deptname, t.titlename FROM emp e
 LEFT OUTER JOIN dept d ON e.deptno = d.deptno
 LEFT OUTER JOIN title t ON e.titleno = t.titleno;
 
 
 #cross JOIN
SELECT * FROM emp e
CROSS JOIN title t;# 왼쪽 emp 기준으로 출력한다. 
 
 
 # self JOIN
 SELECT * FROM emp;
# 사원 이름과 매니저 이름을 출력 단, 모든 직원을 출력
SELECT e1.empname, e2.empname FROM emp e1
LEFT OUTER JOIN emp e2 ON e1.manager = e2.empno;

# 직원 정보를 출력 한다. 
# 이름, 부서명, 직급명, 매니저명
# 단, 모든 직원 정보를 출력
  SELECT e.empname, d.deptname, t.titlename , e2.empname
  FROM emp e
 LEFT OUTER JOIN dept d ON e.deptno = d.deptno
 LEFT OUTER JOIN title t ON e.titleno = t.titleno
 LEFT OUTER JOIN emp e2 ON e.manager = e2.empno;
 



 #1. 직원정보를 출력 한다. 직원의 연봉 정보와 연봉의 세금 정보를 같이 출력 한다.세금은 10%
SELECT empname,salary, (salary*0.1) AS sfee FROM emp;

# 2. 직원정보 중 2001 이전에 입사 하였고 월급이 4000만원 미만인 직원을 조회
SELECT *  FROM emp 
WHERE DATE_FORMAT(hdate, '%Y') < 2001
AND 
salary < 4000;


# 3. manager가 있는 직원 중 이름에 '자' 가 들어가 있는 직원정보 조회
SELECT * FROM emp
WHERE empname LIKE '%자%'
AND
manager IS NOT NULL;

# 4. 월급이 2000미만은 '하' 4000미만은 '중' 4000이상은 '고' 를 출력

SELECT empname , salary,
CASE
	WHEN salary >= 4000 THEN '고'
    WHEN salary >= 2000 THEN '중'
    ELSE '하'
END AS Slevel
FROM emp;


 SELECT * FROM emp;
 SELECT * FROM dept;
 SELECT * FROM title;
 
# 5. 부서 별 월급의 평균을 구하시오 단, 평균이 3000 이상인 부서만 출력
SELECT deptno, AVG(salary) AS savg FROM emp
GROUP BY deptno
HAVING savg >= 3000;


#6. 부서 별 대리와 사원 연봉의 평균을 구하시오 단, 평균이 2500 이상인 부서만 출력
SELECT deptname, AVG(salary) FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
INNER JOIN title t ON e.titleno = t.titleno
WHERE t.titlename IN ('대리', '사원')
GROUP BY deptname
HAVING AVG(salary) >= 2500;

#7. 2000년 부터 2002년에 입사는 직원들의 월급의 평균을 구하시오
SELECT ROUND(AVG(salary/12),1) AS savg FROM emp
WHERE date_format(hdate, '%Y') between 2000 AND 2002;


# 8. 부서 별 월급의 합의 ranking을 1위부터 조회 하시오
SELECT deptname, ROUND(SUM(salary/12),1) AS ssum FROM 
(SELECT d.deptname, e.empname, e.salary FROM emp e
INNER JOIN dept d ON d.deptno = e.deptno) a
GROUP BY a.deptname
ORDER BY ssum DESC;


# 9. 서울에서 근무하는 직원들을 조회 하시오
SELECT empname, d.deptloc FROM emp e
INNER JOIN dept d ON d.deptno = e.deptno
WHERE d.deptloc = '서울'
;


# 10. 이영자가 속한 부서의 직원들을 조회 하시오
SELECT e.empname, d.deptname FROM emp e
INNER JOIN dept d ON d.deptno = e.deptno
WHERE d.deptno = (
SELECT deptno FROM emp
WHERE empname = '이영자'
);


# 11. 김강국(20)의 타이틀과 같은 직원들을 조회 하시오
SELECT * FROM emp e
INNER JOIN title t ON t.titleno = e.titleno
WHERE t.titleno = (
SELECT titleno FROM emp e
WHERE e.empname = '김강국'
);


# 1. 2000 년 이후 입사 한 사원들의 정보르 출력 (사번, 이름, 타이틀, 부서, 지역)
SELECT empno, empname, t.titlename, d.deptname, d.deptloc FROM emp e
INNER JOIN title t ON t.titleno = e.titleno
INNER JOIN dept d ON d.deptno = e.deptno
WHERE date_format(hdate, '%Y') >= 2000;


# 2. 부서 이름 별 월급의 평균을 구하시오 단, 평균이 3000 이상인 부서만 출력
SELECT deptname ,ROUND(AVG(salary),1) AS savg FROM (
SELECT d.deptname , e.salary FROM emp e
INNER JOIN dept d ON d.deptno = e.deptno
)e
GROUP BY deptname
HAVING savg >= 3000;


# 3. 대구 지역의 직원 들의 평균 연봉을 구하시오
SELECT d.deptloc, ROUND(avg(e.salary),1) AS locAvgSalary FROM emp e
INNER JOIN dept d ON d.deptno = e.deptno
GROUP BY d.deptloc
HAVING d.deptloc = '대구';


# 4. 홍영자 직원와 같은 부서 직원들의  근무 월수를 구하시오 
SELECT empname, PERIOD_DIFF(DATE_FORMAT(NOW(), '%Y%m'), DATE_FORMAT(hdate, '%Y%m')) AS mdiff FROM emp
WHERE deptno = (SELECT deptno FROM emp
WHERE empname = '홍영자');


# 5. 입사 년수가 가장 많은 직원 순으로정렬 하여 순위를 정한다.이름, 부서명, 직위, 년수
SELECT empname, deptname, titlename, ROUND(period_diff(DATE_FORMAT(NOW(),'%Y%m'), DATE_FORMAT(hdate,'%Y%m'))/12,0) AS workYear FROM emp e
INNER JOIN title t ON e.titleno = t.titleno
INNER JOIN dept d ON e.deptno = d.deptno
ORDER BY date_format(hdate, '%Y');
