DROP DATABASE IF EXISTS DBGERVACINAS;
CREATE DATABASE DBGERVACINAS;
USE DBGERVACINAS;

CREATE TABLE VACINAS (
  `ID_VACINA` INT NOT NULL AUTO_INCREMENT,
  `PAIS_ORIGEM` VARCHAR(255) NOT NULL,
  `ESTAGIO` INT NOT NULL,
  `DT_INICIO` DATE NOT NULL,
  `NOME_PESQUISADOR` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID_VACINA`),
  UNIQUE INDEX `ID_VACINA_UNIQUE` (`ID_VACINA` ASC) VISIBLE);
  
 CREATE TABLE PESSOAS (
  `ID_PESSOA` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(255) NOT NULL,
  `DT_NASCIMENTO` DATE NOT NULL,
  `SEXO` TINYINT NOT NULL,
  `CPF` VARCHAR(11) NOT NULL,
  `TIPO` INT NOT NULL,
  PRIMARY KEY (`ID_PESSOA`),
  UNIQUE INDEX `ID_PESSOA_UNIQUE` (`ID_PESSOA` ASC) VISIBLE);