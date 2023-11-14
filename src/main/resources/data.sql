-- Rollen toevoegen
INSERT INTO roles ("role_name") VALUES ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_BREWER');


-- Gebruikers toevoegen
INSERT INTO users (id, username, password) VALUES (1000, 'matthijsvandermaas@test.com', 'danielle');


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










