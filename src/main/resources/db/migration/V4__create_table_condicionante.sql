CREATE TABLE IF NOT EXISTS `condicionante` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `descricao` varchar(255) NOT NULL,
    `data_prazo` date NOT NULL,
    `concluida` boolean NOT NULL,
    `documento_id` bigint,
    `criado_em` datetime(6) NOT NULL,
    `atualizado_em` datetime(6) NOT NULL,
    CONSTRAINT `fk_condicionante_documento` FOREIGN KEY (documento_id) REFERENCES documento(id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;