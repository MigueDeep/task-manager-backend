package task.manager.task_manager.model.project;

import org.springframework.data.jpa.repository.JpaRepository;
import task.manager.task_manager.model.type.Type;

import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    Optional<Project> findByName(String name);

}
