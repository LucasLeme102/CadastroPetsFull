# 🐾 Sistema de Cadastro de Pets com Spring Boot

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)


Uma API RESTful robusta e segura, desenvolvida com **Spring Boot**, dedicada ao gerenciamento completo de **pets**, **tutores** e seus respectivos **endereços**. O sistema incorpora **autenticação JWT**, **controle de acesso refinado baseado em perfis de usuário (roles)** e uma **documentação de API interativa via Swagger (SpringDoc OpenAPI)**, facilitando o desenvolvimento e consumo.

---

## 📋 Tabela de Conteúdos

* [🚀 Funcionalidades](#-funcionalidades)
* [🔧 Tecnologias Utilizadas](#-tecnologias-utilizadas)
* [🧠 Regras de Negócio](#-regras-de-negócio)
* [🔐 Autenticação e Autorização](#-autenticação-e-autorização)
* [▶️ Como Rodar o Projeto](#%EF%B8%B4-como-rodar-o-projeto)
    * [1. Clonar o Repositório](#1-clonar-o-repositório)
    * [2. Configurar o Banco de Dados](#2-configurar-o-banco-de-dados)
    * [3. Configurar `application.properties`](#3-configurar-applicationproperties)
    * [4. Rodar o Projeto](#4-rodar-o-projeto)
    * [5. Acessar a Documentação Swagger](#5-acessar-a-documentação-swagger)
* [📦 Estrutura do Projeto](#-estrutura-do-projeto)
* [📘 Documentação da API](#-documentação-da-api)
* [💡 Exemplos de Requisição](#-exemplos-de-requisição)
* [🚧 Funcionalidades Futuras](#-funcionalidades-futuras)
* [🤝 Contribuições](#-contribuições)
* [🧑‍💻 Autor](#-autor)
* [📝 Licença](#-licença)

---

## 🚀 Funcionalidades

* ✅ **Gerenciamento de Pets:** Operações completas de Criação, Leitura, Atualização e Exclusão (CRUD).
* ✅ **Gerenciamento de Tutores:** Cadastro e associação de tutores com seus pets.
* ✅ **Gerenciamento de Endereços:** Cadastro de endereços e associação com cada pet.
* ✅ **Autenticação Segura:** Sistema de login/logout baseado em JWT para proteger os endpoints da API.
* ✅ **Controle de Acesso por Roles:** Definição de permissões (`ROLE_TUTOR`, `ROLE_ADMIN`) para diferentes níveis de acesso.
* ✅ **Documentação Interativa:** Geração automática e visualização da API via Swagger (SpringDoc OpenAPI).
* ✅ **Tratamento Global de Exceções:** Respostas de erro padronizadas e amigáveis para o consumidor da API.

---

## 🔧 Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Framework:** Spring Boot (com Spring Web, Spring Data JPA, Spring Security)
* **Segurança:** JWT (JSON Web Token)
* **Banco de Dados:** PostgreSQL
* **ORM:** Spring Data JPA / Hibernate
* **Geração de Código:** Lombok (para reduzir boilerplate)
* **Documentação API:** Swagger (SpringDoc OpenAPI)
* **Gerenciador de Dependências:** Apache Maven

---

## 🧠 Regras de Negócio

* Um **pet** deve estar obrigatoriamente associado a um **tutor**.
* Não é permitido o cadastro de tutores com nomes completos duplicados para garantir a unicidade.
* Acesso aos endpoints é regido por **roles (perfis)**, por filtros Do método que retorna ```FilterChain```.

---

## 🔐 Autenticação e Autorização

O sistema implementa um controle de acesso baseado em JWT e roles para garantir a segurança dos endpoints.

### Perfis de Usuário (Roles)

* `ROLE_TUTOR`: Acesso básico para operações de leitura e criação (GET, POST) de pets.
* `ROLE_ADMIN`: Acesso total à API, incluindo operações de atualização e exclusão em todos os recursos.

### Endpoints Protegidos (Exemplos)

| Método | Rota                | Descrição                          | Permissão Mínima    |
| :----- | :------------------ | :---------------------------------- | :------------------ |
| `POST` | `/auth/login`       | Realiza o login e retorna um JWT.   | `Público`           |
| `POST` | `/auth/register`         | Cria um novo usuário.               | `Público`           |
| `GET`  | `/api/pets`             | Lista todos os pets.                | `ROLE_USER`         |
| `POST` | `/api/pets`             | Cadastra um novo pet.               | `ROLE_USER`         |
| `DELETE`| `/api/pets/{id}`        | Exclui um pet pelo ID.              | `ROLE_ADMIN`        |
| `GET`  | `/api/tutores`          | Lista todos os tutores.             | `ROLE_ADMIN`        |
| `PUT`  | `/api/pets/{id}`     | Atualiza um pet pelo ID.          | `ROLE_ADMIN`        |

---

## ▶️ Requisicoes 

Siga estas instruções para configurar e executar a API.


```bash
📦 Estrutura do Projeto
.
├── src/main/java/com/seuprojeto/pets/
│   ├── controller/      # Endpoints da API REST
│   ├── dto/             # Objetos de Transferência de Dados (Requisições/Respostas)
│   ├── entity/          # Modelos de Entidades (mapeamento com o banco de dados)
│   ├── repository/      # Interfaces para acesso a dados (Spring Data JPA)
│   ├── service/         # Camada de regras de negócio
│   ├── security/        # Classes de configuração de segurança (JWT, filtros, autenticação)
│   │   ├── JwtFilter.java
│   │   ├── JwtService.java
│   │   ├── SecurityConfig.java
│   │   └── UserDetailsImpl.java
│   ├── exception/       # Classes de exceção personalizadas e handlers
│   └── util/            # Classes utilitárias 
└── src/main/resources/
    ├── application.properties # Configurações da aplicação
    └── static/
    └── db.migrations/ #Configurações do Flyway e a criacao de tabelas no banco.
    └── templates/

💡 Exemplos de Requisição
Para interagir com a API, você precisará primeiro obter um token JWT.

1. Login (Obter JWT)

Requisição (POST): /auth/login
Headers: Content-Type: application/json
JSON
{
  "username": "seu_usuario",
  "password": "sua_senha"
}
Resposta (Exemplo de Sucesso):

JSON
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
2. Cadastrar Novo Pet

Requisição (POST): /pets
Headers:

Authorization: Bearer SEU_TOKEN_JWT
Content-Type: application/json
<!-- end list -->

JSON
{
  "nome": "Mel",
  "idade": 3,
  "raca": "Golden Retriever",
  "peso": 28.5,
  "tipo": "CACHORRO",
  "tutorId": "6ec9d5ec-5363-4e33-b083-aa32b9da68f7",
  "endereco": {
    "estado": "SP",
    "cidade": "São Paulo",
    "rua": "Rua das Flores",
    "numero": "100"
}

Resposta (Exemplo de Sucesso - Status 201 Created):

JSON
{
    "id": "95a4b431-b8ba-4e87-a572-855cf0796d97",
    "nome": "Mel Silva",
    "idade": 4,
    "tipo": "CACHORRO",
    "raca": "Golden Retriever",
    "peso": 28.5,
    "endereco": {
        "rua": "Rua das Flores",
        "numero": "100",
        "cidade": "São Paulo",
        "estado": "SP"
    },
    "tutor": {
        "id": "a6c623f7-d94d-4d6b-8abb-0c931f1140de",
        "nome": "Lucas Leme",
        "telefone": "(15)99787-1233",
        "email": "lukinha@gmail.com"
    },
    "criadoEm": "2025-05-22T19:07:59.365592",
    "_links": {
        "tutor": {
            "href": "http://localhost:8080/api/tutores/a6c623f7-d94d-4d6b-8abb-0c931f1140de"
        }
    }
}

🚧 Funcionalidades Futuras
📌 Documentação completa da Api.
📌 Deploy em nuvem.
📌 Possível desenvolvimento de uma sistema Front-End.

