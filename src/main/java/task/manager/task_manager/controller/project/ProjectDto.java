package task.manager.task_manager.controller.project;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import task.manager.task_manager.model.project.Project;
import task.manager.task_manager.model.type.Type;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ProjectDto {

    private UUID id;

    @NotBlank(message = "El nombre es requerido")
    @Length(max = 50, message = "El nombre no debe exceder los 150 caracteres")
    private String name;

    @NotBlank(message = "La descripción es requerida")
    @Length(max = 250, message = "La descripción no debe exceder los 250 caracteres")
    private String description;

    @NotBlank(message = "El estado es requerido")
    @Length(max = 15, message = "El estado no debe exceder los 15 caracteres")
    private String status;

    @NotNull(message = "La fecha de inicio es requerida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "La fecha de fin es requerida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull(message = "El tipo es requerido")
    private UUID typeId;

    public Project toProject(Type type) {
        Project project = new Project();
        project.setName(this.name);
        project.setDescription(this.description);
        project.setStatus(this.status);
        project.setStartDate(this.startDate);
        project.setEndDate(this.endDate);
        project.setType(type);
        return project;
    }

}
