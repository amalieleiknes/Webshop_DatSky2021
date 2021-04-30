
DROP TABLE IF EXISTS Postoffice;

CREATE TABLE IF NOT EXISTS `Postoffice` (
    Postnumber VARCHAR (4) NOT NULL,
    Postoffice VARCHAR (30) NOT NULL,
    PRIMARY KEY (Postnumber)
);

DROP TABLE IF EXISTS Customer;

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
    PRIMARY KEY (ProductID),
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
