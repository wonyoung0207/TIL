
-- cust insert
INSERT INTO cust VALUES ('id01','pwd01','kim','Seoul',sysdate());
INSERT INTO cust VALUES ('id02','pwd02','lee','Busan',sysdate());
INSERT INTO cust VALUES ('id03','pwd03','park','Daegu',sysdate());
INSERT INTO cust VALUES ('id04','pwd04','seo','Daejeon',sysdate());
INSERT INTO cust VALUES ('id05','pwd05','han','Incheon',sysdate());
INSERT INTO cust VALUES ('id06','pwd06','woo','Gwangju',sysdate());
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
INSERT INTO cate (id, name, tid) VALUES(21,'런닝화',null);
INSERT INTO cate (id, name, tid) VALUES(23,'등산화',null);
INSERT INTO cate (id, name, tid) VALUES(31,'로퍼',null);
select * from cate;

-- Review Insert
INSERT INTO review (pid, star, text , regdate) VALUES(null,1,'배송이 빨라서 맘에 들어요', CURRENT_DATE());
INSERT INTO review (pid, star, text , regdate) VALUES(null,2,'생각했던 색깔이랑 약간 달라요', CURRENT_DATE());
INSERT INTO review (pid, star, text , regdate) VALUES(null,3,'생각보다 신발 발볼이 좁아요, 한사이즈 크게 사시는게 좋을듯', CURRENT_DATE());
INSERT INTO review (pid, star, text , regdate) VALUES(null,4,'맘에 들어요!', CURRENT_DATE());
INSERT INTO review (pid, star, text , regdate) VALUES(null,5,'선물받은 사람이 아주 좋아해요!', CURRENT_DATE());
select * from review;

-- product Insert
INSERT INTO product VALUES(NULL,"canvers1",10000,SYSDATE(),"canvers1.png",11,"red","Men");
INSERT INTO product VALUES(NULL,"sneakers1",20000,SYSDATE(),"sneakers1.png",12,"blue","Women");
INSERT INTO product VALUES(NULL,"running1",30000,SYSDATE(),"running1.png",21,"red","Women");
INSERT INTO product VALUES(NULL,"score1",40000,SYSDATE(),"score1.png",23,"black","Men");
INSERT INTO product VALUES(NULL,"roper1",50000,SYSDATE(),"roper.png",31,"red","Women");

-- Cart Insert
INSERT INTO cart (name, count) VALUES( '나이키운동화', 1);
INSERT INTO cart (name, count) VALUES( '나이키축구화', 1 );
INSERT INTO cart (name, count) VALUES('나이키앵클부츠',2);
INSERT INTO cart (name, count) VALUES( '나이키샌들', 1 );
INSERT INTO cart ( name, count) VALUES( '나이키운동화', 1);
select * from cart;

-- buy insert
INSERT INTO buy VALUES (NULL,'id01','kim','lee','Seoul','1234','문앞에 배송',20000,sysdate());
INSERT INTO buy VALUES (NULL,'id02','lee','lee','Seoul','1234','문앞에 배송',20000,sysdate());
INSERT INTO buy VALUES (NULL,'id03','choi','lee','Seoul','1234','문앞에 배송',20000,sysdate());
INSERT INTO buy VALUES (NULL,'id04','woo','lee','Seoul','1234','문앞에 배송',20000,sysdate());
INSERT INTO buy VALUES (NULL,'id05','park','lee','Seoul','1234','문앞에 배송',20000,sysdate());
SELECT * FROM buy;

-- shoes_cnt insert
INSERT INTO shoes_cnt VALUES (3001,260,5);
INSERT INTO shoes_cnt VALUES (3001,280,15);
INSERT INTO shoes_cnt VALUES (3002,240,10);
INSERT INTO shoes_cnt VALUES (3003,260,15);
SELECT * FROM shoes_cnt;

-- buy_detail Insert
INSERT INTO buy_detail VALUES(NULL,2001,3001,260,2);
INSERT INTO buy_detail VALUES(NULL,2002,3002,270,1);
INSERT INTO buy_detail VALUES(NULL,2003,3003,280,3);
INSERT INTO buy_detail VALUES(NULL,2004,3001,290,1);
SELECT * FROM buy_detail;