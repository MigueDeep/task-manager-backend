package task.manager.task_manager.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import task.manager.task_manager.config.CustomApiResponse;
import task.manager.task_manager.config.CustomDtoValidator;
import task.manager.task_manager.controller.task.TaskDto;
import task.manager.task_manager.model.project.Project;
import task.manager.task_manager.model.project.ProjectRepository;
import task.manager.task_manager.model.task.Task;
import task.manager.task_manager.model.task.TaskRepository;
import task.manager.task_manager.model.task.TaskStatus;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService extends CustomDtoValidator<TaskDto> {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public CustomApiResponse<List<Task>> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if (tasks.isEmpty()) {
            return new CustomApiResponse<>(null, true, HttpStatus.NOT_FOUND, "No se encontraron tareas");
        }
        return new CustomApiResponse<>(tasks, false, HttpStatus.OK, "Tareas obtenidas exitosamente");
    }

    public CustomApiResponse<Task> createTask(TaskDto taskDto) {
        String errors = getErrors(taskDto);
        if (!errors.isEmpty()) {
            return new CustomApiResponse<>(null, true, HttpStatus.BAD_REQUEST, errors);
        }

        Project existingProject = projectRepository.findById(taskDto.getProjectId()).orElse(null);
        if (existingProject == null) {
            return new CustomApiResponse<>(null, true, HttpStatus.NOT_FOUND, "Proyecto no encontrado");
        }

        Task task = taskDto.toTask();
        task.setProject(existingProject);
        taskRepository.save(task);
        return new CustomApiResponse<>(task, false, HttpStatus.CREATED, "Tarea creada exitosamente");
    }

    public CustomApiResponse<Task> getTaskById(UUID id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return new CustomApiResponse<>(null, true, HttpStatus.NOT_FOUND, "Tarea no encontrada");
        }
        return new CustomApiResponse<>(task, false, HttpStatus.OK, "Tarea obtenida exitosamente");
    }

    public CustomApiResponse<List<Task>> getTaskByProjectId(UUID id) {
        List<Task> tasks = taskRepository.findByProjectId(id);
        if (tasks.isEmpty()) {
            return new CustomApiResponse<>(null, true, HttpStatus.NOT_FOUND, "No se encontraron tareas para el proyecto dado");
        }
        return new CustomApiResponse<>(tasks, false, HttpStatus.OK, "Tareas obtenidas exitosamente");
    }

    public CustomApiResponse<Task> deleteTaskById(UUID id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return new CustomApiResponse<>(null, true, HttpStatus.NOT_FOUND, "Tarea no encontrada");
        }
        taskRepository.delete(task);
        return new CustomApiResponse<>(task, false, HttpStatus.OK, "Tarea eliminada exitosamente");
    }

    public CustomApiResponse<Task> updateTask(UUID id, TaskDto taskDto){
        String errors = getErrors(taskDto);
        if (!errors.isEmpty()) {
            return new CustomApiResponse<>(null, true, HttpStatus.BAD_REQUEST, errors);
        }

        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask == null) {
            return new CustomApiResponse<>(null, true, HttpStatus.NOT_FOUND, "Tarea no encontrada");
        }

        Project existingProject = projectRepository.findById(taskDto.getProjectId()).orElse(null);
        if (existingProject == null) {
            return new CustomApiResponse<>(null, true, HttpStatus.NOT_FOUND, "Proyecto no encontrado");
        }

        existingTask.setId(id);
        existingTask.setName(taskDto.getName());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setComment(taskDto.getComment());
        existingTask.setStatus(taskDto.getStatus());
        existingTask.setStartDate(taskDto.getStartDate().toString());
        existingTask.setEndDate(taskDto.getEndDate().toString());
        existingTask.setProject(existingProject);
        taskRepository.save(existingTask);
        return new CustomApiResponse<>(existingTask, false, HttpStatus.OK, "Tarea actualizada exitosamente");
    }

    public CustomApiResponse<Task> updateStatusTask(UUID id, TaskStatus status){
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask == null) {
            return new CustomApiResponse<>(null, true, HttpStatus.NOT_FOUND, "Tarea no encontrada");
        }

        existingTask.setStatus(status);
        taskRepository.save(existingTask);
        return new CustomApiResponse<>(existingTask, false, HttpStatus.OK, "Estado de la tarea actualizado exitosamente");
    }


}
