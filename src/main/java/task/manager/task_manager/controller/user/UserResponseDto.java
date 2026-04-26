package task.manager.task_manager.controller.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private String id;
    private String fullName;
    private String email;
}

