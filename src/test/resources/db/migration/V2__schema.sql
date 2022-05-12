INSERT INTO image (
   filename,
   date,
   published
) VALUES (
    UNHEX(REPLACE(UUID(), "-", "")),
    "2021-01-01",
    TRUE
), (
    UNHEX(REPLACE(UUID(), "-", "")),
    "2018-01-01",
    TRUE
);

INSERT INTO album ( album) VALUES ("2018"), ("2021"), ("Art");

INSERT INTO album_image (album_id, image_id) VALUES (
        (SELECT id FROM album WHERE album = "2018"),
        (SELECT id FROM image WHERE date = "2018-01-01")
    ),
    (
        (SELECT id FROM album WHERE album = "Art"),
        (SELECT id FROM image WHERE date = "2018-01-01")
    ),
    (
        (SELECT id FROM album WHERE album = "2021"),
        (SELECT id FROM image WHERE date = "2021-01-01")
    );