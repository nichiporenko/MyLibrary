INSERT INTO "public".authors (first_name, last_name, date_of_birth) VALUES
('Joshua', 'Bloch', '1961-08-26'),
('Bruce', 'Eckel', '1957-06-8'),
('Steven', 'McConnell', '1962-09-3');

INSERT INTO "public".countries (name) VALUES ('China'), ('USA'), ('Australia'), ('Russia');

INSERT INTO "public".genres (name) VALUES ('Technical literature'), ('Fantasy'), ('Drama'), ('Novel'), ('Tale');

INSERT INTO "public".languages (name) VALUES ('English');

INSERT INTO "public".publishing_houses (name) VALUES ('Publishing house #1'), ('Publishing house #2'), ('Publishing house #3');

INSERT INTO "public".books (name, author_id, country_id, genre_id, language_id, publishing_house_id, circulation) VALUES
('Java Concurrency in Practice', 1, 2, 1, 1, 1, 650000),
('Thinking in Java, 4th Edition', 1, 2, 1, 1, 1, 580000),
('Code Complete', 1, 2, 1, 1, 1, 800000);