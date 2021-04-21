CREATE TABLE IF NOT EXISTS `Customer` (
    `CustomerID` VARCHAR (20) NOT NULL,
    `Firstname` VARCHAR (30) NOT NULL,
    `Lastname` VARCHAR (30) NOT NULL,
    `Addresse` VARCHAR (50) NOT NULL,
    `Postnumber` VARCHAR,
    `Tlfnumber` VARCHAR (8) NOT NULL,
    `Emailaddresse` VARCHAR (20) NOT NULL,
    `Password` VARCHAR (500) NOT NULL,
    PRIMARY KEY (`CustomerID`),
    FOREIGN KEY (`Postnumber`) REFERENCES `Postoffice`(`Postnumber`)
);

CREATE TABLE IF NOT EXISTS `Order` (
    `OrderID` VARCHAR (30) NOT NULL,
    `Date` CURRENT_DATE (10) NOT NULL,
    `Totalprice` DOUBLE (100) NOT NULL,
    `Amount` INT (100) NOT NULL,
    `CustomerID` VARCHAR,
    PRIMARY KEY (`OrderID`),
    FOREIGN KEY (`CustomerID`) REFERENCES `Customer`(`CustomerID`)
);

CREATE TABLE IF NOT EXISTS `Postoffice` (
    `Postnumber` VARCHAR (4) NOT NULL,
    `Postoffice` VARCHAR (30) NOT NULL,
    PRIMARY KEY (`Postnumber`)
);


