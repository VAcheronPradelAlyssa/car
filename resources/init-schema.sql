
CREATE DATABASE IF NOT EXISTS car;

USE car;

CREATE TABLE user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  email VARCHAR(100),
  status VARCHAR(20)
);

CREATE TABLE customer_service (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  email VARCHAR(100),
  status VARCHAR(20)
);

CREATE TABLE chatsession (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  client_id BIGINT,
  advisor_id BIGINT,
  started_at DATETIME,
  FOREIGN KEY (client_id) REFERENCES user(id),
  FOREIGN KEY (advisor_id) REFERENCES customer_service(id)
);


CREATE TABLE message (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  chat_session_id BIGINT,
  sender_user_id BIGINT,
  sender_customer_service_id BIGINT,
  receiver_user_id BIGINT,
  receiver_customer_service_id BIGINT,
  content TEXT,
  timestamp DATETIME,
  FOREIGN KEY (chat_session_id) REFERENCES chatsession(id),
  FOREIGN KEY (sender_user_id) REFERENCES user(id),
  FOREIGN KEY (sender_customer_service_id) REFERENCES customer_service(id),
  FOREIGN KEY (receiver_user_id) REFERENCES user(id),
  FOREIGN KEY (receiver_customer_service_id) REFERENCES customer_service(id)
);

CREATE TABLE appointment (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  client_id BIGINT,
  advisor_id BIGINT,
  date DATETIME,
  status VARCHAR(20),
  FOREIGN KEY (client_id) REFERENCES user(id),
  FOREIGN KEY (advisor_id) REFERENCES customer_service(id)
);

CREATE TABLE agency (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  address VARCHAR(200)
);

CREATE TABLE category (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50)
);

CREATE TABLE car (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  agency_id BIGINT,
  category_id BIGINT,
  brand VARCHAR(50),
  model VARCHAR(50),
  year INT,
  plate VARCHAR(20),
  FOREIGN KEY (agency_id) REFERENCES agency(id),
  FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE offer (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  car_id BIGINT,
  title VARCHAR(100),
  description TEXT,
  price DECIMAL(10,2),
  FOREIGN KEY (car_id) REFERENCES car(id)
);

CREATE TABLE rental (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  client_id BIGINT,
  car_id BIGINT,
  offer_id BIGINT,
  appointment_id BIGINT,
  start_date DATETIME,
  end_date DATETIME,
  total_price DECIMAL(10,2),
  FOREIGN KEY (client_id) REFERENCES user(id),
  FOREIGN KEY (car_id) REFERENCES car(id),
  FOREIGN KEY (offer_id) REFERENCES offer(id),
  FOREIGN KEY (appointment_id) REFERENCES appointment(id)
);


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
