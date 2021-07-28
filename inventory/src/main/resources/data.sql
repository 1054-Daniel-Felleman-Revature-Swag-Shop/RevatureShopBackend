-- CREATE TABLE inventory(
--     id serial primary key,
--     item_name varchar(255),
--     item_price int,
--     quantity int,
--     category varchar,
--     description varchar
-- );
-- select id, item_name, item_price, quantity, category, description from inventory
-- // for h2database sorting purpose
-- line 42-58 medium, 59-76 large shirts

INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Rev It Up Hat', 10, 700, 'Misc', 'A sweet hat to ACCELERATE your development!', null, .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Code Like A Boss T-Shirt', 100, 1000, 'Unisex Shirt', 'Perfect for casual friday!', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Programmer Socks', 1000, 10, 'Misc', 'Scientifically proven to make you a better programmer.', null, .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Party Game', 175, 95, 'Misc', 'Simple board game to test your tech skills', null, .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Anime Collection Blu-Ray', 50, 300, 'Movies', 'Only old-school anime fans will understand.', null, .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Action-Packed Collection DVD', 45, 250, 'Movies', 'Hardcore movies full of nonstop actions and suspenses.', null, .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('SQL T-Shirt', 20, 125, 'Women''s Shirt', 'Embrace the database by wearing THIS t-shirt.', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Will Write Code For Food T-Shirt', 35, 100, 'Men''s Shirt', 'Seriously! Are you that desperate?', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Arrays T-Shirt', 15, 50, 'Men''s Shirt', 'Black T-Shirt with jokes on it.', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('2021 T-Shirt', 21, 100, 'Women''s Shirt', 'Keep calm and wear this shirt', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('I Love Robot T-Shirt', 50, 150, 'Unisex Shirt', 'Are you an AI sympathizer? If so, wear this shirt to show some robot love, baby!', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('I Am Supercomputer T-Shirt', 25, 200, 'Men''s Shirt', 'Have you ever wanted to be Superman, but instead of a man you want to be a computer? Then what are you waiting for? Wear this shirt NOW!', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Pet Owner T-Shirt', 29, 80, 'Unisex Shirt', 'Wear it to brag about your newly-built robodog.', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Karate T-Shirt', 50, 120, 'Unisex Shirt', 'Do you have a black-belt, white-belt, or any belt at home? If you do, then buy this shirt.', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Fast Food Android T-Shirt', 15, 50, 'Men''s Shirt', 'See? Not all androids are bloodthirsty killing machines. Some of them can even serve fast food when their master has a craving for a Big Mac.', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Meme T-Shirt', 30, 75, 'Unisex Shirt', 'The Force is strong with this t-shirt.', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Evolution T-Shirt', 35, 180, 'Unisex Shirt', 'Shhh... Don''t worry my child. Soon you will evolve into T-1000 just like the rest of us.', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Moon Landing T-Shirt', 20, 130, 'Unisex Shirt', 'One step for man...', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Super Tech T-Shirt', 40, 100, 'Unisex Shirt', 'Flying cars are real, people! I am telling you.', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Super Tech II T-Shirt', 50, 200, 'Unisex Shirt', 'Cyborg rider FTW!!!', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Angry Developer T-Shirt', 35, 70, 'Unisex Shirt', 'It is not working! WHY!!!', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Cute Robot T-Shirt', 25, 300, 'Unisex Shirt', 'Do you have kids? Do your kids like cute robots? If yes, why not do them a favor and wear this t-shirt. You will not regret it.', 'Small', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('100 Percent Sweatpants', 50, 75, 'Men''s Pants', 'It is 100 percent, top notch, highest quality sweatpants you ever get.', null, .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Pants On Fire Sweatpants', 60, 50, 'Men''s Pants', 'Liar, liar, pants on fire! Or so they say...', null, .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Eco-Friendly Joggers', 70, 200, 'Women''s Pants', 'For those who are too woke for the environment', null, .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Warm-Up Pants', 90, 200, 'Women''s Pants', 'Its time to burn those calories, baby!', null, .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Programmer Mug', 40, 400, 'Mugs', 'Once you drink from this coffee mug, you will be able to continue coding for 10 straight hours, NONSTOP!', null, .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Sweet Dreams Mug', 100, 20, 'Mugs', 'Legend has it that if you drink beer from this mug, you will get knocked out cold for 7 days. 7 DAYS!', null, .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Get To Da Choppa Lunchbox', 200, 20, 'Misc', 'RUNNNN! GO!!! GET TO DA CHAPPA!!!', null, .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Revature REV Watch', 600, 50, 'Misc', 'The authentic REV watch from Revature. Designed to look cool and hip.', null, .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Code Like A Boss T-Shirt', 100, 800, 'Unisex Shirt', 'Perfect for casual friday!', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('SQL T-Shirt', 20, 125, 'Women''s Shirt', 'Embrace the database by wearing THIS t-shirt.', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Will Write Code For Food T-Shirt', 35, 100, 'Men''s Shirt', 'Seriously! Are you that desperate?', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Arrays T-Shirt', 15, 50, 'Men''s Shirt', 'Black T-Shirt with jokes on it.', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('2021 T-Shirt', 21, 100, 'Women''s Shirt', 'Keep calm and wear this shirt', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('I Love Robot T-Shirt', 50, 150, 'Unisex Shirt', 'Are you an AI sympathizer? If so, wear this shirt to show some robot love, baby!', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('I Am Supercomputer T-Shirt', 25, 200, 'Men''s Shirt', 'Have you ever wanted to be Superman, but instead of a man you want to be a computer? Then what are you waiting for? Wear this shirt NOW!', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Pet Owner T-Shirt', 29, 80, 'Unisex Shirt', 'Wear it to brag about your newly-built robodog.', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Karate T-Shirt', 50, 120, 'Unisex Shirt', 'Do you have a black-belt, white-belt, or any belt at home? If you do, then buy this shirt.', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Fast Food Android T-Shirt', 15, 50, 'Men''s Shirt', 'See? Not all androids are bloodthirsty killing machines. Some of them can even serve fast food when their master has a craving for a Big Mac.', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Meme T-Shirt', 30, 75, 'Unisex Shirt', 'The Force is strong with this t-shirt.', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Evolution T-Shirt', 35, 180, 'Unisex Shirt', 'Shhh... Don''t worry my child. Soon you will evolve into T-1000 just like the rest of us.', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Moon Landing T-Shirt', 20, 130, 'Unisex Shirt', 'One step for man...', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Super Tech T-Shirt', 40, 100, 'Unisex Shirt', 'Flying cars are real, people! I am telling you.', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Super Tech II T-Shirt', 50, 200, 'Unisex Shirt', 'Cyborg rider FTW!!!', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Angry Developer T-Shirt', 35, 70, 'Unisex Shirt', 'It is not working! WHY!!!', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Cute Robot T-Shirt', 25, 300, 'Unisex Shirt', 'Do you have kids? Do your kids like cute robots? If yes, why not do them a favor and wear this t-shirt. You will not regret it.', 'Medium', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Code Like A Boss T-Shirt', 100, 400, 'Unisex Shirt', 'Perfect for casual friday!', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('SQL T-Shirt', 20, 125, 'Women''s Shirt', 'Embrace the database by wearing THIS t-shirt.', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Will Write Code For Food T-Shirt', 35, 100, 'Men''s Shirt', 'Seriously! Are you that desperate?', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Arrays T-Shirt', 15, 50, 'Men''s Shirt', 'Black T-Shirt with jokes on it.', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('2021 T-Shirt', 21, 100, 'Women''s Shirt', 'Keep calm and wear this shirt', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('I Love Robot T-Shirt', 50, 150, 'Unisex Shirt', 'Are you an AI sympathizer? If so, wear this shirt to show some robot love, baby!', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('I Am Supercomputer T-Shirt', 25, 200, 'Men''s Shirt', 'Have you ever wanted to be Superman, but instead of a man you want to be a computer? Then what are you waiting for? Wear this shirt NOW!', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Pet Owner T-Shirt', 29, 80, 'Unisex Shirt', 'Wear it to brag about your newly-built robodog.', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Karate T-Shirt', 50, 120, 'Unisex Shirt', 'Do you have a black-belt, white-belt, or any belt at home? If you do, then buy this shirt.', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Fast Food Android T-Shirt', 15, 50, 'Men''s Shirt', 'See? Not all androids are bloodthirsty killing machines. Some of them can even serve fast food when their master has a craving for a Big Mac.', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Meme T-Shirt', 30, 75, 'Unisex Shirt', 'The Force is strong with this t-shirt.', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Evolution T-Shirt', 35, 180, 'Unisex Shirt', 'Shhh... Don''t worry my child. Soon you will evolve into T-1000 just like the rest of us.', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Moon Landing T-Shirt', 20, 130, 'Unisex Shirt', 'One step for man...', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Super Tech T-Shirt', 40, 100, 'Unisex Shirt', 'Flying cars are real, people! I am telling you.', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Super Tech II T-Shirt', 50, 200, 'Unisex Shirt', 'Cyborg rider FTW!!!', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Angry Developer T-Shirt', 35, 70, 'Unisex Shirt', 'It is not working! WHY!!!', 'Large', .0);
INSERT INTO inventory (item_name, item_price, quantity, category, description, size, discount) VALUES ('Cute Robot T-Shirt', 25, 300, 'Unisex Shirt', 'Do you have kids? Do your kids like cute robots? If yes, why not do them a favor and wear this t-shirt. You will not regret it.', 'Large', .0);
