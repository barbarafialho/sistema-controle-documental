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
- **Spring Boot Validation** - Validação de dados de entrada

## 📦 Dependências Principais

```xml
<!-- Web -->
spring-boot-starter-web

<!-- Banco de Dados -->
spring-boot-starter-data-jpa
flyway-core
flyway-mysql
mysql-connector-j

<!-- Autenticação -->
spring-boot-starter-security
java-jwt

<!-- Email -->
spring-boot-starter-mail

<!-- Documentação -->
springdoc-openapi-starter-webmvc-ui

<!-- Mapeamento -->
mapstruct

<!-- Testes -->
spring-boot-starter-test
```

## 🚀 Começando

### Pré-requisitos
- **Java 21** ou superior
- **Maven 3.6+**
- **MySQL 8.0+**

### Instalação

1. **Clone o repositório**
   ```bash
   git clone https://github.com/barbarafialho/sistema-controle-documental.git
   cd sistema-controle-documental
   ```

2. **Configure o banco de dados**
   - Crie um banco de dados MySQL
   - Configure as credenciais em `application.properties` ou `application.yml`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

3. **Execute a aplicação**
   ```bash
   mvn clean spring-boot:run
   ```
   
   Ou, se preferir, faça build e execute o JAR:
   ```bash
   mvn clean package
   java -jar target/sistema-controle-documental-0.0.1-SNAPSHOT.jar
   ```

## 📚 Documentação da API

A documentação interativa da API estará disponível em:
```
http://localhost:8080/swagger-ui.html
```

Acesse também o arquivo OpenAPI em:
```
http://localhost:8080/api-docs
```

## 🏗️ Estrutura do Projeto

```
sistema-controle-documental/
├── src/
│   ├── main/
│   │   ├── java/br/com/barbara/
│   │   │   ├── controller/      # Controladores REST
│   │   │   ├── service/         # Lógica de negócio
│   │   │   ├── repository/      # Acesso a dados
│   │   │   ├── entity/          # Entidades JPA
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── mapper/          # Mapeadores (MapStruct)
│   │   │   ├── security/        # Configurações de segurança
│   │   │   └── exception/       # Exceções customizadas
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/    # Scripts SQL do Flyway
│   └── test/                    # Testes unitários e integração
├── pom.xml                      # Configuração Maven
└── README.md
```

## 🔐 Segurança

- Autenticação via **JWT (JSON Web Tokens)**
- Senhas criptografadas com **Spring Security**
- Validação de entrada com **Spring Boot Validation**
- Controle de acesso baseado em roles

## 📧 Email

O sistema utiliza **Spring Mail** para envio de notificações por email. Configure as credenciais SMTP em:
```properties
spring.mail.host=seu_host_smtp
spring.mail.port=seu_porta
spring.mail.username=seu_email
spring.mail.password=sua_senha
```

## 🔄 Migração de Banco de Dados

As migrações de schema são gerenciadas pelo **Flyway**. Os scripts SQL devem ser colocados em:
```
src/main/resources/db/migration/
```

Padrão de nomenclatura: `V001__descricao.sql`, `V002__outra_descricao.sql`, etc.

## 🧪 Testes

Execute os testes com:
```bash
mvn test
```

## 📝 Versão

- **Versão Atual**: 0.0.1-SNAPSHOT
- **Versão Java**: 21
- **Versão Spring Boot**: 3.4.2

## 👤 Autor

**Barbara Fialho**

## 📄 Licença

Este projeto é de código aberto. Verifique o arquivo LICENSE para mais detalhes.

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Faça um Fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📞 Suporte

Para dúvidas ou sugestões, abra uma **issue** no repositório.

---

**Desenvolvido com ❤️ usando Spring Boot**
