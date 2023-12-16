CREATE PROCEDURE "NEW_CUSTOMER"(
    firstname VARCHAR (50),
    lastname VARCHAR (50),
    address VARCHAR (100))
    LANGUAGE SQL AS $$
INSERT INTO "CUSTOMER"("CUST_ID", "FIRSTNAME", "LASTNAME", "ADDRESS", "CITY", "STATE", "ZIP")
VALUES (1, firstname, lastname, address, 'LDN', 'NY', '123');
$$