CREATE TABLE IF NOT EXISTS `postagem` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `valor` double NOT NULL,
  `descricao` varchar(300) NOT NULL,
  `genero` varchar(80) NOT NULL,
  `nota` double NOT NULL,
  PRIMARY KEY (`id`)
);

