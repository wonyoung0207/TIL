-- DML

-- cust data
INSERT INTO cust VALUES ('ID01','PWD01','JAMES','제주도 서귀포','010-1234-5678');
INSERT INTO cust VALUES ('ID02','PWD02','TOMAS','서울시 용산구','010-1234-5678');
INSERT INTO cust VALUES ('ID03','PWD03','JOHN','울릉도 ','010-1234-5678');


-- cate data
-- 10=신선, 20=냉동
INSERT INTO cate VALUES (10,'신선',NULL);
INSERT INTO cate VALUES (11,'생선',10);
INSERT INTO cate VALUES (12,'고기',10);
INSERT INTO cate VALUES (13,'야채',10);
INSERT INTO cate VALUES (14,'과일',10);
INSERT INTO cate VALUES (20,'냉동',NULL);
INSERT INTO cate VALUES (21,'생선',20);
INSERT INTO cate VALUES (22,'고기',20);
INSERT INTO cate VALUES (23,'야채',20);
INSERT INTO cate VALUES (24,'과일',20);

-- food data
INSERT INTO food VALUES (NULL,'소고기',20000,SYSDATE(),12);
INSERT INTO food VALUES (NULL,'고등어',1000,SYSDATE(),11);
INSERT INTO food VALUES (NULL,'상추',500,SYSDATE(),13);
INSERT INTO food VALUES (NULL,'두리안',10000,SYSDATE(),14);
INSERT INTO food VALUES (NULL,'닭',6000,SYSDATE(),22);
INSERT INTO food VALUES (NULL,'갈치',10000,SYSDATE(),21);
INSERT INTO food VALUES (NULL,'대파',1000,SYSDATE(),23);
INSERT INTO food VALUES (NULL,'망고',10000,SYSDATE(),24);


-- cart data
INSERT INTO cart VALUES (NULL,100,'ID01',2,40000);

-- orders data
INSERT INTO orders VALUES (NULL,SYSDATE(),40000,'현금','제주도 서귀포',date_add(SYSDATE(), interval 2 day),'ID01');


-- order_detail data
INSERT INTO order_detail VALUES (NULL,'배송중',100,1000,2);


SELECT * FROM cust;
SELECT * FROM food;
SELECT * FROM cate;
SELECT * FROM cart;
SELECT * FROM orders;
SELECT * FROM order_detail;
SELECT DATE_FORMAT(orders.regdate, '%Y%m%d %H%i%s') FROM orders;

SELECT DATE_FORMAT(SYSDATE(), '%H%i%s') AS result;
SELECT SYSDATE() AS result;

CREATE VIEW v_food 
AS
SELECT f.foodname,f.price,od.count,f.price*od.count AS totalprice FROM order_detail od
INNER JOIN food f ON  od.foodno = f.foodno;

SELECT * FROM v_food;

CREATE VIEW coupang
AS
SELECT o.regdate,IF ((24-DATE_FORMAT(o.regdate,'%H')) > 1,'익일배송','당일배송') FROM orders o;

SELECT * FROM coupang;