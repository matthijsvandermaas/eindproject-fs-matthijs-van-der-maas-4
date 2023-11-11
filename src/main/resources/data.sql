insert into roles("role_name") values ('ROLE_USER'), ('ROLE_ADMIN');

-- INSERT INTO users (username, password)
-- VALUES ('MightyCarlos', 'Taylor');


INSERT INTO profile ( "id", "email", "first_name", "last_name")
VALUES ( 1, 'tonystark@gmail.com', 'Tony', 'Stark');



-- INSERT INTO products ("id", "productname", "namebrewer", "productionlocation", "type", "alcohol", "ibu", "color", "taste", "volume", "file", "file2")
-- VALUES (1, 'Hulk', 'Tony Stark', 'New York', 'IPA', 6.5, 50, 'Amber', 'Fruity', 33, 'C:/Users/marty/OneDrive/Bureaublad/ironman.jpg', 'C:/Users/marty/OneDrive/Bureaublad/ironman.jpg');
-- INSERT INTO products (file) VALUES ('C:/Users/marty/OneDrive/Bureaublad/ironman.jpg');
-- INSERT INTO products (file2) VALUES ('C:/Users/marty/OneDrive/Bureaublad/ironman.jpg');
-- org.postgresql.util.PSQLException: ERROR: invalid input syntax for type oid: "C:/Users/marty/OneDrive/Bureaublad/ironman.jpg"


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
    "volume",
    "file",
    "file2"
)
VALUES (
    1,
    'Hulk',
    'Tony Stark',
    'New York',
    'IPA',
    6.5,
    50,
    'Amber',
    'Fruity',
    33,
    E'\\x' || encode(pg_read_binary_file('C:/Users/marty/OneDrive/Bureaublad/ironman.jpg'), 'hex'),
    E'\\x' || encode(pg_read_binary_file('C:/Users/marty/OneDrive/Bureaublad/ironman.jpg'), 'hex')
);

CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          productname VARCHAR(255) NOT NULL,
                          namebrewer VARCHAR(255),
                          productionlocation VARCHAR(255),
                          type VARCHAR(255),
                          alcohol INTEGER,
                          ibu INTEGER,
                          color VARCHAR(255),
                          taste VARCHAR(255),
                          volume VARCHAR(255),
                          file BYTEA,
                          file2 BYTEA
);