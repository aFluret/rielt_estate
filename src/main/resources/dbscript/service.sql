use rielt_estate;
DROP TABLE IF EXISTS `service`;

CREATE TABLE `service` (
                         `idservice` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Синтетический ключ, который играет роль уникального идентификатора каждой услуги',
                         `eng_name` varchar(70) NOT NULL COMMENT 'Наименование услуги(помещение)',
                         `cost` double unsigned NOT NULL COMMENT 'Стоимость услуги',
                         `type` varchar(20) NOT NULL COMMENT '.',
                         `image_path` varchar(45) NOT NULL,
                         `rus_name` varchar(70) NOT NULL COMMENT 'Наименование услуги',
                         `exist` tinyint(4) NOT NULL DEFAULT '1',
                         PRIMARY KEY (`idservice`)
) ENGINE=InnoDB AUTO_INCREMENT=255 DEFAULT CHARSET=utf8 COMMENT='Таблица предназначена для хранения информации об услугах, которые предоставляются в компании';

LOCK TABLES `service` WRITE;

UNLOCK TABLES;
