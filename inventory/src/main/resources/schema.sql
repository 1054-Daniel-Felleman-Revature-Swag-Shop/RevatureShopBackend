DROP TABLE IF EXISTS inventory;

CREATE TABLE inventory(
    id serial primary key,
    item_name varchar(255),
    item_price int,
    quantity int,
    category varchar(1023),
    description varchar(1023),
    discount numeric(3, 2)
);
