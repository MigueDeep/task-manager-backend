package task.manager.task_manager.controller.type;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import task.manager.task_manager.model.type.Type;

@Data
public class TypeDto {

    @NotBlank(message = "El nombre es requerido")
    private String name;

    @NotBlank(message = "El color es requerido")
    @Pattern(regexp = "^#[A-Fa-f0-9]{3,6}$", message = "El codigo de color no cumple con el formato, ejemplo: #A21C00")
    private String color;


}
