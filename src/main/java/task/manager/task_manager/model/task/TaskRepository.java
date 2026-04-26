package task.manager.task_manager.model.task;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByProjectId(UUID projectId);

    List<Task> findAll(Specification<Task> specification);

    @Query("SELECT t FROM Task t JOIN t.project p WHERE p.user.id = :userId")
    List<Task> findAllByUserId(@Param("userId") String userId);
}
