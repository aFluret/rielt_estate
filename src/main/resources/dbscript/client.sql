
use rielt_estate;
CREATE TABLE `client` (
                          `name` varchar(20) NOT NULL COMMENT 'Имя клиента',
                          `surname` varchar(20) NOT NULL COMMENT 'Фамилия клиента',
                          `email` varchar(30) NOT NULL COMMENT 'Адрес электронной почты клиента, который необходим для рассылки уведомлений системы, либо для восстановления пароля',
                          `point` decimal(3,1) DEFAULT NULL COMMENT 'Баллы лояльности, которые начисляются клиенту автоматически в случае предварительного совершения заказа, либо снимаются в случае, если клиент не забирает готовый заказ',
                          `ban` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Статус клиента в системе, т.е. клиент может быть либо забанен, либо разбанен. По умолчанию при регистрации бан у клиента отсутствует',
                          `user_iduser` int(10) unsigned NOT NULL,
                          PRIMARY KEY (`user_iduser`),
                          UNIQUE KEY `email_UNIQUE` (`email`),
                          UNIQUE KEY `user_iduser_UNIQUE` (`user_iduser`),
                          KEY `fk_client_user1_idx` (`user_iduser`),
                          CONSTRAINT `fk_client_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица предназначена для хранения сведений о клиентах, зарегистрированных в системе';
SET character_set_client = @saved_cs_client ;

LOCK TABLES `client` WRITE;
ALTER TABLE `client` DISABLE KEYS ;
ALTER TABLE `client` ENABLE KEYS ;
UNLOCK TABLES;
SET TIME_ZONE=@OLD_TIME_ZONE ;

SET SQL_MODE=@OLD_SQL_MODE ;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS ;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;
SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT ;
SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS ;
SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION ;
SET SQL_NOTES=@OLD_SQL_NOTES ;


