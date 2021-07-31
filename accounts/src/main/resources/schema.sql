DROP TABLE IF EXISTS point_history;
DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts(
    id serial primary key,
    email varchar(255),
    name varchar(255),
    role int,
    points numeric(10,2),
    email_subscription boolean
);

CREATE TABLE point_history(
    id serial primary key,
    fk_account int references accounts(id),
    cause varchar(1023),
    change numeric(10,2),
    date date
);
