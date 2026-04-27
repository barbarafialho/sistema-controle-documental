CREATE TABLE IF NOT EXISTS `config_servidor_email` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `atualizado_em` datetime(6) NOT NULL,
    `criado_em` datetime(6) NOT NULL,
    `host` varchar(255) NOT NULL,
    `porta` varchar(255) NOT NULL,
    `nome_usuario` varchar(255) NOT NULL,
    `senha` varchar(255) NOT NULL,
    `protocolo` varchar(255) NOT NULL
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;