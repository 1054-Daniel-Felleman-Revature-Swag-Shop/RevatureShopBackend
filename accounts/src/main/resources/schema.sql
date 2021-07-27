DROP TABLE IF EXISTS point_history;
DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts(
    id serial primary key,
    email varchar(255),
    name varchar(255),
    role int,
    points int
);

CREATE TABLE point_history(
    id serial primary key,
    fk_account int references accounts(id),
    cause varchar(1023),
    change int,
    date date
);