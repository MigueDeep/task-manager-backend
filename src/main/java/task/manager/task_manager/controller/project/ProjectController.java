package task.manager.task_manager.controller.project;

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
import task.manager.task_manager.model.project.Project;
import task.manager.task_manager.service.project.ProjectService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/project")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @Operation(
            summary = "Registrar Proyecto",
            description = "Crear un nuevo proyecto.",
            tags = { "PROJECT" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ProjectDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/register")
    public ResponseEntity<CustomApiResponse<Project>> register(@RequestBody ProjectDto projectDto) {
        CustomApiResponse<Project> response = new CustomApiResponse<>();
        try{
            response = projectService.createProject(projectDto);
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(
            summary = "Obtener Proyectos",
            description = "Obtener todos los proyectos.",
            tags = { "PROJECT" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ProjectDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/getAll")
    public ResponseEntity<CustomApiResponse<List<Project>>> getAllProjects() {
        CustomApiResponse<List<Project>> response = new CustomApiResponse<>();
        try{
            response = projectService.getAllProjects();
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(
            summary = "Obtener Proyectos por ID",
            description = "Obtener un proyecto por su ID.",
            tags = { "PROJECT" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ProjectDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/getById")
    public ResponseEntity<CustomApiResponse<Project>> getProjectById(@RequestParam UUID projectId) {
        CustomApiResponse<Project> response = new CustomApiResponse<>();
        try{
            response = projectService.getProjectById(projectId);
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(
            summary = "Actualizar Proyecto",
            description = "Actualizar un proyecto existente.",
            tags = { "PROJECT" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ProjectDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/update")
    public ResponseEntity<CustomApiResponse<Project>> updateProject(@RequestBody ProjectDto projectDto) {
        CustomApiResponse<Project> response = new CustomApiResponse<>();
        try{
            response = projectService.updateProject(projectDto.getId(), projectDto);
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

}
