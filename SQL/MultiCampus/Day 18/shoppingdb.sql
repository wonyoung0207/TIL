DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS cust;
DROP TABLE IF EXISTS item;

CREATE TABLE cust(
   id CHAR(5),
   pwd VARCHAR(10) NOT NULL,
   name VARCHAR(10) NOT NULL
);
# alter를 이용해 기본키를 추가시켜준다. 
ALTER TABLE cust ADD PRIMARY KEY (id);
INSERT INTO cust VALUES('id01','pwd01','이말숙');
INSERT INTO cust VALUES('id02','pwd02','김말숙');
INSERT INTO cust VALUES('id03','pwd03','정말숙');
# ------------------------------------------

CREATE TABLE item(
   id INT,
   name VARCHAR(20) NOT NULL,
   price INT  NOT NULL,
   imgname VARCHAR(20),
   regdate DATE  NOT NULL
);
ALTER TABLE item ADD PRIMARY KEY (id);
ALTER TABLE item MODIFY id INT AUTO_INCREMENT;
ALTER TABLE item AUTO_INCREMENT = 1000;

INSERT INTO item (name,price,imgname,regdate) VALUES('pants1',10000,'pants1.jpg',CURRENT_DATE());
INSERT INTO item (name,price,imgname,regdate) VALUES('pants2',20000,'pants2.jpg',CURRENT_DATE());
INSERT INTO item (name,price,imgname,regdate) VALUES('pants3',30000,'pants3.jpg',CURRENT_DATE());
INSERT INTO item (name,price,imgname,regdate) VALUES('pants4',40000,'pants4.jpg',CURRENT_DATE());
INSERT INTO item VALUES(NULL,'pants5',50000,'pants5.jpg',CURRENT_DATE());

CREATE TABLE cart(
	id INT,
	custid CHAR(5),
	itemid INT,
	num INT,
	regdate DATE
);

ALTER TABLE cart ADD PRIMARY KEY (id);
ALTER TABLE cart MODIFY id INT AUTO_INCREMENT;
ALTER TABLE cart AUTO_INCREMENT = 100;
ALTER TABLE cart ADD CONSTRAINT FOREIGN KEY (custid) REFERENCES cust(id);
ALTER TABLE cart ADD CONSTRAINT FOREIGN KEY (itemid) REFERENCES item(id);
# 제약조건을 주는데, itemid를 추가하는데 이것을 item테이블의 id로 만든다. 

INSERT INTO cart (custid, itemid,num,regdate) VALUES ('id01',1000,10,CURRENT_DATE());


SELECT * FROM cust;
SELECT * FROM item;
SELECT * FROM cart;

INSERT INTO cart VALUES (NULL,'id02',1001,5,CURRENT_DATE());
INSERT INTO cart VALUES (NULL,'id02',1002,7,CURRENT_DATE());
INSERT INTO cart VALUES (NULL,'id03',1000,3,CURRENT_DATE());


# 세개의 테이블에 있는 정보를 가져옴
SELECT * FROM cart c INNER JOIN cust cu ON c.custid = cu.id;

SELECT c.id, cu.name, i.name, i.price, (c.num * i.price) AS totalPrice FROM cart c 
INNER JOIN cust cu ON c.custid = cu.id
INNER JOIN item i ON c.itemid = i.id
WHERE i.price > 15000;

# 장바구니에 고객별 총 금액의 평균을 구하시오.
SELECT cu.name, AVG(c.num * i.price) AS iavg FROM cart c 
INNER JOIN cust cu ON c.custid = cu.id
INNER JOIN item i ON c.itemid = i.id
GROUP BY cu.name;



SELECT * FROM cart;
SELECT * FROM cust;
SELECT * FROM item;
