SELECT * FROM buy;
SELECT * FROM buy_detail;
INSERT INTO buy VALUES (NULL,'id01','kim','Seoul','010-1234-1234','문앞에 배송',40000,sysdate());
INSERT INTO buy VALUES (NULL,'id01','kim','Seoul','010-1234-1234','문앞에 배송',30000,sysdate());




SELECT b.id, b.uid, b.recipient, b.recipient_addr, b.recipient_phonenumber,
b.request, b.price, b.regdate, 
bd.pid , bd.size, bd.cnt
FROM buy b
INNER JOIN buy_detail bd ON b.id = bd.oid
WHERE b.uid = "id02";
