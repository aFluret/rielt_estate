
use rielt_estate;
DROP TABLE IF EXISTS `registration_service`;

CREATE TABLE `registration_service`
(
    `registration_idregistration` int(10) unsigned NOT NULL COMMENT 'Внешний ключ,по которому заказ услуг связывается с перечнем предлагаемых услуг',
    `service_idservice`          int(10) unsigned NOT NULL COMMENT 'Внешний ключ,  по которому предлагаемые услуги связываются с заказом,в который они добавлены',
    `quantity`                    int(10) unsigned DEFAULT '1',
    PRIMARY KEY (`registration_idregistration`, `service_idservice`),
    KEY `fk_registration_has_service_service1_idx` (`service_idservice`),
    KEY `fk_registration_has_service_registration1_idx` (`registration_idregistration`),
    CONSTRAINT `fk_registration_has_service_registration1` FOREIGN KEY (`registration_idregistration`) REFERENCES `registration` (`idregistration`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_registration_has_service_service1` FOREIGN KEY (`service_idservice`) REFERENCES `service` (`idservice`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1 COMMENT ='Таблица предназначена для хранения информации о перечне аренд из списка, которые были добавлены в конкретный заказ';


LOCK TABLES `registration_service` WRITE;


UNLOCK TABLES;
