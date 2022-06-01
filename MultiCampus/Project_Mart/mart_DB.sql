CREATE TABLE meat(
	name VARCHAR(20) primary KEY,
    price float,
    status VARCHAR(20),
    date DATE
);

SELECT * FROM meat;


CREATE TABLE fish(
	name VARCHAR(20) primary KEY,
    price float,
    status VARCHAR(20),
    date DATE
);


SELECT * FROM fish;

INSERT INTO fish VALUES('test1',1000,'clean',sysdate());

SELECT * FROM fruit;
INSERT INTO fruit VALUES('fruit1',1000,'clean',sysdate());



CREATE TABLE vegetable(
	name VARCHAR(20) primary KEY,
    price float,
    status VARCHAR(20),
    date DATE
);

SELECT * FROM vegetable;
INSERT INTO vegetable VALUES('vegetable',1000,'clean',sysdate());

