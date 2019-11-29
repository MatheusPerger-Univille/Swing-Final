CREATE DATABASE bancomatheus;
USE bancomatheus;

CREATE TABLE livros (
  id INT NOT NULL,
  nome VARCHAR(45) NULL,
  categoria VARCHAR(45) NULL,
  autor VARCHAR(45) NULL,
  PRIMARY KEY (id));