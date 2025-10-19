package task.manager.task_manager.service.project;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import task.manager.task_manager.config.CustomApiResponse;
import task.manager.task_manager.config.CustomDtoValidator;
import task.manager.task_manager.controller.project.ProjectDto;
import task.manager.task_manager.model.project.Project;
import task.manager.task_manager.model.project.ProjectRepository;
import task.manager.task_manager.model.project.ProjectSpecification;
import task.manager.task_manager.model.type.Type;
import task.manager.task_manager.model.type.TypeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService extends CustomDtoValidator<ProjectDto> {

    private final ProjectRepository projectRepository;
    private final TypeRepository typeRepository;

    public CustomApiResponse<Project> createProject(ProjectDto projectDto) {
        String errors = getErrors(projectDto);
        if (!errors.isEmpty()) {
            return new CustomApiResponse<>(null, true, HttpStatus.CONFLICT, errors);
        }

        Project existingProject = projectRepository.findByName(projectDto.getName()).orElse(null);
        if (existingProject != null) {
            return new CustomApiResponse<>(null, true, HttpStatus.CONFLICT, "Ya existe un proyecto con ese nombre");
        }

        Type type = typeRepository.findById(projectDto.getTypeId())
                .orElse(null);

        if (type == null) {
            return new CustomApiResponse<>(null, true, HttpStatus.NOT_FOUND, "Tipo de proyecto no encontrado");
        }

        Project project = new Project();
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setStatus(projectDto.getStatus());
        project.setStartDate(projectDto.getStartDate());
        project.setEndDate(projectDto.getEndDate());
        project.setType(type);

        projectRepository.save(project);
        return new CustomApiResponse<>(project, false, HttpStatus.CREATED, "Proyecto creado exitosamente");
    }

    public CustomApiResponse<List<Project>> getAllProjects(String name, String status, LocalDate startDate, LocalDate endDate) {

        Specification<Project> specification = Specification
                .where(ProjectSpecification.hasName(name))
                .and(ProjectSpecification.hasStatus(status))
                .and(ProjectSpecification.hasStartDate(startDate))
                .and(ProjectSpecification.hasEndDate(endDate));

        List<Project> projects = projectRepository.findAll(specification);
        if (projects.isEmpty()) {
            return new CustomApiResponse<>(null, true, HttpStatus.NOT_FOUND, "No se encontraron proyectos");
        }
        return new CustomApiResponse<>(projects, false, HttpStatus.OK, "Proyectos encontrados exitosamente");
    }

    public CustomApiResponse<Project> getProjectById(UUID id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project == null) {
            return new CustomApiResponse<>(null, true, HttpStatus.NOT_FOUND, "Proyecto no encontrado");
        }
        return new CustomApiResponse<>(project, false, HttpStatus.OK, "Proyecto encontrado exitosamente");
    }

    public CustomApiResponse<Project> updateProject(UUID id, ProjectDto projectDto) {
        String errors = getErrors(projectDto);
        if (!errors.isEmpty()) {
            return new CustomApiResponse<>(null, true, HttpStatus.CONFLICT, errors);
        }

        Project existingProject = projectRepository.findById(id).orElse(null);
        if (existingProject == null) {
            return new CustomApiResponse<>(null, true, HttpStatus.NOT_FOUND, "Proyecto no encontrado");
        }

        Type type = typeRepository.findById(projectDto.getTypeId())
                .orElse(null);

        if (type == null) {
            return new CustomApiResponse<>(null, true, HttpStatus.NOT_FOUND, "Tipo de proyecto no encontrado");
        }

        existingProject = projectDto.toProject(type);
        existingProject.setId(id);

        projectRepository.save(existingProject);

        return new CustomApiResponse<>(existingProject, false, HttpStatus.OK, "Proyecto actualizado exitosamente");
    }
}
