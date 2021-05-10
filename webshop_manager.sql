USE webshop_manager;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS City;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS `Order`;
DROP TABLE IF EXISTS Purchase;
DROP TABLE IF EXISTS Ordercontent;
DROP TABLE IF EXISTS Product;
SET FOREIGN_KEY_CHECKS = 1;

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
                      ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `Order` (
                                       OrderID INT (30) NOT NULL AUTO_INCREMENT,
                                       Date varchar (10) NOT NULL,
                                       Totalprice DECIMAL (10) NOT NULL,
                                       Amount INTEGER (100) NOT NULL,
                                       CustomerID VARCHAR (20) NOT NULL,
                                       PRIMARY KEY (OrderID),
                                       FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE Product (
                                        ProductID INT (11) NOT NULL AUTO_INCREMENT,
                                        ProductName VARCHAR (20) NOT NULL,
                                        shortDescription VARCHAR (100) NOT NULL,
                                        longDescription VARCHAR (100) NOT NULL,
                                        Price FLOAT NOT NULL,
                                        ImageURL VARCHAR (100),
                                        PRIMARY KEY (ProductID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Ordercontent (
                                          PurchaseID INT (100) NOT NULL AUTO_INCREMENT,
                                          OrderID INT (30) NOT NULL,
                                          CustomerID VARCHAR (20) NOT NULL,
                                          ProductID INT(10) NOT NULL,
                                          PRIMARY KEY (PurchaseID),
                                          FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
                                          FOREIGN KEY (OrderID) REFERENCES `Order`(OrderID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE Purchase (
                                          PurchaseID INT (30) AUTO_INCREMENT,
                                          CustomerID INTEGER (20) NOT NULL,
                                          ProductID INT (30) NOT NULL,
                                          PRIMARY KEY (PurchaseID),
                                          FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
                                          FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;





INSERT INTO `City` (`zipcode`, `city`) VALUES
('0286', 'Løkka'),
('0273', 'Frogner');


INSERT INTO `Customer` (`CustomerID`, `Firstname`, `Lastname`,
                        `address`, `zipcode`, `Tlfnumber`, `Email`, `Password`) VALUES
(1, 'Hannah', 'Eriksen', 'Frognerveien 1', '0273', '97969594', 'hannah@mail.com', 'password'),
(2, 'Caroline', 'Jetteberg', 'Løkkaveien 2', '0286', '01020304', 'caroline@mail.com', 'password'),
(3, 'Amalie', 'Leiknes', 'Løkkaveien 3', '0286', '81828384', 'amalie@mail.com', 'password');


#INSERT INTO `Order` (`OrderID`, `Date`, `Totalprice`, `Amount`, `CustomerID`) VALUES
#(1, '26.04.2021', 100.00, 2, 1),
#(2, '26.04.2021', 3000.00, 10, 2),
#(3, '28.04.2021', 1500.00, 6, 3);


INSERT INTO `Product` (`ProductID`, `ProductName`, `shortDescription`,
                       `longDescription`, `Price`, `ImageURL`) VALUES
(1, 'godKaffi', 'Ein god kaffi', 'Ein gooooood kaffi', 1000.00, '/images/kaffe.jpg'),
(2, 'bedreKaffi', 'Beittre kaffi', 'Ein beittre kaffi', 2000.00, '/images/kaffe.jpg'),
(3, 'besteKaffi', 'Beste kaffi', 'Den beste kaffi', 3000.00, '/images/kaffe.jpg');

UNLOCK TABLES;