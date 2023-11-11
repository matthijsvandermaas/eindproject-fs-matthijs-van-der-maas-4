INSERT INTO users("email", "password", "rol") VALUES ('matthijs@example.com', '$2a$12$PTyobwyO.mi1qgSLoHCIIOlcppr3vE9quXQy1pD0Aw1zHMJf3Yopy', 'ROLE_USER');



CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,

);
