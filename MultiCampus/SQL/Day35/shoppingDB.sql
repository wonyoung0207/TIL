-- DDL
DROP DATABASE IF EXISTS shoppingdb;
CREATE DATABASE shoppingdb;
USE shoppingdb;

DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS cust;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS cate;
-- cust table 
CREATE TABLE cust(
	id VARCHAR(10),
    name VARCHAR(20) NOT NULL,
    addr VARCHAR(100) NOT NULL,
    regdate DATE NOT NULL
);

ALTER TABLE cust ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE cust ALTER COLUMN addr SET DEFAULT 'Seoul';
-- cate table 

CREATE TABLE cate(
	id INT,
    name VARCHAR(30) NOT NULL,
    pid INT
);

ALTER TABLE cate ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE cate ADD CONSTRAINT UNIQUE (name);
ALTER TABLE cate ADD CONSTRAINT
FOREIGN KEY (pid) REFERENCES cate (id);
-- ALTER TABLE cate CHANGE COLUMN name name VARCHAR(30) NOT NULL; 

-- product table 

CREATE TABLE product(
	id INT,
    name VARCHAR(20) NOT NULL,
    price INT NOT NULL,
    regdate DATE NOT NULL,
    cid INT 
);
ALTER TABLE product ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE product MODIFY id INT AUTO_INCREMENT;
ALTER TABLE product AUTO_INCREMENT = 1000;
ALTER TABLE product ADD CONSTRAINT CHECK (price > 0);

ALTER TABLE product ADD CONSTRAINT 
FOREIGN KEY (cid) REFERENCES cate (id);


-- cart table

CREATE TABLE cart(
	id INT,
    uid VARCHAR(10),
    pid INT,
    regdate DATE
);
ALTER TABLE cart ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE cart MODIFY id INT AUTO_INCREMENT;
ALTER TABLE cart AUTO_INCREMENT = 1000;

ALTER TABLE cart ADD FOREIGN KEY (uid) 
REFERENCES cust (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE cart ADD FOREIGN KEY (pid) 
REFERENCES product (id);





-- cust data

INSERT INTO cust VALUES ('id01','lee','Busan','2019-03-02');
INSERT INTO cust (id,name,regdate) VALUES ('id02','kim','2020-05-02');

-- cate data
INSERT INTO cate VALUES (10, 'pants', NULL);
INSERT INTO cate VALUES (11, 'short pants', 10);

INSERT INTO cate VALUES (20, 'shirts', NULL);
INSERT INTO cate VALUES (21, 'short shirts', 20);

-- product data
SELECT * FROM product;
INSERT INTO product VALUES 
(NULL, 'levis',10000,curdate(),11);
INSERT INTO product VALUES 
(NULL, 'bang',20000,curdate(),11);
INSERT INTO product VALUES 
(NULL, 'levis',10000,curdate(),21);
INSERT INTO product VALUES 
(NULL, 'bang',20000,curdate(),21);

-- data 
SELECT * FROM product;
SELECT * FROM cust;
SELECT * FROM cate;
SELECT p.id, p.name, p.price, p.regdate, p.cid, c.name AS cate_name
 FROM product p INNER JOIN cate c ON p.cid = c.id;
 
 -- 해당 테이블의 디폴트 정보를 포함 
 desc cust;
 
 -- 설정되어있는 디폴트값으로 설정하고 싶으면 default로 설정한다. 
 INSERT INTO cust VALUES('id68', 'lee',default ,SYSDATE());
 