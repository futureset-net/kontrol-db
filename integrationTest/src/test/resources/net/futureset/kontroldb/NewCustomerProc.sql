CREATE PROCEDURE NEW_CUSTOMER(IN firstname VARCHAR (50), IN lastname VARCHAR (50), IN address VARCHAR (100))
    MODIFIES SQL DATA
BEGIN ATOMIC
INSERT INTO CUSTOMER(CUST_ID,FIRSTNAME,LASTNAME,ADDRESS,CITY,STATE,ZIP)
                VALUES (1, firstname, lastname, address, 'LDN', 'NY', '123');
END