package task.manager.task_manager.controller.user;

import lombok.*;
import jakarta.validation.constraints.*;
import task.manager.task_manager.model.user.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {

    private String id;

    @NotBlank(message = "El nombre es requerido")
    @Pattern(regexp = "[a-zA-Z ]+$", message = "El nombre no es valido")
    @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
    private String fullName;

    @NotBlank(message = "El correo es requerido")
    @Email(message = "El correo no es válido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 8, max = 150, message = "La contraseña debe contener al menos 8 caracteres")
    private String password;

    public User convert(){
        return new User(id, fullName, email, password);
    }

}
