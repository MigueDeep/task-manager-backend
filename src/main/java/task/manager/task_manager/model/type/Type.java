package task.manager.task_manager.model.type;


import jakarta.persistence.*;
import lombok.Data;
import task.manager.task_manager.model.project.Project;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "type")
@Data
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", length = 150, nullable = false )
    private String name;

    @Column(name = "color", length = 250, nullable = false)
    private String color;

    @OneToMany(mappedBy = "type")
    private List<Project> projects;

}
