DROP DATABASE IF EXISTS marketdb;
CREATE SCHEMA marketdb;
USE marketdb;

DROP TABLE IF EXISTS `cart`;
DROP TABLE IF EXISTS `cust`;
DROP TABLE IF EXISTS `food`;
DROP TABLE IF EXISTS `cate`;

CREATE TABLE `cart` (
	`cartno`	INT	NOT NULL,
	`orderdate`	DATE	NULL,
	`foodid`	INT	NOT NULL,
	`custid`	VARCHAR(30)	NOT NULL
);


CREATE TABLE `cust` (
	`custid`	VARCHAR(30)	NOT NULL,
	`name`	VARCHAR(30)	NULL,
	`addr`	VARCHAR(100)	NULL
);



CREATE TABLE `food` (
	`foodno`	INT	NOT NULL,
	`name`	VARCHAR(30)	NULL,
	`price`	FLOAT	NULL,
	`regdate`	DATE	NULL,
	`status`	VARCHAR(30)	NULL,
	`cateno`	INT	NULL
);

CREATE TABLE `cate` (
	`cateno`	INT	NULL,
	`catename`	VARCHAR(30)	NULL
);

ALTER TABLE `cart` ADD CONSTRAINT `PK_CART` PRIMARY KEY (
	`cartno`
);

ALTER TABLE `cust` ADD CONSTRAINT `PK_CUST` PRIMARY KEY (
	`custid`
);

ALTER TABLE `food` ADD CONSTRAINT `PK_FOOD` PRIMARY KEY (
	`foodno`
);

ALTER TABLE `cate` ADD CONSTRAINT `PK_CATE` PRIMARY KEY (
	`cateno`
);
