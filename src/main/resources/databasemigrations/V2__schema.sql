INSERT INTO image (
   filename,
   date,
   published,
   album_id
) VALUES (
    UNHEX(REPLACE(UUID(), "-", "")),
    "2021-01-01",
    FALSE,
    3
), (
        UNHEX(REPLACE(UUID(), "-", "")),
        "2018-01-01",
        FALSE,
        4
);



