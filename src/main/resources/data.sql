INSERT INTO `testdb`.`transaction`
(`id`,
 `timestamp`,
 `type`,
 `actor`)
VALUES (1, '2021-05-01 15:20:16', 'type1', 'user1'),
       (2, '2022-04-06 10:35:15', 'type1', 'user2'),
       (3, '2022-04-06 11:00:00', 'type2', 'user1');
INSERT INTO `testdb`.`transaction_data`
(`transaction_id`,
 `data_key`,
 `data`)
VALUES (1, 'key', 'data'),
       (1, '2key', '2data'),
       (3, '3key', '3data');