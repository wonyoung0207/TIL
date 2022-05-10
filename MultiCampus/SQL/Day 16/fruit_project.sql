CREATE TABLE fruit(
	name VARCHAR(20) primary key,
    price FLOAT,
	status VARCHAR(20),
    date DATE
);

INSERT INTO fruit VALUES("test", 1000, "clean", SYSDATE());

