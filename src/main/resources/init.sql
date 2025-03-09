CREATE SCHEMA IF NOT EXISTS test;
SET SCHEMA test;

--DROP TABLE employees;

CREATE TABLE IF NOT EXISTS employees(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    surname VARCHAR(20)
);

--INSERT INTO employees(name, surname) VALUES ('Ben', 'Braun');
