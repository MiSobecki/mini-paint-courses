-- Those are example login and password values, could be changed.

INSERT INTO users VALUES ('9ded2b4e-90e9-11ed-a1eb-0242ac120002', 'Admin', '$2a$10$NUnTRu8tWJ8gQK7MPiMBv.31YTHInwuMrc2YmwiAqmETkUBb52Vie');
INSERT INTO users_roles VALUES ('9ded2b4e-90e9-11ed-a1eb-0242ac120002', SELECT (id) FROM role WHERE name = 'USER');
INSERT INTO users_roles VALUES ('9ded2b4e-90e9-11ed-a1eb-0242ac120002', SELECT (id) FROM role WHERE name = 'ADMIN');