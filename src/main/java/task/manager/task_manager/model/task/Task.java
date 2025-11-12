package task.manager.task_manager.model.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.manager.task_manager.model.project.Project;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", length = 150, nullable = false )
    private String name;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @Column(name = "comment", length = 250, nullable = false)
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 15, nullable = false)
    private TaskStatus status;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date", nullable = false)
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "id_project", referencedColumnName = "id")
    @JsonIgnoreProperties({"tasks", "type"})
    private Project project;

}
