package task.manager.task_manager.model.project;

import jakarta.persistence.*;
import lombok.*;
import task.manager.task_manager.model.Task;
import task.manager.task_manager.model.type.Type;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", length = 150, nullable = false )
    private String name;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @Column(name = "status" , length = 15, nullable = false)
    private String status;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date", nullable = false)
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "id_type", referencedColumnName = "id")
    private Type type;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;
}
