package task.manager.task_manager.model.type;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.manager.task_manager.model.project.Project;
import task.manager.task_manager.model.user.User;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", length = 150, nullable = false )
    private String name;

    @Column(name = "color", length = 250, nullable = false)
    private String color;

    @OneToMany(mappedBy = "type")
    @JsonIgnore
    private List<Project> projects;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private User user;
}
