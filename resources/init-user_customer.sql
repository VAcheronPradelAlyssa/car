-- Ajout des utilisateurs classiques
INSERT INTO user (username, password, first_name, last_name, email, status) VALUES
('alice', 'password', 'Alice', 'Dupont', 'alice@example.com', 'ACTIVE'),
('bob', 'password', 'Bob', 'Martin', 'bob@example.com', 'ACTIVE');

-- Ajout des agents du service client
INSERT INTO customer_service (username, password, first_name, last_name, email, status) VALUES
('carole', 'password', 'Carole', 'Durand', 'carole@example.com', 'ACTIVE'),
('david', 'password', 'David', 'Leroy', 'david@example.com', 'ACTIVE'),
('emma', 'password', 'Emma', 'Bernard', 'emma@example.com', 'BUSY'),
('lucas', 'password', 'Lucas', 'Petit', 'lucas@example.com', 'OFFLINE');