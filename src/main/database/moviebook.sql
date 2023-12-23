CREATE DATABASE moviebook CHARACTER SET utf8 COLLATE utf8_general_ci;

USE moviebook;

CREATE TABLE movies (
  id INTEGER NOT NULL AUTO_INCREMENT,
  title VARCHAR(80) NOT NULL,
  genre VARCHAR(60) NOT NULL,
  year SMALLINT NOT NULL,
  main_actor VARCHAR(80) NOT NULL,
  watched_when DATE NOT NULL,
  watched_where VARCHAR(20) NOT NULL,
  synopse TEXT NULL,
  PRIMARY KEY (id)
);
