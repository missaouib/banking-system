
INSERT INTO banking_system.user (id, password, username) VALUES (1, '$2a$10$X/8aUoZmebVt4oRH2iu9AOGbnucbxdQdx2NEspuDO/GPInmGJFQkO', 'nerea');
INSERT INTO banking_system.user (id, password, username) VALUES (2, '$2a$10$63wmrtJ9LYXeuH7BveropuB6dCU0fDNkxOymzewUxzQ2hMf5PZDGK', 'sergio');
INSERT INTO banking_system.user (id, password, username) VALUES (3, '$2a$10$gfon4yKUFd.RxYw5tjVUmumB3PQ8qFA4JJXGBk5CLQg/V6G/mwwb6', 'irene');
INSERT INTO banking_system.user (id, password, username) VALUES (4, '$2a$10$4kc4YOd58CnIP543Bk.n5e7YcEHhBLuc6BLoFpOu6lHE8klUO9KCi', 'miguel');
INSERT INTO banking_system.user (id, password, username) VALUES (5, '$2a$10$c3JynP8wJOwRGxKH8BtCy.MLvSikIzlSoYl5mgJj7t2o.yI7L4O0G', 'maria');
INSERT INTO banking_system.user (id, password, username) VALUES (6, '$2a$10$8KZ55SxqbT3I/8r9MfnBKeDikz/WkuSIf.cbReeksHu4YlTGs.DEa', 'ramiro');
INSERT INTO banking_system.user (id, password, username) VALUES (7, '$2a$10$0iMdUIEa0zsFlI9alb56ju2MnYJnhh1cQsXNkHKihy6ldvkJ9B/ZW', 'juanjo');

INSERT INTO banking_system.third_party (hashed_key, id) VALUES ('guvbhjbnjkbn45454645', 2);
INSERT INTO banking_system.third_party (hashed_key, id) VALUES ('lmkljmkl123456', 3);
INSERT INTO banking_system.third_party (hashed_key, id) VALUES ('gsdufavgsduv', 6);
INSERT INTO banking_system.third_party (hashed_key, id) VALUES ('gsdufavgsduv', 7);

INSERT INTO banking_system.role (id, name, user_id) VALUES (1, 'ADMIN', 1);
INSERT INTO banking_system.role (id, name, user_id) VALUES (2, 'ACCOUNTHOLDER', 2);
INSERT INTO banking_system.role (id, name, user_id) VALUES (3, 'THIRDPARTY', 2);
INSERT INTO banking_system.role (id, name, user_id) VALUES (4, 'THIRDPARTY', 3);
INSERT INTO banking_system.role (id, name, user_id) VALUES (5, 'ACCOUNTHOLDER', 4);
INSERT INTO banking_system.role (id, name, user_id) VALUES (6, 'ADMIN', 5);
INSERT INTO banking_system.role (id, name, user_id) VALUES (7, 'THIRDPARTY', 7);


INSERT INTO banking_system.account_holder (city, country, postal_code, street, date_of_birth, mailing_city, mailing_country, mailing_postal_code, mailing_street, id) VALUES ('madrid', 'españa', '28053', 'lele', '2000-04-23', 'madrid', 'españa', '28053', 'lele', 2);
INSERT INTO banking_system.account_holder (city, country, postal_code, street, date_of_birth, mailing_city, mailing_country, mailing_postal_code, mailing_street, id) VALUES ('barcelona', 'españa', '28017', 'diagonal', '1993-11-30', 'barcelona', 'españa', '28017', 'diagonal', 4);

INSERT INTO banking_system.admin (id) VALUES (1);
INSERT INTO banking_system.admin (id) VALUES (5);

INSERT INTO banking_system.account (id, amount, currency, creation_date, penalty_fee, status, primary_owner_id, secondary_owner_id) VALUES (1, 500.00, 'USD', '2021-02-10', 40.00, 'ACTIVE', 2, null);
INSERT INTO banking_system.account (id, amount, currency, creation_date, penalty_fee, status, primary_owner_id, secondary_owner_id) VALUES (2, 1000.00, 'USD', '2021-02-11', 40.00, 'ACTIVE', 2, null);
INSERT INTO banking_system.account (id, amount, currency, creation_date, penalty_fee, status, primary_owner_id, secondary_owner_id) VALUES (3, 5000.00, 'USD', '2021-02-11', 40.00, 'ACTIVE', 2, null);
INSERT INTO banking_system.account (id, amount, currency, creation_date, penalty_fee, status, primary_owner_id, secondary_owner_id) VALUES (4, 4000.00, 'JPY', '2021-02-11', 40.00, 'ACTIVE', 2, null);
INSERT INTO banking_system.account (id, amount, currency, creation_date, penalty_fee, status, primary_owner_id, secondary_owner_id) VALUES (5, 4000.00, 'JPY', '2021-02-11', 40.00, 'ACTIVE', 4, null);
INSERT INTO banking_system.account (id, amount, currency, creation_date, penalty_fee, status, primary_owner_id, secondary_owner_id) VALUES (6, 5000.00, 'EUR', '2021-02-11', 40.00, 'ACTIVE', 2, 4);
INSERT INTO banking_system.account (id, amount, currency, creation_date, penalty_fee, status, primary_owner_id, secondary_owner_id) VALUES (7, 100.00, 'EUR', '2021-02-14', 40.00, 'ACTIVE', 2, null);

INSERT INTO banking_system.checking (minimum_balance, monthly_maintenance_fee, secret_key, id) VALUES (250.00, 12.00, 'hola', 5);

INSERT INTO banking_system.credit_card (credit_limit, interest_rate, paid_interest_rate, id) VALUES (5000.00, 0.2000, '2021-02-11', 6);
INSERT INTO banking_system.credit_card (credit_limit, interest_rate, paid_interest_rate, id) VALUES (150.00, 0.1500, '2021-02-14', 7);

INSERT INTO banking_system.savings (interest_rate, minimum_balance, paid_interest_rate, secret_key, id) VALUES (0.0025, 1000.00, '2021-02-10', 'hola', 1);
INSERT INTO banking_system.savings (interest_rate, minimum_balance, paid_interest_rate, secret_key, id) VALUES (0.0025, 1000.00, '2021-02-11', 'hola', 2);
INSERT INTO banking_system.savings (interest_rate, minimum_balance, paid_interest_rate, secret_key, id) VALUES (0.0025, 1000.00, '2021-02-11', 'hola', 3);

INSERT INTO banking_system.student_checking (secret_key, id) VALUES ('hola', 4);
