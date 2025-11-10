package task.manager.task_manager.model.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query(value = "SELECT * FROM task WHERE id_project = UUID_TO_BIN(:projectId)", nativeQuery = true)
    List<Task> findByProjectId(@Param("projectId") String projectId);



}
