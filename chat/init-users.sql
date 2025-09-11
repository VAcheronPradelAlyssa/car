INSERT INTO role (id, name) VALUES (1, 'USER'), (2, 'ADVISOR');

INSERT INTO user (id, username, password, role_id, first_name, last_name, email, status) VALUES
(1, 'alice', 'password', 1, 'Alice', 'Dupont', 'alice@example.com', 'ACTIVE'),
(2, 'bob', 'password', 1, 'Bob', 'Martin', 'bob@example.com', 'ACTIVE'),
(3, 'carole', 'password', 2, 'Carole', 'Durand', 'carole@example.com', 'ACTIVE'),
(4, 'david', 'password', 2, 'David', 'Leroy', 'david@example.com', 'ACTIVE'),
(5, 'emma', 'password', 2, 'Emma', 'Bernard', 'emma@example.com', 'BUSY'),
(6, 'lucas', 'password', 2, 'Lucas', 'Petit', 'lucas@example.com', 'OFFLINE');
