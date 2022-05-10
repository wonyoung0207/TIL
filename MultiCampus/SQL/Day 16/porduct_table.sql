CREATE TABLE product(
	id INT primary KEY auto_increment,
    name VARCHAR(20),
    price INT,
    regdate DATE,
    rate FLOAT
);
ALTER TABLE meat CHANGE colname date abd;

INSERT INTO product VALUES(NULL, 'pants', 10000, SYSDATE(),3.4);

SELECT * FROM product;