INSERT INTO accounts(email, name, role, points, email_subscription) VALUES ('gerald.stillman@revature.net', 'Gerald', 1, 390, true), ('emmett.riddle@revature.net', 'Emmett', 0, 500, false), ('bennett.kerbow@revature.net', 'Bennett', 0, 200, false);
INSERT INTO point_history(fk_account, cause, change, date) VALUES ( 2, 'Brownie Bit Points', 256, '2021-05-28' );
INSERT INTO point_history(fk_account, cause, change, date) VALUES ( 2, 'More Brownie Points', 244, '2021-05-28' );
INSERT INTO point_history(fk_account, cause, change, date) VALUES ( 1, 'Excellent Work Week', 100, '2021-06-01' );
INSERT INTO point_history(fk_account, cause, change, date) VALUES ( 1, 'Purchased 3 item(s) from the shop: [I Love Robot T-Shirt, RevCoins:50, Anime Collection Blu-Ray, RevCoins:50, Rev It Up Hat, RevCoins:10]', -110, '2021-05-30' );
INSERT INTO point_history(fk_account, cause, change, date) VALUES ( 1, 'Admin starting points', 400, '2021-05-28' );
INSERT INTO point_history(fk_account, cause, change, date) VALUES ( 3, 'Project Lead Points', 100, '2021-05-28' );
INSERT INTO point_history(fk_account, cause, change, date) VALUES ( 3, 'Brownie Points', 50, '2021-05-28' );
INSERT INTO point_history(fk_account, cause, change, date) VALUES ( 3, 'Hacker Rank Champion', 50, '2021-05-28' );