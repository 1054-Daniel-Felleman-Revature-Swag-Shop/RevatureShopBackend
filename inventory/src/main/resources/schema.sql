DROP TABLE IF EXISTS inventory;

CREATE TABLE inventory(
    id serial primary key,
    item_name varchar2(255),
    item_price int,
    quantity int,
    category varchar2(1023),
    description varchar2(1023),
    discount numeric(3, 2)
);
