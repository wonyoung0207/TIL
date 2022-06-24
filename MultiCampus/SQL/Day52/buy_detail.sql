SELECT * FROM addr_list;
SELECT * FROM cart;
SELECT * FROM cust;
SELECT * FROM buy;
SELECT * FROM buy_detail;

SELECT * FROM review;

-- cart 에서 uid 같은거 삭제 
DELETE FROM CART WHERE UID = 'ID05';

SELECT * FROM buy WHERE UID='id03';

SELECT  c.id, c.uid as uid, p.id as pid,p.name as pname,p.imgname1 as pimg1,p.price as pprice, c.count as count,c.size as size FROM cart c
INNER JOIN product p on c.pid=p.id;


SELECT b.id, b.oid, b.pid, b.size, b.cnt,
p.name, p.price, p.imgname1, p.color, p.gender
FROM buy_detail b
INNER JOIN product p ON b.pid = p.id;