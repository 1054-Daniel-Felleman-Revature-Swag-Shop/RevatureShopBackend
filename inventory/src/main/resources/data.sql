DROP TABLE IF EXISTS inventory;

CREATE TABLE inventory (
id INT,
item_name VARCHAR(250) NOT NULL,
item_price INT NOT NULL,
quantity INT NOT NULL);

INSERT INTO inventory (item_name, item_price, quantity, id) VALUES
 ('Rev It Up Hat', 10, 1000, 1), ('Code Like A Boss T-Shirt', 100, 1000, 2), ('Programmer Socks', 1000, 10, 3);

