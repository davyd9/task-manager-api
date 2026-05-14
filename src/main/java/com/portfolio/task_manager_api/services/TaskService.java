package com.portfolio.task_manager_api.services;

import com.portfolio.task_manager_api.models.Task;
import com.portfolio.task_manager_api.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de Serviço (Service). 
 * Criei essa camada para evitar que o Controller fique muito "gordo" com lógicas de negócio.
 * Se no futuro precisarmos validar regras complexas (ex: uma tarefa não pode ter o mesmo nome de outra),
 * é aqui que o código vai morar, mantendo o Controller responsável apenas por receber e responder as requisições web.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Injeção de dependência via construtor. 
    // É a prática mais recomendada atualmente, pois facilita a criação de testes unitários caso a gente precise mockar o repositório depois.
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Busca todas as tarefas cadastradas no banco
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Busca uma tarefa específica pelo ID.
    // Retorno um Optional porque o ID que o usuário mandou pode não existir no banco.
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Salva uma nova tarefa (ou atualiza uma existente se o ID já vier preenchido)
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    // Deleta a tarefa. Antes de tentar deletar na marra, confere se a tarefa realmente existe lá.
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false; // Retorna falso se não achou o ID, assim o Controller sabe que deu ruim.
    }
}