

-- cust insert
INSERT INTO cust VALUES ('id01','pwd01','kim',sysdate(),'010-1598-1515');
INSERT INTO cust VALUES ('id02','pwd02','lee',sysdate(),'010-3598-1245');
INSERT INTO cust VALUES ('id03','pwd03','park',sysdate(),'010-5528-1515');
INSERT INTO cust VALUES ('id04','pwd04','seo',sysdate(),'010-4592-5515');
INSERT INTO cust VALUES ('id05','pwd05','han',sysdate(),'010-8758-2315');
INSERT INTO cust VALUES ('id06','pwd06','woo',sysdate(),'010-1298-7782');
SELECT * FROM cust;

-- addr_list insert
INSERT INTO addr_list VALUES (null,'id01','Seoul','Gangnamgu',1004);
INSERT INTO addr_list VALUES (null,'id02','Busan','Hawondae',724);
INSERT INTO addr_list VALUES (null,'id03','Daegu','Dalseoung',868);
INSERT INTO addr_list VALUES (null,'id04','Daejeon','Yousung',165);
INSERT INTO addr_list VALUES (null,'id05','Incheon','Bupyeong',773);
INSERT INTO addr_list VALUES (null,'id06','Gwangju','Seogu',521);
SELECT * FROM addr_list;


-- Cate Insert
INSERT INTO cate (id, name, tid) VALUES(10,'운동화',null);
INSERT INTO cate (id, name, tid) VALUES(11,'스니커즈',10);
INSERT INTO cate (id, name, tid) VALUES(12,'캔버스화',10);
INSERT INTO cate (id, name, tid) VALUES(20,'스포츠화',null);
INSERT INTO cate (id, name, tid) VALUES(21,'런닝화',20);
INSERT INTO cate (id, name, tid) VALUES(22,'등산화',20);
INSERT INTO cate (id, name, tid) VALUES(23,'축구화',20);
INSERT INTO cate (id, name, tid) VALUES(30,'구두',null);
INSERT INTO cate (id, name, tid) VALUES(31,'로퍼',30);
INSERT INTO cate (id, name, tid) VALUES(32,'뮬/블로퍼',30);
INSERT INTO cate (id, name, tid) VALUES(40,'샌들',null);
INSERT INTO cate (id, name, tid) VALUES(41,'슬라이드',40);
INSERT INTO cate (id, name, tid) VALUES(42,'아쿠아슈즈',40);
INSERT INTO cate (id, name, tid) VALUES(43,'스포츠샌들',40);
INSERT INTO cate (id, name, tid) VALUES(50,'부츠',null);
INSERT INTO cate (id, name, tid) VALUES(51,'앵클부츠',50);
INSERT INTO cate (id, name, tid) VALUES(52,'미들부츠',50);

select * from cate;

-- Review Insert
INSERT INTO review VALUES(null,3000,1,'배송이 빨라서 맘에 들어요', CURRENT_DATE(),'canverse1.png','id01');
INSERT INTO review VALUES(null,3001,2,'생각했던 색깔이랑 약간 달라요', CURRENT_DATE(),'canverse2.png','id02');
INSERT INTO review VALUES(null,3003,3,'생각보다 신발 발볼이 좁아요, 한사이즈 크게 사시는게 좋을듯', CURRENT_DATE(),'running1.png','id03');
INSERT INTO review VALUES(null,3009,4,'맘에 들어요!', CURRENT_DATE(),'roper.png','id04');
INSERT INTO review VALUES(null,3008,5,'선물받은 사람이 아주 좋아해요!', CURRENT_DATE(),'score1.png','id05');


select * from review;

