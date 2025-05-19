# 🐾 Sistema de Cadastro de Pets com Spring Boot

Este projeto é uma API RESTful desenvolvida com **Spring Boot** que permite o cadastro e gerenciamento de **pets**, **tutores** e seus respectivos **endereços**. O sistema conta com **autenticação JWT**, **controle de acesso baseado em roles (perfis de usuário)** e **documentação interativa via Swagger**.

---

## 🔧 Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* Spring Security
* JWT (JSON Web Token)
* PostgreSQL
* Lombok
* Swagger (SpringDoc OpenAPI)
* Maven

---

## 🔐 Funcionalidades

* ✅ Cadastro, atualização, listagem e exclusão de Pets
* ✅ Cadastro de Tutores e associação com Pets
* ✅ Cadastro de Endereços e associação com Tutores
* ✅ Autenticação com JWT (login/logout)
* ✅ Controle de acesso com perfis (`ROLE_USER`, `ROLE_ADMIN`)
* ✅ Documentação da API via Swagger
* ✅ Tratamento global de exceções com respostas padronizadas

---

## 🧠 Regras de Negócio

* Um **pet** deve obrigatoriamente ter um **tutor** e um **endereço**.
* Não é permitido duplicar nomes completos de tutores.
* Cada usuário pode ter diferentes **roles** que definem o nível de acesso à API.
* O acesso aos endpoints é controlado por anotações como `@PreAuthorize` com base nas roles.

---

## 🔐 Autenticação e Autorização

### Perfis de Usuário (Roles)

* `ROLE_USER`: Acesso básico à API (GET, POST pets)
* `ROLE_ADMIN`: Acesso total (inclusive atualizações e exclusões)

### Endpoints protegidos

| Método | Rota        | Permissão    |
| ------ | ----------- | ------------ |
| POST   | /auth/login | Público      |
| POST   | /usuarios   | Público      |
| GET    | /pets       | `ROLE_USER`  |
| POST   | /pets       | `ROLE_USER`  |
| DELETE | /pets/{id}  | `ROLE_ADMIN` |
| GET    | /tutores    | `ROLE_ADMIN` |

---

## ▶️ Como Rodar o Projeto

1. **Clone o repositório**

   ```bash
   git clone https://github.com/seu-usuario/nome-do-repo.git
   ```

2. **Configure o `application.properties`**

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/petsdb
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

3. **Rode o projeto**

   * Via IDE (IntelliJ, Eclipse) ou terminal:

     ```bash
     ./mvnw spring-boot:run
     ```

4. **Acesse a documentação Swagger**

   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## 📦 Estrutura do Projeto

```
src
├── controller
├── dto
├── entity
├── repository
├── service
├── security
│   ├── JwtFilter
│   ├── JwtService
│   ├── SecurityConfig
│   └── UserDetailsImpl
├── exception
└── main
```

---

## 📘 Documentação da API

Acesse a documentação gerada automaticamente com Swagger:

```
http://localhost:8080/swagger-ui.html
```

---

## 🚧 Funcionalidades Futuras

* 📌 Upload de fotos dos pets
* 📌 Recuperação de senha por e-mail
* 📌 Painel administrativo com gráficos

---

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir um Pull Request ou relatar issues.

---

## 🧑‍💻 Autor

Desenvolvido por **\[Seu Nome]**
[LinkedIn](https://www.linkedin.com/in/seu-usuario) | [GitHub](https://github.com/seu-usuario)

---

## 📝 Licença

Este projeto está licenciado sob a licença MIT.
