package task.manager.task_manager.model.tag;

import jakarta.persistence.*;
import task.manager.task_manager.model.Task;

import java.util.Set;
import java.util.UUID;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", length = 40, nullable = false )
    private String name;

    @Column(name = "description", length = 40, nullable = false)
    private String color;

    @ManyToMany(mappedBy = "tags")
    private Set<Task> tasks;

}
