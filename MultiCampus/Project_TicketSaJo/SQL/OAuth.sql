DROP TABLE IF EXISTS user;

select * from user;
UPDATE user set role='ROLE_MANAGER' where id=7;
UPDATE user set role='ROLE_ADMIN' where id=6;
desc user;