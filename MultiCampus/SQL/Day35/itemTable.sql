SELECT * FROM cust;

SELECT * FROM product;

INSERT INTO product VALUES(NULL, 'pants3', 10000,sysdate(),5.5);

SELECT * FROM item;

DROP TABLE ITEM;

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
INSERT INTO item (name,price,imgname,regdate) VALUES('pants7',10000,'pants7.jpg',CURRENT_DATE()-30);
INSERT INTO item (name,price,imgname,regdate) VALUES('pants8',20000,'pants8.jpg',date_add(current_date(), INTERVAL -2 MONTH));

INSERT INTO item VALUES(NULL,'pants5',50000,'pants5.jpg',CURRENT_DATE());

-- 해당 테이블의 디폴트 값을 출력 
desc cust;
