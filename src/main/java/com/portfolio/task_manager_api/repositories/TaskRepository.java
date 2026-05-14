package com.portfolio.task_manager_api.repositories;

import com.portfolio.task_manager_api.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Interface responsável pela camada de acesso a dados (Data Access Object - DAO) para a entidade Task.
Ao estender JpaRepository, o Spring Data fornece automaticamente a implementação em tempo de execução para as operações CRUD (Create, Read, Update, Delete) básicas, eliminando a necessidade de escrever queries SQL manualmente */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // O JpaRepository recebe dois parâmetros genéricos:
    // 1. A entidade que ele vai gerenciar (Task)
    // 2. O tipo de dado da Chave Primária dessa entidade (Long)

    // Consultas personalizadas (Custom Queries) podem ser declaradas aqui no futuro.
}