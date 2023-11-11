-- Rollen toevoegen
INSERT INTO roles ("role_name") VALUES ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_BREWER');

-- Gebruikers toevoegen
INSERT INTO users (username, password) VALUES ('karlos', 'karlos');
INSERT INTO users (username, password) VALUES ('admin', 'admin');
INSERT INTO users (username, password) VALUES ('brewer', 'brewer');


-- Profiel toevoegen
INSERT INTO profile ("id", "email", "first_name", "last_name", "company")
VALUES (1, 'tonystark@gmail.com', 'Tony', 'Stark', 'stark tech');
DROP TABLE IF EXISTS products;
-- Producten tabel aanmaken
CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          productname VARCHAR(255) NOT NULL,
                          namebrewer VARCHAR(255),
                          productionlocation VARCHAR(255),
                          type VARCHAR(255),
                          alcohol DECIMAL,
                          ibu DECIMAL,
                          color VARCHAR(255),
                          taste VARCHAR(255),
                          volume DECIMAL
);

-- Product toevoegen
INSERT INTO products (
    "id",
    "productname",
    "namebrewer",
    "productionlocation",
    "type",
    "alcohol",
    "ibu",
    "color",
    "taste",
    "volume"
) VALUES (
             1,
             'Hulk',
             'Tony Stark',
             'New York',
             'IPA',
             6.5,
             50,
             'Amber',
             'Fruity',
             33
         );
DROP TABLE IF EXISTS profile;
-- Profiel tabel aanmaken
-- Profiel tabel aanmaken
CREATE TABLE profile (
                         id SERIAL PRIMARY KEY,
                         email VARCHAR(255) NOT NULL,
                         first_name VARCHAR(255),
                         last_name VARCHAR(255),
                         company VARCHAR(255)
);


-- Profielgegevens toevoegen
INSERT INTO profile (
    "id",
    "email",
    "first_name",
    "last_name",
    "company"
) VALUES (
             1,
             'matthijsvandermaas@yahoo.com',
             'matthijs',
             'van der maas',
             'matthijs tech'
         );


-- Bestand toevoegen aan de tabel files



