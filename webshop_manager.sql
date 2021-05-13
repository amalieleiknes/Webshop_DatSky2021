USE webshop_manager;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS SPRING_SESSION;
DROP TABLE IF EXISTS SPRING_SESSION_ATTRIBUTES;
DROP TABLE IF EXISTS City;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS `Order`;
DROP TABLE IF EXISTS Ordercontent;
DROP TABLE IF EXISTS Product;
SET FOREIGN_KEY_CHECKS = 1;


# Code below is borrowed from https://docs.spring.io/spring-session/docs/current/api/org/springframework/session/jdbc/JdbcIndexedSessionRepository.html. Downloaded 10.05.2021
CREATE TABLE SPRING_SESSION (
                                PRIMARY_ID CHAR(36) NOT NULL,
                                SESSION_ID CHAR(36) NOT NULL,
                                CREATION_TIME BIGINT NOT NULL,
                                LAST_ACCESS_TIME BIGINT NOT NULL,
                                MAX_INACTIVE_INTERVAL INT NOT NULL,
                                EXPIRY_TIME BIGINT NOT NULL,
                                PRINCIPAL_NAME VARCHAR(100),
                                CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
                                           SESSION_PRIMARY_ID CHAR(36) NOT NULL,
                                           ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
                                           ATTRIBUTE_BYTES BIGINT NOT NULL,
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);

CREATE INDEX SPRING_SESSION_ATTRIBUTES_IX1 ON SPRING_SESSION_ATTRIBUTES (SESSION_PRIMARY_ID);

# Code from documentation ends here.

CREATE TABLE City (
                                            zipcode VARCHAR (4) NOT NULL,
                                            city VARCHAR (30) NOT NULL,
                                            PRIMARY KEY (zipcode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE Customer (
                                          customerID VARCHAR (20) NOT NULL,
                                          firstname VARCHAR (30),
                                          lastname VARCHAR (30),
                                          address VARCHAR (50),
                                          zipcode VARCHAR (4),
                                          tlfnumber VARCHAR (8),
                                          email VARCHAR (20) NOT NULL,
                                          password VARCHAR (20) NOT NULL,
                                          PRIMARY KEY (customerID),
                                          FOREIGN KEY (zipcode) REFERENCES City(zipcode)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `Order` (
                                       orderID VARCHAR (30) NOT NULL,
                                       orderDate varchar (10) NOT NULL,
                                       totalprice DECIMAL (10) NOT NULL,
                                       amount INTEGER (100) NOT NULL,
                                       customerID VARCHAR (20) NOT NULL,
                                       PRIMARY KEY (orderID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `Product` (
                                        productID INT (11) NOT NULL AUTO_INCREMENT,
                                        productName VARCHAR (20) NOT NULL,
                                        shortDescription VARCHAR (100) NOT NULL,
                                        longDescription VARCHAR (100) NOT NULL,
                                        price FLOAT NOT NULL,
                                        imageURL VARCHAR (100),
                                        PRIMARY KEY (productID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Ordercontent (
                                          purchaseID INT (100) NOT NULL AUTO_INCREMENT,
                                          orderID VARCHAR (30) NOT NULL,
                                          customerID VARCHAR (20) NOT NULL,
                                          productID INT(10) NOT NULL,
                                          PRIMARY KEY (purchaseID),
                                          FOREIGN KEY (productID) REFERENCES Product(productID),
                                          FOREIGN KEY (orderID) REFERENCES `Order`(orderID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;





INSERT INTO `City` (`zipcode`, `city`) VALUES
('0286', 'Løkka'),
('0273', 'Frogner');


INSERT INTO `Customer` (`customerID`, `firstname`, `lastname`,
                        `address`, `zipcode`, `tlfnumber`, `email`, `password`) VALUES
(1, 'Hannah', 'Eriksen', 'Frognerveien 1', '0273', '97969594', 'hannah@mail.com', 'password'),
(2, 'Caroline', 'Jetteberg', 'Løkkaveien 2', '0286', '01020304', 'caroline@mail.com', 'password'),
(3, 'Amalie', 'Leiknes', 'Løkkaveien 3', '0286', '81828384', 'amalie@mail.com', 'password');


INSERT INTO `Order` (`orderID`, `orderDate`, `totalprice`, `amount`, `customerID`) VALUES
(1, '26.04.2021', 100.00, 2, 1),
(2, '26.04.2021', 3000.00, 10, 2),
(3, '28.04.2021', 1500.00, 6, 3);


INSERT INTO `Product` (`productID`, `productName`, `shortDescription`,
                       `longDescription`, `price`, `imageURL`) VALUES
(1, 'godKaffi', 'Ein god kaffi', 'Ein gooooood kaffi', 1000.00, '/images/kaffe.jpg'),
(2, 'bedreKaffi', 'Beittre kaffi', 'Ein beittre kaffi', 2000.00, '/images/kaffe.jpg'),
(3, 'besteKaffi', 'Beste kaffi', 'Den beste kaffi', 3000.00, '/images/kaffe.jpg');

UNLOCK TABLES;