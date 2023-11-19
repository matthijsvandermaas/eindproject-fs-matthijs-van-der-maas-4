-- Rollen toevoegen
INSERT INTO roles (role_name) VALUES ('ROLE_USER');
INSERT INTO roles (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (role_name) VALUES ('ROLE_BREWER');

-- Product-tabel aanmaken
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



-- Product toevoegen
INSERT INTO products (productname, namebrewer, productionlocation, type, alcohol, ibu, color, taste, volume)
VALUES ( 'Hulk', 'Tony Stark', 'New York', 'IPA', 6.5, 50, 'Amber', 'Fruity', 33);

-- User toevoegen
INSERT INTO users (id, company, email, first_name, last_name, username, password)
VALUES (3000, 'BenB', 'matthijsvandermaas@test.com', 'matthijs', 'van der maas', 'admin', 'danielle');
