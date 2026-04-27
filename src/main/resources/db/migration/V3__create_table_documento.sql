CREATE TABLE IF NOT EXISTS `documento` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `atualizado_em` datetime(6) NOT NULL,
    `caminho_arquivo` varchar(255) NOT NULL,
    `criado_em` datetime(6) NOT NULL,
    `data_emissao` date NOT NULL,
    `data_vencimento` date NOT NULL,
    `descricao` text,
    `numero_processo` varchar(255) DEFAULT NULL,
    `status` enum('DISPENSADO','EM_ANALISE','EXPIRADO','RENOVADO','SUSPENSO','VALIDO') NOT NULL,
    `tipo_documento` enum('AUTORIZACAO_QUEIMA','AUTORIZACAO_SUPRESSAO','CAR','CERTIFICADO_DISPENSA','LI','LO','LP','OUTORGA','OUTROS','PRA','PRADA') NOT NULL,
    `propriedade_id` bigint DEFAULT NULL,
    CONSTRAINT fk_documento_propriedade FOREIGN KEY (propriedade_id) REFERENCES propriedade(id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

