package task.manager.task_manager.model;

import jakarta.persistence.*;
import task.manager.task_manager.model.project.Project;
import task.manager.task_manager.model.tag.Tag;

import java.util.Set;

@Entity
@Table(name = "task")
public class Task {

    @Id
    private String id;

    @Column(name = "name", length = 150, nullable = false )
    private String name;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @Column(name = "comments", length = 250, nullable = false)
    private String comments;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date", nullable = false)
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "id_project", referencedColumnName = "id")
    private Project project;

    @ManyToMany
    @JoinTable(
            name = "task_tags",
            joinColumns = @JoinColumn(name = "id_task"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private Set<Tag> tags;

}
