

# INSERT DATA

# cust data
SELECT * FROM cust;
INSERT INTO cust VALUES ('id01', 'lee', 'Busan', '2019-03-02');
INSERT INTO cust(id, name, regdate) VALUES ('id02', 'kim', '2020-05-02'); # not null 의 데이터를 기본값으로 넣고 싶을 경우 이렇게 쓴다. 

# cate date
SELECT * FROM cate;

INSERT INTO cate VALUES (10, 'pants', NULL);
INSERT INTO cate VALUES (11, 'short pants', 10); # 10번 품목인 pants를 참조한다. -> 자기 자신 참조 
INSERT INTO cate VALUES (20, 'shirts', NULL); 
INSERT INTO cate VALUES (21, 'short shirts', 20); 

SELECT * FROM cate c1
INNER JOIN cate c2 ON c1.pid = c2.id;


# product date
SELECT * FROM product;

INSERT INTO product VALUES(NULL, 'levis', 10000, curdate(),11);
# INSERT INTO product VALUES(NULL, 'bangbang', -10000, curdate(),11); # 음수면 안된다. 
INSERT INTO product VALUES(NULL, 'bangbang', 20000, curdate(),11);
INSERT INTO product VALUES(NULL, 'levis', 10000, curdate(),21);
INSERT INTO product VALUES(NULL, 'bangbang', 20000, curdate(),21);


# 제품의 정보를 출력한다. 
# 카테고리 명도 출력한다. 
SELECT * FROM product p
INNER JOIN cate c ON p.cid = c.id;

# cart date
SELECT * FROM cart;

INSERT INTO cart VALUES (NULL, 'id01', 1000, CURDATE());
INSERT INTO cart VALUES (NULL, 'id01', 1001, CURDATE());
INSERT INTO cart VALUES (NULL, 'id01', 1003, CURDATE());


# cart 정보 출력
# 사용자 이름, 상품 이름 가격, 카테고리 이름
SELECT * FROM cart;
SELECT * FROM product;
SELECT * FROM cust;
SELECT * FROM cate;


SELECT ca.id AS orderID , cu.id AS uID, cu.name AS uname, p.id AS pID, p.name AS pname, p.price, ca.regdate 
FROM cart ca
INNER JOIN cust cu ON ca.uid = cu.id
INNER JOIN product p ON ca.pid = p.id
INNER JOIN cate ON cate.id = p.cid;


-- DELETE FROM cust WHERE id = 'id001';
-- UPDATE cust SET id='id001' WHERE id='id01';

# Make View Table
CREATE VIEW v_cart
AS
SELECT ca.id AS orderID , cu.id AS uID, cu.name AS uname, p.id AS pID, p.name AS pname, p.price, ca.regdate 
FROM cart ca
INNER JOIN cust cu ON ca.uid = cu.id
INNER JOIN product p ON ca.pid = p.id
INNER JOIN cate ON cate.id = p.cid;

UPDATE cart SET regdate = '2020-05-11' WHERE id='1000';
SELECT * FROM v_cart;
UPDATE v_cart SET regdate = '2019-05-04' WHERE id='1001';

SELECT * FROM v_cart
WHERE uid = 'id01';


