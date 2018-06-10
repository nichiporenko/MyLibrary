CREATE SCHEMA IF NOT EXISTS "public";

DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS countries;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS languages;
DROP TABLE IF EXISTS publishing_houses;

CREATE TABLE IF NOT EXISTS "public".authors (
	id								    bigserial 		PRIMARY KEY,
	first_name				    varchar(50)		NOT NULL,
	last_name	  			    varchar(50)		NOT NULL,
  date_of_birth         timestamp     NOT NULL
);

CREATE TABLE IF NOT EXISTS "public".countries (
	id								    bigserial 		PRIMARY KEY,
	name		      		    varchar(50)		NOT NULL
);

CREATE TABLE IF NOT EXISTS "public".genres (
	id								    bigserial 		PRIMARY KEY,
	name		      		    varchar(50)		NOT NULL
);

CREATE TABLE IF NOT EXISTS "public".languages (
	id								    bigserial 		PRIMARY KEY,
	name		      		    varchar(50)		NOT NULL
);

CREATE TABLE IF NOT EXISTS "public".publishing_houses (
	id								    bigserial 		PRIMARY KEY,
	name		      		    varchar(50)		NOT NULL
);

CREATE TABLE IF NOT EXISTS "public".books (
	id								    bigserial 		PRIMARY KEY,
	name                  varchar(50)   NOT NULL,
  author_id             bigint        NOT NULL,
  country_id            bigint        NOT NULL,
  genre_id              bigint        NOT NULL,
  language_id           bigint        NOT NULL,
  publishing_house_id   bigint        NOT NULL,
  circulation           int           NOT NULL
);

ALTER TABLE "public".books DROP CONSTRAINT IF EXISTS fk_books_authors;
ALTER TABLE "public".books ADD CONSTRAINT fk_books_authors FOREIGN KEY (author_id) REFERENCES "public".authors(id) ON DELETE RESTRICT;
ALTER TABLE "public".books DROP CONSTRAINT IF EXISTS fk_books_countries;
ALTER TABLE "public".books ADD CONSTRAINT fk_books_countries FOREIGN KEY (country_id) REFERENCES "public".countries(id) ON DELETE RESTRICT;
ALTER TABLE "public".books DROP CONSTRAINT IF EXISTS fk_books_genres;
ALTER TABLE "public".books ADD CONSTRAINT fk_books_genres FOREIGN KEY (genre_id) REFERENCES "public".genres(id) ON DELETE RESTRICT;
ALTER TABLE "public".books DROP CONSTRAINT IF EXISTS fk_books_languages;
ALTER TABLE "public".books ADD CONSTRAINT fk_books_languages FOREIGN KEY (language_id) REFERENCES "public".languages(id) ON DELETE RESTRICT;
ALTER TABLE "public".books DROP CONSTRAINT IF EXISTS fk_books_publishing_houses;
ALTER TABLE "public".books ADD CONSTRAINT fk_books_publishing_houses FOREIGN KEY (publishing_house_id) REFERENCES "public".publishing_houses(id) ON DELETE RESTRICT;