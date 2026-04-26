package task.manager.task_manager.model.type;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TypeRepository extends JpaRepository<Type, UUID> {
    Optional<Type> findByName(String name);
    List<Type> findAllByUserId(String userId);
}
