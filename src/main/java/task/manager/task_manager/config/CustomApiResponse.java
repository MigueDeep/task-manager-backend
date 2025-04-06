package task.manager.task_manager.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomApiResponse<T> {

    private T data;

    private boolean error;

    private HttpStatus status;

    private String message;

}
