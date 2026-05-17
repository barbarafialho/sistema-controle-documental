# 📋 Sistema de Controle Documental

Um sistema robusto e seguro para gerenciamento e controle de documentos, desenvolvido com **Spring Boot 3** e **Java 21**.

## 🎯 Sobre o Projeto

O Sistema de Controle Documental é uma aplicação backend que fornece uma plataforma completa para:
- 📁 Armazenamento e organização de documentos
- 🔐 Autenticação e autorização segura (JWT)
- 📧 Notificações por email
- 🗄️ Persistência de dados com MySQL
- 📖 Documentação interativa com Swagger/OpenAPI

## 🛠️ Tecnologias Utilizadas

### Core
- **Java 21** - Linguagem de programação
- **Spring Boot 3.4.2** - Framework web
- **Maven** - Gerenciador de dependências e build

### Banco de Dados
- **MySQL** - Sistema de gerenciamento de banco de dados relacional
- **JPA/Hibernate** - ORM (Object-Relational Mapping)
- **Flyway** - Controle de versão de schema do banco de dados

### Autenticação & Segurança
- **Spring Security** - Autenticação e autorização
- **JWT (java-jwt 4.4.0)** - JSON Web Tokens para autenticação stateless

### Outros
- **SpringDoc OpenAPI** - Documentação automática com Swagger UI
- **MapStruct** - Mapeamento de objetos (DTO ↔ Entity)
- **Spring Boot DevTools** - Desenvolvimento mais ágil com live reload

## 🏗️ Estrutura do Projeto

```
sistema-controle-documental/
├── src/
│   ├── main/
│   │   ├── java/br/com/barbara/
│   │   │   ├── controller/      # Controladores REST
│   │   │   ├── service/         # Lógica de negócio
│   │   │   ├── repository/      # Acesso a dados
│   │   │   ├── model/           # Entidades JPA
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── mapper/          # Mapeadores (MapStruct)
│   │   │   ├── security/        # Configurações de segurança
│   │   │   └── exceptions/      # Exceções customizadas
│   │   └── resources/
│   │       ├── application.yaml
│   │       └── db/migration/    # Scripts SQL do Flyway
│   └── test/                    # Testes unitários e integração
├── pom.xml                      # Configuração Maven
└── README.md
```

## 🔐 Segurança

- Autenticação via JWT (JSON Web Tokens)
- Senhas armazenadas com hash BCrypt
- Controle de acesso baseado em roles e no ID do usuário autenticado

## 📧 Email

O sistema utiliza o Spring Mail para o envio de notificações automáticas por e-mail.
As configurações do servidor SMTP devem ser cadastradas na tabela `config_servidor_email`.

## 📝 Versão

- **Versão Atual**: 0.0.1-SNAPSHOT
- **Versão Java**: 21
- **Versão Spring Boot**: 3.4.2

## ▶️ Como Executar

```bash
git clone https://github.com/barbarafialho/sistema-controle-documental.git
cd sistema-controle-documental
mvn spring-boot:run
```

## 📖 Documentação da API

Após iniciar a aplicação, acesse:

http://localhost:8080/swagger-ui/

## ⚙️ Configuração

Crie um banco de dados MySQL com o nome `sistema_controle_documental`.

O perfil `local` é carregado por padrão. As configurações específicas do ambiente podem ser definidas no arquivo `application-local.yaml` ou por meio das seguintes variáveis de ambiente:

```env
DB_USERNAME=root
DB_PASSWORD=123456
JWT_SECRET=seu-segredo
JWT_EXPIRATION=86400000
```

Ao iniciar a aplicação, o Flyway executará automaticamente as migrations localizadas em `src/main/resources/db/migration`.

## 👤 Autor

**Barbara Fialho**

## 📞 Suporte

Para dúvidas ou sugestões, abra uma **issue** no repositório.

---

**Desenvolvido usando Spring Boot**
