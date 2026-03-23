package task.manager.task_manager.controller.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignedDto {
    String token;
    UserResponseDto user;
}
