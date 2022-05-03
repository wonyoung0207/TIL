DROP DATABASE IF EXISTS shoppingdb;
CREATE SCHEMA shoppingdb;
USE shoppingdb;

# 삭제 순서 중요. cust를 먼저 삭제하면 cart나 product에서 유령데이터가 되기 때문에
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS cust;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS cate;



# cust table
CREATE TABLE cust(
	id VARCHAR(10),
    name VARCHAR(20) NOT NULL,
    addr VARCHAR(100) NOT NULL,
    regdate DATE NOT NULL
);

ALTER TABLE cust ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE cust ALTER COLUMN addr SET DEFAULT 'Seoul'; # 컬럼을 수정하겠다. 

# cate table
CREATE TABLE cate(
	id INT,
    name VARCHAR(30) NOT NULL,
    pid INT
	
);

ALTER TABLE cate ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE cate ADD CONSTRAINT UNIQUE(name);
ALTER TABLE cate ADD CONSTRAINT FOREIGN KEY(pid) REFERENCES cate (id);

# ALTER TABLE cate CHANGE COLUMN name name VARCHAR(30) NOT NULL ; # cate 의 name 필드를 varchar(30)으로 변경한다. 

# product table
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
ALTER TABLE product ADD CONSTRAINT CHECK (price > 0); # 제약조건으로, 마이너스가 들어오지 않게 한다. 
ALTER TABLE product ADD CONSTRAINT FOREIGN KEY(cid) REFERENCES cate(id);


# cart table
CREATE TABLE cart(
	id INT,
	uid VARCHAR(10),
	pid INT,
    regdate DATE
);

ALTER TABLE cart ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE cart MODIFY id INT AUTO_INCREMENT;
ALTER TABLE cart AUTO_INCREMENT = 1000;
ALTER TABLE cart ADD CONSTRAINT FOREIGN KEY(uid) REFERENCES cust(id)
ON DELETE CASCADE
ON UPDATE CASCADE;
ALTER TABLE cart ADD CONSTRAINT FOREIGN KEY(pid) REFERENCES product(id);



 
