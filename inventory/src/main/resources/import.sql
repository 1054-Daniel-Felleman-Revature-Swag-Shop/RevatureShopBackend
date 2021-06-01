--CREATE TABLE inventory(
--id int primary key,
--item_name varchar(255),
--item_price int,
--quantity int
--);
-- select id, item_name, item_price, quantity, category, description from inventory
-- // for h2database sorting purpose

INSERT INTO inventory (item_name, item_price, quantity, category, description)
VALUES ('Rev It Up Hat', 10, 1000, 'Misc', 'A sweet hat to ACCELERATE your development!'),
       ('Code Like A Boss T-Shirt', 100, 1000, 'Unisex Shirt', 'Perfect for casual friday!'),
       ('Programmer Socks', 1000, 10, 'Misc', 'Scientifically proven to make you a better programmer.'),
       ('Karate T-Shirt', 50, 120, 'Unisex Shirt', 'Do you have black-belt, white-belt, or any belt at home? If you do, then buy this shirt.'),
       ('SQl T-Shirt', 1000, 10, 'Women''s Shirt', 'Scientifically proven to make you a better programmer.'),
       ('dummy shirt1', 100, 1000, 'Unisex Shirt', 'Perfect for casual friday!'),
       ('dummy shirt2', 100, 1000, 'Unisex Shirt', 'Perfect for casual friday!'),
       ('dummy shirt3', 100, 1000, 'Unisex Shirt', 'Perfect for casual friday!'),
       ('dummy shirt4', 100, 1000, 'Unisex Shirt', 'Perfect for casual friday!'),
       ('dummy shirt5', 100, 1000, 'Unisex Shirt', 'Perfect for casual friday!'),
       ('dummy shirt6', 100, 1000, 'Unisex Shirt', 'Perfect for casual friday!'),
       ('Programmer Mug', 40, 400, 'Mug', 'Once you drink from this coffee mug, you will be able to continue coding for 10 straight hours, NONSTOP!.');

