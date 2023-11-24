-- Rollen toevoegen
INSERT INTO roles (rolename) VALUES ('ROLE_USER');
INSERT INTO roles (rolename) VALUES ('ROLE_ADMIN');
INSERT INTO roles (rolename) VALUES ('ROLE_BREWER');

-- Product toevoegen
CREATE TABLE IF NOT EXISTS products (
        id SERIAL PRIMARY KEY,
        productname VARCHAR(255),
    namebrewer VARCHAR(255),
    productionlocation VARCHAR(255),
    type VARCHAR(255),
    alcohol DOUBLE PRECISION,
    ibu DOUBLE PRECISION,
    color VARCHAR(255),
    taste VARCHAR(255),
    volume DOUBLE PRECISION
    );
INSERT INTO products (productname, namebrewer, productionlocation, type, alcohol, ibu, color, tast, volume)
VALUES ('Hulk', 'Tony Stark', 'New York', 'IPA', 6.5, 50, 'Amber', 'Fruity', 33);

-- User toevoegen
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    company VARCHAR(255),
    email VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255)
    );
INSERT INTO users (company, email, firstname, lastname, username, password)
VALUES ('BenB', 'matthijsvandermaas@BenB.com', 'matthijs', 'van der maas', 'admin matthijs', '$2a$12$XuDwsr1Ri/IqGGAHFus4XOhZk4RDnPRgUjRfXBX2B2osGD12uy/y.');-- danielle
