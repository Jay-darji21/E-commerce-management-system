CREATE DATABASE inventary;

USE inventary;

CREATE TABLE user(
user_id INT AUTO_INCREMENT PRIMARY KEY,
user_name VARCHAR(40),
age INT,
gender VARCHAR(10)
);

SELECT * FROM user;

CREATE TABLE products(
product_id INT auto_increment PRIMARY KEY,
product_name VARCHAR(40),
description VARCHAR(90),
price INT,
numberOfItemns INT
);


CREATE TABLE ordersForAuthority(
 product_id INT,
 user_id INT PRIMARY KEY,
 product_name VARCHAR(4),
 numberOfProducts INT,
 price INT
);

CREATE TABLE orderHistory(
 user_id INT,
 product_id INT ,
 product_name VARCHAR(40),
 quantity INT,
 price INT,
FOREIGN KEY (user_id) references user(user_id)
);

