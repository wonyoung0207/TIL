DROP TABLE IF EXISTS user;

CREATE TABLE user(
   name VARCHAR(100),
   email VARCHAR(100),
   picture VARCHAR(100)
);

select * from user;
UPDATE user set role='ROLE_MANAGER' where id=7;
UPDATE user set role='ROLE_ADMIN' where id=6;
desc user;