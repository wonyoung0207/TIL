CREATE TABLE CUST(
	id VARCHAR(10) PRIMARY KEY,
    pwd VARCHAR(20),
    name  VARCHAR(30)
    
);

CREATE TABLE ITEM(
	id int PRIMARY KEY,
    name VARCHAR(30),
    price FLOAT 
);
# Create
INSERT INTO CUST VALUES('id01',  'pwd01', '이말숙');
INSERT INTO CUST VALUES('id02',  'pwd02', '이말숙');

# Read
SELECT * FROM CUST;
SELECT * FROM CUST WHERE id = 'id01';
SELECT id,pwd FROM CUST;

# Update
UPDATE CUST SET pwd='1111', name='홍말숙' WHERE id='id01';
UPDATE CUST SET PWD='1111', name='홍말숙2' WHERE ID='id01';

# Delete
delete from cust where id='id02';



