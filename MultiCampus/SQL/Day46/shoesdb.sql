SELECT * FROM cust;
SELECT * FROM cate;
SELECT * FROM product;
SELECT * FROM review;
SELECT * FROM shoes_cnt;
SELECT * FROM cart;
SELECT * FROM addr_list;
SELECT * FROM buy;
SELECT * FROM buy_detail;

-- Product CRUD 
-- Product Insert
INSERT INTO product VALUES(NULL,'canvers1',10000,curdate(),'canvers1.png',null,12,'colorTest','Gender Men');


-- product Select
SELECT p.id
as id, p.name as name, p.price, p.regdate, p.cid as cid, p.imgname1 as imgname1, p.imgname2 as imgname2,
c.name as catename , 
scnt.cnt as cnt, scnt.size as size ,
re.uid as review_uid, re.text as review_text
FROM product p
INNER JOIN cate c ON p.cid = c.id
LEFT JOIN shoes_cnt scnt ON p.id = scnt.pid
LEFT JOIN review re ON p.id = re.pid
WHERE p.id = 3001;


-- Product select All 
SELECT p.id
as id, p.name as name, p.price, p.regdate, p.cid as cid, p.imgname1 as imgname1, p.imgname2 as imgname2,
c.name as catename , 
scnt.cnt as cnt, scnt.size as size ,
re.uid as review_uid, re.text as review_text
FROM product p
INNER JOIN cate c ON p.cid = c.id
LEFT JOIN shoes_cnt scnt ON p.id = scnt.pid
LEFT JOIN review re ON p.id = re.pid;


-- Product Update
UPDATE product SET 
NAME='canvers2Test', PRICE=25500, regdate=curdate(), 
imgname1='imgTest3', imgname2='imgTest3', 
cid=11, color='ColorTest', GENDER='Gender Men' WHERE id=3018;


-- Product Delete
DELETE FROM product WHERE id=3018;



