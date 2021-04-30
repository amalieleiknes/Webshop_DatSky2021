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