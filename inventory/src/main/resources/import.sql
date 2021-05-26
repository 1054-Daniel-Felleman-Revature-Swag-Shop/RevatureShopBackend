--CREATE TABLE inventory(
--id int primary key,
--item_name varchar(255),
--item_price int,
--quantity int
--);
-- select id, item_name, item_price, quantity, category, description from inventory
-- // for h2database sorting purpose

INSERT INTO inventory (item_name, item_price, quantity, category, description) VALUES ('Rev It Up Hat', 10, 1000, 'Accessories', 'A sweet hat to ACCELERATE your development!'), ('Code Like A Boss T-Shirt', 100, 1000, 'Clothing', 'Perfect for casual friday!'), ('Programmer Socks', 1000, 10, 'Clothing', 'Scientifically proven to make you a better programmer.');

