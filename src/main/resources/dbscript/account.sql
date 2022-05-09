


CREATE TABLE `account` (
                         `idaccount` int(10) unsigned NOT NULL AUTO_INCREMENT
                         `account_status` decimal(10,2) DEFAULT NULL
                         `account_number` varchar(7) CHARACTER SET utf8 NOT NULL
                         `client_user_iduser` int(10) unsigned NOT NULL,
                         PRIMARY KEY (`idaccount`),
                         UNIQUE KEY `account_number_UNIQUE` (`account_number`),
                         KEY `fk_account_client1_idx` (`client_user_iduser`),
                         CONSTRAINT `fk_account_client1` FOREIGN KEY (`client_user_iduser`) REFERENCES `client` (`user_iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
