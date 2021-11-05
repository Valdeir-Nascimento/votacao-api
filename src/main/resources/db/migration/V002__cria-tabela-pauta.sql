CREATE TABLE IF NOT EXISTS `votacao_api`.`pauta` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(100) NOT NULL,
    `voto_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_paula_voto1_idx` (`voto_id` ASC) VISIBLE,
    CONSTRAINT `fk_paula_voto1`
    FOREIGN KEY (`voto_id`)
    REFERENCES `votacao_api`.`voto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;