package com.portfolio.task_manager_api.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/** Entidade que representa uma Tarefa no banco de dados.
 Utiliza JPA para mapeamento objeto-relacional. */
@Entity
@Table(name = "tasks")
public class Task {

    /** Chave primária da tabela. 
    O GenerationType.IDENTITY delega ao banco de dados a responsabilidade de gerar o auto-incremento. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Título da tarefa. 
    Mapeado como um campo obrigatório (nullable = false) com limite de 100 caracteres para otimização do banco. */
    @Column(nullable = false, length = 100)
    private String title;

    /** Descrição detalhada da tarefa. Limite estendido para 500.
     */
    @Column(length = 500)
    private String description;

    /** Status de conclusão. Por padrão, toda nova tarefa é criada como pendente (false)*/
    @Column(nullable = false)
    private boolean isCompleted = false;

    /** Data e hora exatas de criação do registro. O parâmetro 'updatable = false' garante a integridade da auditoria, impedindo que essa data seja alterada no futuro.*/
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}