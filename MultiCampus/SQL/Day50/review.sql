SELECT * FROM review;
SELECT * FROM buy;
SELECT * FROM buy_detail;
SELECT * FROM cart;
SELECT * FROM cust;


SELECT b.id, b.uid, b.recipient, b.recipient_addr,b.recipient_addr_detail,
b.recipient_phonenumber, b.request, b.price, b.regdate,
bd.pid , bd.size, bd.cnt,
p.name, p.price AS price_one,p.imgname1
FROM buy b
INNER JOIN buy_detail bd ON b.id = bd.oid
INNER JOIN product p ON p.id = bd.pid;



INSERT INTO buy VALUES (NULL,'id02','lee','Daejeon','010-7894-5612',NULL,70000,sysdate());
INSERT INTO buy_detail VALUES(NULL,2002,3002,270,1);


SELECT r.id, r.pid, r.star, r.text, r.regdate, r.filename, r.uid,
p.name, p.price, p.imgname1, p.color
FROM review r
INNER JOIN product p ON p.id = r.pid
WHERE uid='id02';