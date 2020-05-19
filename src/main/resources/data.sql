INSERT INTO item VALUES (1, 'Java');
INSERT INTO item VALUES (2, 'Spring');
INSERT INTO item VALUES (3, 'Angular');
INSERT INTO item VALUES (4, 'Python');

INSERT INTO user_info(id, name, email, password) VALUES (1, 'Administrator', 'admin@jwt.com', '$2a$10$qwR.WNKW8RyrY0bAwAel1OSd2NtBIRoXJZK1..nKRF02DtqFT4/ZK');
INSERT INTO user_info(id, name, email, password) VALUES (2, 'User', 'user@jwt.com', '$2a$10$7OKxfhJZrHbiJpaAIDcb5eshOlOW7bAMJwPHbXf9Z6gRQcfFc22sy');

INSERT INTO permission VALUES (1, 'LST_ITEM');
INSERT INTO permission VALUES (2, 'ADD_ITEM');
INSERT INTO permission VALUES (3, 'REM_ITEM');

-- admin
INSERT INTO user_permission VALUES (1, 1);
INSERT INTO user_permission VALUES (1, 2);
INSERT INTO user_permission VALUES (1, 3);

-- user
INSERT INTO user_permission VALUES (2, 1);

