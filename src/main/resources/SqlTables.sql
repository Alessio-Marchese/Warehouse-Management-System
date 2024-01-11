DROP SCHEMA warehouse;
CREATE SCHEMA warehouse;
USE warehouse;

-- this table is for a log-in system so each user have his personal space
CREATE TABLE user (
	id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(200) NOT NULL UNIQUE,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(20) NOT NULL
);

-- this table is made to track user's warehouses
CREATE TABLE warehouse (
 id INT PRIMARY KEY AUTO_INCREMENT,
 location VARCHAR(200) NOT NULL UNIQUE,
 user_id INT NOT NULL REFERENCES user(id)
);

-- a warehouse has many transaction, every time
-- the user add or remove articles there will be a transaction
-- who keep the informations to know what happened
CREATE TABLE transaction (
	id INT PRIMARY KEY AUTO_INCREMENT,
    quantity INT NOT NULL,
    description VARCHAR(200) DEFAULT "Description not available",
    type ENUM('OUT','IN') NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    warehouse_id INT NOT NULL REFERENCES warehouse(id)
);

-- every category is stored inside a warehouse
CREATE TABLE category (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL UNIQUE,
	warehouse_id INT NOT NULL REFERENCES warehouse(id)
);

-- every article is stored inside a category
CREATE TABLE article (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL UNIQUE,
    description VARCHAR(200) DEFAULT "Description not available",
    available_quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    discount DOUBLE DEFAULT 0,
    category_id INT NOT NULL REFERENCES category(id)
);

-- every article has a supplier there can be many of them for a single article
CREATE TABLE supplier (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL UNIQUE,
    email VARCHAR(200) NOT NULL UNIQUE,
    article_id INT NOT NULL REFERENCES article(id)
);
