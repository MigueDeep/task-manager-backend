package task.manager.task_manager.controller.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import task.manager.task_manager.config.EnumValidator;
import task.manager.task_manager.model.task.Task;
import task.manager.task_manager.model.task.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class TaskDto {

    @Schema(hidden = true)
    private UUID id;

    @NotBlank(message = "El nombre es requerido")
    @Length(max = 50, message = "El nombre no debe exceder los 50 caracteres")
    private String name;

    @NotBlank(message = "La descripción es requerida")
    @Length(max = 250, message = "La descripción no debe exceder los 250 caracteres")
    private String description;

    @NotBlank(message = "El comentario es requerido")
    @Length(max = 250, message = "El comentario no debe exceder los 250 caracteres")
    private String comment;

    @NotNull(message = "El estado es requerido")
    @EnumValidator(enumClass = TaskStatus.class, message = "El estado debe ser un valor válido para TaskStatus")
    private String status;

    @NotNull(message = "La fecha de inicio es requerida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "La fecha de fin es requerida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull(message = "El ID del proyecto es requerido")
    private UUID projectId;

    public Task toTask() {
        Task task = new Task();
        task.setName(this.name);
        task.setDescription(this.description);
        task.setComment(this.comment);
        task.setStatus(TaskStatus.valueOf(this.status));
        task.setStartDate(this.startDate.toString());
        task.setEndDate(this.endDate.toString());
        return task;
    }

}
