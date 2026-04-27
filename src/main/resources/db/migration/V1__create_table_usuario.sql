CREATE TABLE IF NOT EXISTS `usuario` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `cpf_cnpj` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `nome` varchar(255) NOT NULL,
    `senha` varchar(255) NOT NULL,
    UNIQUE KEY `uk_usuario_cpf_cnpj` (`cpf_cnpj`),
    UNIQUE KEY `uk_usuario_email` (`email`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;