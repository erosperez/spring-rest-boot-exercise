CREATE schema if not exists test;  

CREATE TABLE test.users
(
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    birthdate varchar(10) DEFAULT NULL,
    PRIMARY KEY (id)
);