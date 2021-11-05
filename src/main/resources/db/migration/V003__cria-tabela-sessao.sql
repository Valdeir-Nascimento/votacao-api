CREATE TABLE IF NOT EXISTS `votacao_api`.`sessao` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `data_inicio` DATETIME NOT NULL,
    `minutos` INT NOT NULL,
    `paula_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_sessao_paula_idx` (`paula_id` ASC) VISIBLE,
    CONSTRAINT `fk_sessao_paula`
    FOREIGN KEY (`paula_id`)
    REFERENCES `votacao_api`.`pauta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;