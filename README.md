# Task Manager API

Uma API RESTful desenvolvida em **Java** com o framework **Spring Boot** para gerenciamento de tarefas. Este projeto foi construído para demonstrar a aplicação de arquitetura limpa, boas práticas de Back-end e persistência de dados.

## Tecnologias Utilizadas
* **Linguagem:** Java 17
* **Framework:** Spring Boot 3
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** H2 Database (In-Memory)
* **Gerenciamento de Dependências:** Maven

## Arquitetura do Projeto
O sistema foi estruturado seguindo o padrão de **Layered Architecture** (Arquitetura de Camadas), separando claramente as responsabilidades:

* **Controllers:** Camada responsável por interceptar as requisições HTTP (REST) e retornar as respostas (JSON).
* **Services:** Camada de isolamento onde ficam as lógicas e regras de negócio da aplicação.
* **Repositories:** Interfaces de abstração para o acesso a dados (Data Access Object), estendendo o `JpaRepository`.
* **Models:** Entidades que representam as tabelas do banco de dados, mapeadas via anotações JPA.

## Como executar localmente

1. Clone este repositório para sua máquina local:
   ```bash
   git clone [https://github.com/SEU_USUARIO/task-manager-api.git](https://github.com/SEU_USUARIO/task-manager-api.git)
