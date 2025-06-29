# Ubermoto Microservice

Este é um microserviço REST em Java 17 e Spring Boot para gestão de motoristas e solicitações de corrida. Foi projetado com foco em boas práticas, Docker Compose para Postgres, documentação OpenAPI e testes.

---

## ✅ Tecnologias Utilizadas

- Java 17  
- Spring Boot  
  - Web  
  - Data JPA  
  - Validation  
- PostgreSQL (via Docker Compose)  
- HikariCP (pool de conexões)  
- MapStruct (mapeamento DTO ↔ Entity)  
- SpringDoc OpenAPI (Swagger UI)  
- Lombok  
- JUnit 5 + Mockito (testes unitários)  
- Postman (testes manuais de endpoints)  

---

## 📌 Funcionalidades da API

### Drivers

- **GET /api/drivers**  
  Lista todos os motoristas cadastrados e disponíveis.

### Ride Requests

- **POST /api/rides/request**  
  Solicita uma corrida.  
  Body (JSON):
  ```json
  {
    "startLat": -20.541,
    "startLng": -47.405,
    "endLat"  : -20.530,
    "endLng"  : -47.400
  }
  ```
  Retorno de sucesso:
  ```json
  {
    "rideId"  : 1,
    "driverId": 1,
    "status"  : "CONFIRMED"
  }
  ```

---

## 🐳 Banco de Dados com Docker Compose

```yaml
version: "3.8"
services:
  postgres:
    image: postgres:15
    container_name: ubermoto-postgres
    restart: always
    environment:
      POSTGRES_DB: ubermoto
      POSTGRES_USER: uber
      POSTGRES_PASSWORD: uberpass
    ports:
      - "5432:5432"
    volumes:
      - ./data-postgres:/var/lib/postgresql/data
```

Para subir o Postgres local:

```bash
docker-compose up -d
```

Configurações em `src/main/resources/application.properties`:

```properties
spring.datasource.jdbc-url=jdbc:postgresql://localhost:5432/ubermoto
spring.datasource.username=uber
spring.datasource.password=uberpass
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.defer-datasource-initialization=true

spring.sql.init.mode=always
spring.sql.init.data-locations=classpath:/data.sql
```

---

## ✅ Testes Unitários

O projeto inclui testes de unidade para as principais camadas:

- Services (negócio)  
- Repositories (acesso a dados)  
- Controllers (integração com MVC)

Para rodar todos os testes:

```bash
./mvnw test
```

---

## 🔎 Testes com Postman

A coleção Postman está disponível em `postman/ubermoto.postman_collection.json`.  
Para importar:

1. Abra o Postman  
2. **Import** → **Upload Files** → selecione o arquivo JSON  
3. Configure a variável de ambiente `baseUrl` como `http://localhost:8080`  

---

## 📂 Clonando o Projeto

```bash
git clone https://github.com/Roggerrs/ubermoto.git
cd ubermoto
```

---

## 🏁 Como Executar Localmente

1. Subir o banco (Postgres) via Docker Compose:
   ```bash
   docker-compose up -d
   ```
2. Build e run da aplicação:
   ```bash
   ./mvnw clean package -DskipTests
   java -jar target/ubermoto-0.0.1-SNAPSHOT.jar
   ```
3. Acesse a Swagger UI em:  
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## 📢 Tags

```
#java #springboot #microservice #docker #postgresql #tdd #openapi
```

---

## 📄 Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

✍️ **Autor**  
Roger ([@rogerr](https://github.com/Roggerrs))
