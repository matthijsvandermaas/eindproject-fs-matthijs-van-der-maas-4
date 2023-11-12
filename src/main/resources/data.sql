-- Rollen toevoegen
INSERT INTO roles ("role_name") VALUES ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_BREWER');


-- Gebruikers toevoegen
INSERT INTO users (id, username, password) VALUES (1000, 'karlos@gmail.com', 'karlos');
INSERT INTO users (id, username, password) VALUES (1001,'admin@b&b.com', 'admin');
INSERT INTO users (id, username, password) VALUES (1002, 'brewer@bier.com', 'brewer');


-- Profiel toevoegen
INSERT INTO profile (id, email, first_name, last_name, company, password, username)
VALUES (1002, 'matthijsvandermaas@test.com', 'matthijs', 'van der maas', 'test company', 'danielle', 'marty');


-- Product toevoegen
INSERT INTO products (
    id,
    productname,
    namebrewer,
    productionlocation,
    type,
    alcohol,
    ibu,
    color,
    taste,
    volume
) VALUES (
             1000,
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










