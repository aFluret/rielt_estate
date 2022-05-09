
use rielt_estate;
DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
                       `is_main` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Поле, несущее в себе информацию о том является ли данный администратор системы главным, обладающим самым широким перечнем прав как над клиентами, так и над другими администраторами системы',
                       `user_iduser` int(10) unsigned NOT NULL,
                       PRIMARY KEY (`user_iduser`),
                       UNIQUE KEY `user_iduser_UNIQUE` (`user_iduser`),
                       CONSTRAINT `fk_admin_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица предназначена для хранения информации об администраторах данной системы';



