CREATE SCHEMA `aprendendojsp` DEFAULT CHARACTER SET utf8 ;


    
CREATE TABLE `aprendendojsp`.`cliente_pessoa_fisica` (
  `id` INT NOT NULL auto_increment,
  `nome` VARCHAR(500) NULL,
  `cpf` VARCHAR(20) NULL,
  `endereco` VARCHAR(500) NULL,
  `datanacimento` DATE NULL,
  `numerologradouro` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;  


CREATE TABLE `aprendendojsp`.`telefone_cliente` (
  `id` INT NOT NULL auto_increment,
  `tipotelefone` VARCHAR(45) NOT NULL,
  `numero` VARCHAR(45) NULL,
  `clientepessoafisica` INT not NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

ALTER TABLE `aprendendojsp`.`cliente_pessoa_fisica` 
ADD COLUMN `foto` LONGTEXT NULL AFTER `numerologradouro`;

ALTER TABLE `aprendendojsp`.`cliente_pessoa_fisica` ADD COLUMN `sexo` character varying(10);
ALTER TABLE `aprendendojsp`.`cliente_pessoa_fisica` ADD COLUMN `ativo` boolean;


