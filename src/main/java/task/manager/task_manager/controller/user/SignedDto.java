package task.manager.task_manager.controller.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import task.manager.task_manager.model.user.User;

@Data
@AllArgsConstructor
public class SignedDto {
    String token;
    User user;
}
