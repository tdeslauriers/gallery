INSERT INTO image (
   filename,
   date,
   published
) VALUES (
    "e2256481-4e97-42d5-89c0-78a3c49e760d",
    "2021-01-01",
    TRUE
), (
    "64abaa67-85c1-430f-9780-a76bdaeacff2",
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