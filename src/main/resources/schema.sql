DROP TABLE IF EXISTS Postoffice;

CREATE TABLE IF NOT EXISTS `Postoffice` (
    Postnumber VARCHAR (4) NOT NULL,
    Postoffice VARCHAR (30) NOT NULL,
    PRIMARY KEY (Postnumber)
);

DROP TABLE IF EXISTS Customer;

CREATE TABLE IF NOT EXISTS `Customer` (
    CustomerID INTEGER (20) NOT NULL AUTO_INCREMENT,
    Firstname VARCHAR (30) NOT NULL,
    Lastname VARCHAR (30) NOT NULL,
    Addresse VARCHAR (50) NOT NULL,
    Postnumber VARCHAR (4) NOT NULL,
    Tlfnumber VARCHAR (8) NOT NULL,
    Email VARCHAR (20) NOT NULL,
    PRIMARY KEY (CustomerID),
    FOREIGN KEY (Postnumber) REFERENCES Postoffice(Postnumber)
);

DROP TABLE IF EXISTS `Order`;

CREATE TABLE IF NOT EXISTS `Order` (
    OrderID varchar (30) NOT NULL AUTO_INCREMENT,
    Date varchar (10) NOT NULL,
    Totalprice DECIMAL (10) NOT NULL,
    Amount INTEGER (100) NOT NULL,
    CustomerID VARCHAR (20),
    PRIMARY KEY (OrderID),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

DROP TABLE IF EXISTS Product;
CREATE TABLE IF NOT EXISTS `Product` (
    ProductID VARCHAR (11) NOT NULL AUTO_INCREMENT,
    ProductName VARCHAR (20) NOT NULL,
    shortDescription VARCHAR (100) NOT NULL,
    longDescription VARCHAR (100) NOT NULL,
    Price FLOAT NOT NULL,
    ImageURL VARCHAR (100),
    PRIMARY KEY (ProductID),
);

