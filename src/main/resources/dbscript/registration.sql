
use rielt_estate;
DROP TABLE IF EXISTS `registration`;
CREATE TABLE `registration` (
                              `idregistration` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Синтетический ключ, который играет роль уникального идентификатора каждого заказа',
                              `total_cost` double NOT NULL DEFAULT '0' COMMENT 'Итоговая стоимость заказа с учетом всех бонусов клиента и подсчета стоимости всех входящих в заказ аренд',
                              `date` date NOT NULL COMMENT 'Указывающаяся клиентом дата',
                              `client_user_iduser` int(10) unsigned NOT NULL,
                              `status` tinyint(4) NOT NULL DEFAULT '0',
                              PRIMARY KEY (`idregistration`),
                              UNIQUE KEY `idregistration_UNIQUE` (`idregistration`),
                              KEY `fk_registration_client1_idx` (`client_user_iduser`),
                              CONSTRAINT `fk_registration_client1` FOREIGN KEY (`client_user_iduser`) REFERENCES `client` (`user_iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COMMENT='Таблица предназначена для хранения информации о клиентских заказах';


