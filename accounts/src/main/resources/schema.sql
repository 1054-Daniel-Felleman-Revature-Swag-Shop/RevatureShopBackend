DROP TABLE IF EXISTS point_history;
DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts(
    id serial primary key,
    email varchar,
    name varchar,
    role int,
    points int
);

CREATE TABLE point_history(
    id serial primary key,
    fk_account int references accounts(id),
    cause varchar,
    change int,
    date date
);