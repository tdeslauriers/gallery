DROP TABLE IF EXISTS album;
DROP TABLE IF EXISTS image;

CREATE TABLE album (
    id INT NOT NULL AUTO_INCREMENT,
    album VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE image (
    id INT NOT NULL AUTO_INCREMENT,
    filename BINARY(16) NOT NULL,
    title VARCHAR(255),
    description VARCHAR(255),
    date DATE,
    published BOOLEAN,
    album_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (album_id) REFERENCES album (id)
);