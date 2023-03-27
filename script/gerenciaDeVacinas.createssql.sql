DROP DATABASE IF EXISTS DBGERVACINAS;
CREATE DATABASE DBGERVACINAS;
USE DBGERVACINAS;

CREATE TABLE TIPOPESSOA (
 VALOR INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 DESCRICAO VARCHAR(50) NOT NULL
);
  
 CREATE TABLE PESSOAS (
  ID_PESSOA INT NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(255) NOT NULL,
  DT_NASCIMENTO DATE NOT NULL,
  SEXO TINYINT NOT NULL,
  CPF VARCHAR(11) NOT NULL,
  VALOR INT NOT NULL,
  PRIMARY KEY (ID_PESSOA),
  FOREIGN KEY (VALOR) REFERENCES TIPOPESSOA (VALOR),
  UNIQUE INDEX ID_PESSOA_UNIQUE (ID_PESSOA ASC) VISIBLE);
  
  CREATE TABLE ESTAGIOPESQUISA(
 VALOR INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 DESCRICAO VARCHAR(50) NOT NULL
);
  
  CREATE TABLE VACINAS (
  ID_VACINA INT NOT NULL AUTO_INCREMENT,
  PAIS_ORIGEM VARCHAR(255) NOT NULL,
  VALOR INT NOT NULL,
  DT_INICIO DATE NOT NULL,
  ID_PESSOA INT,
  PRIMARY KEY (ID_VACINA),
  FOREIGN KEY (ID_PESSOA) REFERENCES PESSOAS (ID_PESSOA),
  FOREIGN KEY (VALOR) REFERENCES ESTAGIOPESQUISA (VALOR),
  UNIQUE INDEX ID_VACINA_UNIQUE (ID_VACINA ASC) VISIBLE);