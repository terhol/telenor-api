create table product
(
    id                   integer      AUTO_INCREMENT,
    product_type      varchar(30)  NOT NULL,
    product_properties varchar(300) NOT NULL,
    price           varchar(300) NOT NULL,
    store_address      varchar(300)      NOT NULL,

    PRIMARY KEY (id)
);

INSERT INTO product (product_type, product_properties, price, store_address)
SELECT * FROM CSVREAD('classpath:product_data.csv');