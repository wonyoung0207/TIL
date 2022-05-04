-- DDL

DROP DATABASE IF EXISTS marketdb;
CREATE SCHEMA marketdb;
USE marketdb;

DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS order_detail;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS cust;
DROP TABLE IF EXISTS cate;



CREATE TABLE cust(
custno VARCHAR(30),
pwd VARCHAR(30) NOT NULL,
custname VARCHAR(30) NOT NULL,
addr VARCHAR(100) NOT NULL,
phone VARCHAR(30) NOT NULL
);

ALTER TABLE cust ADD CONSTRAINT PRIMARY KEY(custno);


CREATE TABLE cate(
   cateno INT,
    catename VARCHAR(30),
    cateno2 INT
);
ALTER TABLE cate ADD CONSTRAINT PRIMARY KEY(cateno);
ALTER TABLE cate ADD FOREIGN KEY (cateno2)
REFERENCES cate (cateno);

CREATE TABLE food(
   foodno INT,
    foodname VARCHAR(20) NOT NULL,
    price FLOAT NOT NULL,
    regdate DATE NOT NULL,
    cateno INT NOT NULL
);
ALTER TABLE food ADD CONSTRAINT PRIMARY KEY(foodno);
ALTER TABLE food MODIFY foodno INT AUTO_INCREMENT;
ALTER TABLE food AUTO_INCREMENT = 100;
ALTER TABLE food ADD CONSTRAINT CHECK (price > 0);

ALTER TABLE food ADD FOREIGN KEY (cateno) 
REFERENCES cate (cateno);



-- cart
CREATE TABLE cart(
cartno INT, -- 장바구니번호
foodno INT, -- 상품번호
custno VARCHAR(30), -- 아이디
cartcount INT, -- 수량
pricecheck FLOAT -- 가격확인


);
ALTER TABLE cart ADD CONSTRAINT PRIMARY KEY(cartno);
ALTER TABLE cart ADD FOREIGN KEY (foodno)
REFERENCES food (foodno);
ALTER TABLE cart ADD FOREIGN KEY (custno)
REFERENCES cust (custno);
ALTER TABLE cart MODIFY cartno INT AUTO_INCREMENT;
ALTER TABLE cart AUTO_INCREMENT = 1;


CREATE TABLE orders(
   orderno INT, -- 주문서번호
    regdate DATETIME, -- 주문날짜
    fianlprice FLOAT, -- 최종가격
    paymode VARCHAR(30), -- 결재방법
    addr VARCHAR(100), -- 배송지 정보
    arrdate DATE, -- 배송예정일
    custno VARCHAR(30) -- 아이디 
);
ALTER TABLE orders ADD CONSTRAINT PRIMARY KEY(orderno);
ALTER TABLE orders ADD FOREIGN KEY (custno)
REFERENCES cust (custno);
ALTER TABLE orders MODIFY orderno INT AUTO_INCREMENT;
ALTER TABLE orders AUTO_INCREMENT = 1000;
-- ALTER TABLE orders ALTER COLUMN addr SET DEFAULT addr;



CREATE TABLE order_detail (
order_detailno INT,
orderstatus VARCHAR(30) NOT NULL,
foodno INT,
orderno INT,
count INT
);
ALTER TABLE order_detail ADD CONSTRAINT PRIMARY KEY(order_detailno);
ALTER TABLE order_detail ADD FOREIGN KEY (foodno)
REFERENCES food (foodno);
ALTER TABLE order_detail ADD FOREIGN KEY (orderno)
REFERENCES orders (orderno);
ALTER TABLE order_detail MODIFY order_detailno INT AUTO_INCREMENT;
ALTER TABLE order_detail AUTO_INCREMENT = 10000;