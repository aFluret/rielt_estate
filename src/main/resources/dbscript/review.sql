use rielt_estate;
DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
                        `idreview` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Синтетический ключ, который играет роль уникального идентификатора каждого клиентского отзыва',
                        `review_text` text CHARACTER SET utf8 COMMENT 'Поле, в котором клиент хранит текстовый отзыв о выполнении заказа',
                        `mark` int(10) unsigned NOT NULL COMMENT 'Оценка, которую клиент выставляет заказу',
                        `client_user_iduser` int(10) unsigned NOT NULL,
                        PRIMARY KEY (`idreview`),
                        KEY `fk_review_client1_idx` (`client_user_iduser`),
                        CONSTRAINT `fk_review_client1` FOREIGN KEY (`client_user_iduser`) REFERENCES `client` (`user_iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COMMENT='Таблица предназначена для хранения информации о клиентских отзывах на их заказы';

LOCK TABLES `review` WRITE;

UNLOCK TABLES;
