CREATE TABLE if not exists category(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    description TEXT
);

CREATE TABLE if not exists product(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    description TEXT,
    price DOUBLE,
    categoryId INTEGER,
    FOREIGN KEY (categoryId) REFERENCES category(id)
);