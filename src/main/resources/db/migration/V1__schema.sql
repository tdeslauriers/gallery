DROP TABLE IF EXISTS album;
DROP TABLE IF EXISTS image;

CREATE TABLE album (
    id INT NOT NULL AUTO_INCREMENT,
    album VARCHAR(32) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE image (
    id INT NOT NULL AUTO_INCREMENT,
    filename VARCHAR(64),
    title VARCHAR(64),
    description VARCHAR(255),
    date DATE,
    published BOOLEAN,
    thumbnail MEDIUMBLOB,
    image MEDIUMBLOB,
    PRIMARY KEY (id)
);

CREATE TABLE album_image (
    id INT NOT NULL AUTO_INCREMENT,
    album_id INT NOT NULL,
    image_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_album_id FOREIGN KEY (album_id) REFERENCES album (id),
    CONSTRAINT fk_image_id FOREIGN KEY (image_id) REFERENCES image (id)
);