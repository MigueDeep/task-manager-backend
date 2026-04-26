package task.manager.task_manager.controller.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeResponseDto {
    private UUID id;
    private String name;
    private String color;
}

