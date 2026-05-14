package com.portfolio.task_manager_api.controllers;

import com.portfolio.task_manager_api.models.Task;
import com.portfolio.task_manager_api.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST da nossa API de Tarefas.
 * Ele atua como o entregador da aplicação: recebe o pedido do cliente e manda o Service preparar, e devolve a resposta no formato JSON */
@RestController
@RequestMapping("/api/tasks") // Todas as URLs desse arquivo vão começar com esse caminho
public class TaskController {

    private final TaskService taskService;

    // Injetando o Service que acabamos de criar
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Endpoint para listar todas as tarefas (GET em /api/tasks)
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks); // Retorna Status 200 (OK) junto com a lista.
    }

    // Endpoint para buscar uma única tarefa pelo ID dela na URL (GET em /api/tasks/1)
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        
        // Se achou a tarefa, retorna 200 com ela. Se o Optional vier vazio, retorna um Erro 404.
        return task.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para criar uma nova tarefa
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        // O @RequestBody pega o JSON que o usuário mandou no corpo da requisição e converte em um objeto Task do Java.
        Task savedTask = taskService.saveTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask); // Retorna Status 201 indicando sucesso.
    }

    // Endpoint para atualizar uma tarefa
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Optional<Task> existingTask = taskService.getTaskById(id);

        if (existingTask.isPresent()) {
            Task taskToUpdate = existingTask.get();
            // Atualizando apenas os campos que podem ser modificados pelo usuário
            taskToUpdate.setTitle(updatedTask.getTitle());
            taskToUpdate.setDescription(updatedTask.getDescription());
            taskToUpdate.setCompleted(updatedTask.isCompleted());
            
            Task saved = taskService.saveTask(taskToUpdate);
            return ResponseEntity.ok(saved);
        } else {
            return ResponseEntity.notFound().build(); // Erro 404 se tentar atualizar uma tarefa que não existe.
        }
    }

    // Endpoint para deletar uma tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Status 204 - Deletou com sucesso e não tem nada pra devolver.
        } else {
            return ResponseEntity.notFound().build(); // Erro 404 se a tarefa não existia.
        }
    }
}