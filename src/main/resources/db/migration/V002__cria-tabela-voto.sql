CREATE TABLE IF NOT EXISTS `votacao_api`.`voto` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `cpf` VARCHAR(11) NOT NULL,
    `escolha` TINYINT NOT NULL,
    `pauta_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_voto_pauta1_idx` (`pauta_id` ASC) VISIBLE,
    CONSTRAINT `fk_voto_pauta1`
        FOREIGN KEY (`pauta_id`)
            REFERENCES `votacao_api`.`pauta` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION)
ENGINE = InnoDB;