CREATE DATABASE test;
USE test;

CREATE TABLE IF NOT EXISTS `Postoffice` (
                                            Postnumber VARCHAR (4) NOT NULL,
                                            Postoffice VARCHAR (30) NOT NULL,
                                            PRIMARY KEY (Postnumber)
);




CREATE TABLE IF NOT EXISTS `Customer` (
                                          CustomerID VARCHAR (20) NOT NULL,
                                          Firstname VARCHAR (30),
                                          Lastname VARCHAR (30),
                                          Addresse VARCHAR (50),
                                          Postnumber VARCHAR (4),
                                          Tlfnumber VARCHAR (8),
                                          Email VARCHAR (20) NOT NULL,
                                          Password VARCHAR (20) NOT NULL,
                                          PRIMARY KEY (CustomerID),
                                          FOREIGN KEY (Postnumber) REFERENCES Postoffice(Postnumber)
);

DROP TABLE IF EXISTS `Order`;

CREATE TABLE IF NOT EXISTS `Order` (
                                       OrderID INT (30) NOT NULL AUTO_INCREMENT,
                                       Date varchar (10) NOT NULL,
                                       Totalprice DECIMAL (10) NOT NULL,
                                       Amount INTEGER (100) NOT NULL,
                                       CustomerID VARCHAR (20) NOT NULL,
                                       PRIMARY KEY (OrderID),
                                       FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

DROP TABLE IF EXISTS Product;
CREATE TABLE IF NOT EXISTS `Product` (
                                         ProductID INT (11) NOT NULL AUTO_INCREMENT,
                                         ProductName VARCHAR (20) NOT NULL,
                                         shortDescription VARCHAR (100) NOT NULL,
                                         longDescription VARCHAR (100) NOT NULL,
                                         Price FLOAT NOT NULL,
                                         ImageURL VARCHAR (100),
                                         PRIMARY KEY (ProductID)
);

DROP TABLE IF EXISTS Ordercontent;

CREATE TABLE IF NOT EXISTS `Ordercontent` (
                                              PurchaseID INT (100) NOT NULL AUTO_INCREMENT,
                                              OrderID INT (30) NOT NULL,
                                              CustomerID VARCHAR (20) NOT NULL,
                                              ProductID INT(10) NOT NULL,
                                              PRIMARY KEY (PurchaseID),
                                              FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
                                              FOREIGN KEY (OrderID) REFERENCES `Order`(OrderID)
);

DROP TABLE IF EXISTS `Purchase`;

CREATE TABLE IF NOT EXISTS `Purchase` (
                                          PurchaseID INT (30) AUTO_INCREMENT,
                                          CustomerID INTEGER (20) NOT NULL,
                                          ProductID INT (30) NOT NULL,
                                          PRIMARY KEY (PurchaseID),
                                          FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
                                          FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);





INSERT INTO `Postoffice` (`Postnumber`, `Postoffice`) VALUES
('0286', 'Løkka'),
('0273', 'Frogner');

INSERT INTO `Customer` (`CustomerID`, `Firstname`, `Lastname`,
                        `Addresse`, `Postnumber`, `Tlfnumber`, `Email`, `Password`) VALUES
(1, 'Hannah', 'Eriksen', 'Frognerveien 1', '0273', '97969594', 'hannah@mail.com', 'password'),
(2, 'Caroline', 'Jetteberg', 'Løkkaveien 2', '0286', '01020304', 'caroline@mail.com', 'password'),
(3, 'Amalie', 'Leiknes', 'Løkkaveien 3', '0286', '81828384', 'amalie@mail.com', 'password');

INSERT INTO `Order` (`OrderID`, `Date`, `Totalprice`, `Amount`, `CustomerID`) VALUES
(1, '26.04.2021', 100.00, 2, 1),
(2, '26.04.2021', 3000.00, 10, 2),
(3, '28.04.2021', 1500.00, 6, 3);


INSERT INTO `Product` (`ProductID`, `ProductName`, `shortDescription`,
                       `longDescription`, `Price`, `ImageURL`) VALUES
(1, 'godKaffi', 'Ein god kaffi', 'Ein gooooood kaffi', 1000.00, '/images/kaffe.jpg'),
(2, 'bedreKaffi', 'Beittre kaffi', 'Ein beittre kaffi', 2000.00, '/images/kaffe.jpg'),
(3, 'besteKaffi', 'Beste kaffi', 'Den beste kaffi', 3000.00, '/images/kaffe.jpg');