-- product Insert
INSERT INTO product VALUES(NULL,'canvers1',10000,SYSDATE(),'canvers1.png','canvers1.png',12,'red','Men');
INSERT INTO product VALUES(NULL,'canvers2',35000,SYSDATE(),'canvers2.png','canvers2.png',12,'red','Women');
INSERT INTO product VALUES(NULL,'sneakers1',20000,SYSDATE(),'sneakers1.png','sneakers1.png',11,'blue','Women');
INSERT INTO product VALUES(NULL,'running1',30000,SYSDATE(),'running1.png','running1.png',21,'red','Women');
INSERT INTO product VALUES(NULL,'running2',50000,SYSDATE(),'running2.png','running2.png',21,'white','Men');
INSERT INTO product VALUES(NULL,'running3',50000,SYSDATE(),'running3.png','running3.png',21,'gray','Unisex');
INSERT INTO product VALUES(NULL,'mountain1',50000,SYSDATE(),'mountain1.png','mountain1.png',22,'black','Men');
INSERT INTO product VALUES(NULL,'mountain2',50000,SYSDATE(),'mountain2.png','mountain2.png',22,'black','Women');
INSERT INTO product VALUES(NULL,'score1',40000,SYSDATE(),'score1.png','score1.png',23,'black','Men');
INSERT INTO product VALUES(NULL,'roper1',50000,SYSDATE(),'roper.png','roper.png',31,'black','Women');
INSERT INTO product VALUES(NULL,'mule1',15000,SYSDATE(),'mule1.png','mule1.png',32,'white','Men');
INSERT INTO product VALUES(NULL,'slide1',30000,SYSDATE(),'slide1.png','slide1.png',41,'black','Men');
INSERT INTO product VALUES(NULL,'aqua1',20000,SYSDATE(),'aqua1.png','aqua1.png',42,'red','Unisex');
INSERT INTO product VALUES(NULL,'aqua1',20000,SYSDATE(),'aqua1.png','aqua1.png',42,'white','Unisex');
INSERT INTO product VALUES(NULL,'sportsandal1',25000,SYSDATE(),'sportsandal1.png','sportsandal1.png',43,'white','Unisex');
INSERT INTO product VALUES(NULL,'sportsandal1',25000,SYSDATE(),'sportsandal1.png','sportsandal1.png',43,'black','Unisex');
INSERT INTO product VALUES(NULL,'ankle1',40000,SYSDATE(),'ankle1.png','ankle1.png',51,'black','Women');
INSERT INTO product VALUES(NULL,'middleboots1',40000,SYSDATE(),'middleboots1.png','middleboots1.png',52,'black','Women');

SELECT * FROM product;


-- Cart Insert
INSERT INTO cart VALUES(null,1,'id02',3000,260);
INSERT INTO cart VALUES(null,1,'id02',3002,280);
INSERT INTO cart VALUES(null,2,'id02',3003,240);
INSERT INTO cart VALUES(null,1,'id02',3008,220);
INSERT INTO cart VALUES(null,3,'id05',3010,260);

select * from cart;

-- buy insert
INSERT INTO buy VALUES (NULL,'id01','lee','Seoul','seoul','010-1234-1234','문앞에 배송',50000,sysdate());
INSERT INTO buy VALUES (NULL,'id02','kim','Daejeon','Daejeon','010-7894-5612',NULL,70000,sysdate());
INSERT INTO buy VALUES (NULL,'id03','park','Seoul','Daejeon','010-1234-1234',NULL,80000,sysdate());
INSERT INTO buy VALUES (NULL,'id04','choi','Seoul','Daejeon','010-7894-3215','택배함에 배송 부탁드립니다',60000,sysdate());
INSERT INTO buy VALUES (NULL,'id05','choi','Seoul','Daejeon','010-1234-1234','부재시 경비실',100000,sysdate());
SELECT * FROM buy;


-- shoes_cnt insert
INSERT INTO shoes_cnt VALUES (NULL,3000,220,10);
INSERT INTO shoes_cnt VALUES (NULL,3000,240,5);
INSERT INTO shoes_cnt VALUES (NULL,3000,260,5);
INSERT INTO shoes_cnt VALUES (NULL,3001,260,5);
INSERT INTO shoes_cnt VALUES (NULL,3001,280,15);
INSERT INTO shoes_cnt VALUES (NULL,3002,240,10);
INSERT INTO shoes_cnt VALUES (NULL,3003,260,15);
SELECT * FROM shoes_cnt;

-- buy_detail Insert
INSERT INTO buy_detail VALUES(NULL,2001,3001,260,2);
INSERT INTO buy_detail VALUES(NULL,2002,3002,270,1);
INSERT INTO buy_detail VALUES(NULL,2003,3003,280,3);
INSERT INTO buy_detail VALUES(NULL,2004,3001,290,1);
SELECT * FROM buy_detail;
    