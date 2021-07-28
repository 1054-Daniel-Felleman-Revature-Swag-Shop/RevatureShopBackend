-- CREATE TABLE inventory(
--     id serial primary key,
--     item_name varchar(255),
--     item_price int,
--     quantity int,
--     category varchar,
--     description varchar,
--	   is_Featured boolean
-- );
-- select id, item_name, item_price, quantity, category, description from inventory
-- // for h2database sorting purpose

INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Rev It Up Hat', 10, 1000, 'Misc', 'A sweet hat to ACCELERATE your development!', .10, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Code Like A Boss T-Shirt', 100, 1000, 'Unisex Shirt', 'Perfect for casual friday!', .15, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Programmer Socks', 1000, 10, 'Misc', 'Scientifically proven to make you a better programmer.', .11, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Revature: Party Game', 175, 95, 'Misc', 'Simple board game to test your tech skills', .15, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Anime Collection Blu-Ray', 50, 300, 'Movies', 'Only old-school anime fans will understand.', .9, true);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Action-Packed Collection DVD', 45, 250, 'Movies', 'Hardcore movies full of nonstop actions and suspenses.', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('SQL T-Shirt', 20, 125, 'Women''s Shirt', 'Embrace the database by wearing THIS t-shirt.', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Will Write Code For Food T-Shirt', 35, 100, 'Men''s Shirt', 'Seriously! Are you that desperate?', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Arrays T-Shirt', 15, 50, 'Men''s Shirt', 'Black T-Shirt with jokes on it.', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('2021 T-Shirt', 21, 100, 'Women''s Shirt', 'Keep calm and wear this shirt', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('I Love Robot T-Shirt', 50, 150, 'Unisex Shirt', 'Are you an AI sympathizer? If so, wear this shirt to show some robot love, baby!', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('I Am Supercomputer T-Shirt', 25, 200, 'Men''s Shirt', 'Have you ever wanted to be Superman, but instead of a man you want to be a computer? Then what are you waiting for? Wear this shirt NOW!', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Pet Owner T-Shirt', 29, 80, 'Unisex Shirt', 'Wear it to brag about your newly-built robodog.', .0, true);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Karate T-Shirt', 50, 120, 'Unisex Shirt', 'Do you have a black-belt, white-belt, or any belt at home? If you do, then buy this shirt.', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Fast Food Android T-Shirt', 15, 50, 'Men''s Shirt', 'See? Not all androids are bloodthirsty killing machines. Some of them can even serve fast food when their master has a craving for a Big Mac.', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Meme T-Shirt', 30, 75, 'Unisex Shirt', 'The Force is strong with this t-shirt.', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Evolution t-Shirt', 35, 180, 'Unisex Shirt', 'Shhh... Don''t worry my child. Soon you will evolve into T-1000 just like the rest of us.', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Moon Landing T-Shirt', 20, 130, 'Unisex Shirt', 'One step for man...', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Super Tech T-Shirt', 40, 100, 'Unisex Shirt', 'Flying cars are real, people! I am telling you.', .0, true);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Super Tech II T-Shirt', 50, 200, 'Unisex Shirt', 'Cyborg rider FTW!!!', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Angry Developer T-Shirt', 35, 70, 'Unisex Shirt', 'It is not working! WHY!!!', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Cute Robot T-Shirt', 25, 300, 'Unisex Shirt', 'Do you have kids? Do your kids like cute robots? If yes, why not do them a favor and wear this t-shirt. You will not regret it.', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('100 Percent Sweatpants', 50, 75, 'Men''s Pants', 'It is 100 percent, top notch, highest quality sweatpants you ever get.', .0, true);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Pants On Fire Sweatpants', 60, 50, 'Men''s Pants', 'Liar, liar, pants on fire! Or so they say...', .0, true);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Eco-Friendly Joggers', 70, 200, 'Women''s Pants', 'For those who are too woke for the environment', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Warm-Up Pants', 90, 200, 'Women''s Pants', 'Its time to burn those calories, baby!', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Programmer Mug', 40, 400, 'Mugs', 'Once you drink from this coffee mug, you will be able to continue coding for 10 straight hours, NONSTOP!', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Sweet Dreams Mug', 100, 20, 'Mugs', 'Legend has it that if you drink beer from this mug, you will get knocked out cold for 7 days. 7 DAYS!', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Get To Da Choppa Lunchbox', 200, 20, 'Misc', 'RUNNNN! GO!!! GET TO DA CHAPPA!!!', .0, false);
INSERT INTO inventory (item_name, item_price, quantity, category, description, discount, is_Featured) VALUES ('Revature REV Watch', 600, 50, 'Misc', 'The authentic REV watch from Revature. Designed to look cool and hip.', .0, false);
