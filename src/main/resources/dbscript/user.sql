
use rielt_estate;
CREATE TABLE `user` (
                      `iduser` int(10) unsigned NOT NULL AUTO_INCREMENT,
                      `login` varchar(45) CHARACTER SET utf8 NOT NULL,
                      `password` varchar(45) CHARACTER SET utf8 NOT NULL,
                      `role` tinyint(4) NOT NULL,
                      PRIMARY KEY (`iduser`),
                      UNIQUE KEY `iduser_UNIQUE` (`iduser`),
                      UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


