CREATE TABLE IF NOT EXISTS `propriedade` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `area_total` double NOT NULL,
    `cidade` varchar(255) NOT NULL,
    `cnpj` varchar(255) NOT NULL,
    `nome` varchar(255) NOT NULL,
    `usuario_id` bigint NOT NULL,
    CONSTRAINT `fk_propriedade_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
