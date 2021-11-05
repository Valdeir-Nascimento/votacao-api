CREATE TABLE IF NOT EXISTS `votacao_api`.`voto` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `cpf` VARCHAR(11) NOT NULL,
    `escolha` TINYINT NOT NULL,
    PRIMARY KEY (`id`))
ENGINE = InnoDB;