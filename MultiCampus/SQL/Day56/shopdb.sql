CREATE TABLE cust (
	ID VARCHAR(10) PRIMARY KEY,
    PWD VARCHAR(20),
    NAME VARCHAR(20)
);

INSERT INTO cust VALUES('id01','pwd01','james');


CREATE TABLE product(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(30),
    PRICE INT,
    REGDATE DATE,
    RATE FLOAT
);