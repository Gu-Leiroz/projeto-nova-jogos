CREATE TABLE IF NOT EXISTS `postagem` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `autor` varchar(80) NOT NULL,
  `titulo` varchar(300) NOT NULL,
  `descricao` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
);

