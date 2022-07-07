-- DDL
DROP DATABASE IF EXISTS ticketsajo;
CREATE DATABASE ticketsajo;
USE ticketsajo;

DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS cust;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS pointlist;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS mycoupon;
DROP TABLE IF EXISTS couponlist;
DROP TABLE IF EXISTS theater;
DROP TABLE IF EXISTS seat;
DROP TABLE IF EXISTS schedules;
DROP TABLE IF EXISTS detail_schedules;


CREATE TABLE genre(
   id INT,
   name VARCHAR(100),
   tid INT 
);
ALTER TABLE genre ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE genre ADD CONSTRAINT FOREIGN KEY (tid) REFERENCES genre (id);

CREATE TABLE movie(
	id INT,
    gid INT,
    title VARCHAR(500),
    director VARCHAR(200),
    mainactor VARCHAR(500),
    releasedate DATE,
    posterimg1 VARCHAR(100),
    posterimg2 VARCHAR(100),
    country VARCHAR(100),
    runningtime VARCHAR(50),
    text VARCHAR(500)
);
ALTER TABLE movie ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE movie MODIFY id INT AUTO_INCREMENT;
ALTER TABLE movie AUTO_INCREMENT = 1000;
ALTER TABLE movie ADD CONSTRAINT FOREIGN KEY (gid) REFERENCES genre (id);

CREATE TABLE cust (
   id   VARCHAR(100),
   pwd   VARCHAR(20),
   name VARCHAR(20),
   birth DATE,
   point INT,
   sex   VARCHAR(10)
);
ALTER TABLE cust ADD CONSTRAINT PRIMARY KEY(id);


CREATE TABLE reviews (
   id   INT,
   uid   VARCHAR(100),
   mid   INT,
   star INT,
   text VARCHAR(200),
   regdate DATE
);
ALTER TABLE reviews ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE reviews MODIFY id INT AUTO_INCREMENT;
ALTER TABLE reviews AUTO_INCREMENT = 6000;

CREATE TABLE pointlist (
   id   INT,
   uid   VARCHAR(100),
   price   INT,
   regdate   DATE,
   text VARCHAR(100)
);
ALTER TABLE pointlist ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE pointlist MODIFY id INT AUTO_INCREMENT;
ALTER TABLE pointlist AUTO_INCREMENT = 3000;

-- SEAT TABLE

CREATE TABLE seat(
   id VARCHAR(5),
   r VARCHAR(5),
   c INT
);
ALTER TABLE seat ADD CONSTRAINT PRIMARY KEY(id);

-- THEATER TABLE

CREATE TABLE theater(
    id INT,
    tid VARCHAR(5),
   capacity INT
);
ALTER TABLE theater ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE theater ADD CONSTRAINT FOREIGN KEY(tid) REFERENCES seat(id);

-- SCHEDULES TABLE

CREATE TABLE schedules(
    id INT,
    tid INT,
    mid INT,
    sdate DATE
);
ALTER TABLE schedules ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE schedules MODIFY id INT AUTO_INCREMENT;
ALTER TABLE schedules AUTO_INCREMENT = 2000;
ALTER TABLE schedules ADD CONSTRAINT FOREIGN KEY(tid) REFERENCES theater(id);
ALTER TABLE schedules ADD CONSTRAINT FOREIGN KEY(mid) REFERENCES movie(id);


-- DETAIL_SCHEDULES TABLE

CREATE TABLE detail_schedules(
    sid INT,
    mcnt INT,
    starttime TIME,
    endtime TIME
);
ALTER TABLE detail_schedules ADD CONSTRAINT FOREIGN KEY(sid) REFERENCES schedules(id);
ALTER TABLE detail_schedules ADD CONSTRAINT PRIMARY KEY(sid,mcnt);

 -- detail_reservation
CREATE TABLE detail_reservation(
	id INT,
    sid INT,
    purchasedate DATE,
    mcnt INT,
    seatno VARCHAR(10)
);
ALTER TABLE detail_reservation ADD CONSTRAINT PRIMARY KEY(id);

ALTER TABLE detail_reservation MODIFY id INT AUTO_INCREMENT;
ALTER TABLE detail_reservation AUTO_INCREMENT = 4000;
ALTER TABLE detail_reservation ADD CONSTRAINT FOREIGN KEY (sid) REFERENCES schedules (id);


-- reservation 
CREATE TABLE reservation(
    id INT, -- 결제번호
    rid INT, -- 예약번호
    uid VARCHAR(100), -- 아이디 
    pcnt INT, -- 인원수
    price INT, -- 예매금액
    totalprice INT -- 결제금액
);
ALTER TABLE reservation ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE reservation MODIFY id INT AUTO_INCREMENT;
ALTER TABLE reservation AUTO_INCREMENT = 6000;
ALTER TABLE reservation ADD CONSTRAINT FOREIGN KEY (uid) REFERENCES cust(id);
ALTER TABLE reservation ADD CONSTRAINT FOREIGN KEY (rid) REFERENCES detail_reservation(id);

-- coupon
CREATE TABLE coupon(
   id VARCHAR(100) PRIMARY KEY, -- 쿠폰번호
    cprice INT -- 할인가격 
);
    

-- mycoupon
CREATE TABLE mycoupon(
    id VARCHAR(100), -- 내 쿠폰함 아이디 
    uid VARCHAR(100), -- 회원아이디
    cid VARCHAR(100), -- 쿠폰번호
    used BOOLEAN, -- 쿠폰 사용여부
    udate DATE, -- 사용한 날짜
    rdate DATE -- 발급날짜
);
ALTER TABLE mycoupon ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE mycoupon MODIFY id INT AUTO_INCREMENT;
ALTER TABLE mycoupon AUTO_INCREMENT = 7000;
ALTER TABLE mycoupon ADD CONSTRAINT FOREIGN KEY (uid) REFERENCES cust (id);
ALTER TABLE mycoupon ADD CONSTRAINT FOREIGN KEY (cid) REFERENCES coupon (id);

