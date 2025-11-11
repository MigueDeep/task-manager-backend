package task.manager.task_manager.controller.task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import task.manager.task_manager.config.CustomApiResponse;
import task.manager.task_manager.model.task.Task;
import task.manager.task_manager.model.task.TaskStatus;
import task.manager.task_manager.service.task.TaskService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/task")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @Operation(
            summary = "Obtener Tareas",
            description = "Obtener todas las tareas.",
            tags = { "TASK" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/getAll")
    public ResponseEntity<CustomApiResponse<List<Task>>> getAllTasks() {
        CustomApiResponse<List<Task>> response = new CustomApiResponse<>();
        try{
            response = service.getAllTasks();
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(
            summary = "Obtener una Tarea por ID",
            description = "Obtener una tarea por su ID.",
            tags = { "TASK" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/getById")
    public ResponseEntity<CustomApiResponse<Task>> getTaskById(UUID id) {
        CustomApiResponse<Task> response = new CustomApiResponse<>();
        try{
            response = service.getTaskById(id);
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(
            summary = "Crear una tarea",
            description = "Crear una nueva tarea.",
            tags = { "TASK" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/create")
    public ResponseEntity<CustomApiResponse<Task>> createTask(@RequestBody TaskDto taskDto) {
        CustomApiResponse<Task> response = new CustomApiResponse<>();
        try{
            response = service.createTask(taskDto);
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(
            summary = "Eliminar una Tarea por ID",
            description = "Eliminar una tarea por su ID.",
            tags = { "TASK" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/deleteById")
    public ResponseEntity<CustomApiResponse<Task>> deleteTaskById(@RequestParam UUID id) {
        CustomApiResponse<Task> response = new CustomApiResponse<>();
        try{
            response = service.deleteTaskById(id);
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(
            summary = "Actualizar una Tarea",
            description = "Actualizar una tarea.",
            tags = { "TASK" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/update")
    public ResponseEntity<CustomApiResponse<Task>> updateTask(@RequestParam UUID id, @RequestBody TaskDto taskDto) {
        CustomApiResponse<Task> response = new CustomApiResponse<>();
        try{
            response = service.updateTask(id, taskDto);
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(
            summary = "Actualizar el estado de una Tarea",
            description = "Actualizar el estado de una tarea.",
            tags = { "TASK" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/updateStatus")
    public ResponseEntity<CustomApiResponse<Task>> updateStatusTask(@RequestParam UUID id, @RequestParam TaskStatus status) {
        CustomApiResponse<Task> response = new CustomApiResponse<>();
        try{
            response = service.updateStatusTask(id, status);
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(
            summary = "Obtener Tareas por ID de Proyecto",
            description = "Obtener todas las tareas asociadas a un ID de proyecto.",
            tags = { "TASK" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/getByProjectId")
    public ResponseEntity<CustomApiResponse<List<Task>>> getTaskByProjectId(@RequestParam UUID id) {
        CustomApiResponse<List<Task>> response = new CustomApiResponse<>();
        try{
            response = service.getTaskByProjectId(id);
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
}
