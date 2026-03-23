package task.manager.task_manager.model.task;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class TaskSpecification {

    public static Specification<Task> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(name) ? criteriaBuilder.like(root.get("name"), "%" + name + "%") : null;
    }

    public static Specification<Task> hasStatus(TaskStatus status) {
        return (root, query, criteriaBuilder) ->
                status != null ? criteriaBuilder.equal(root.get("status"), status) : null;
    }

    public static Specification<Task> hasProjectId(java.util.UUID projectId) {
        return (root, query, criteriaBuilder) ->
                projectId != null ? criteriaBuilder.equal(root.get("project").get("id"), projectId) : null;
    }

    public static Specification<Task> hasStartDate(LocalDate startDate) {
        return (root, query, criteriaBuilder) ->
                startDate != null ? criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate) : null;
    }

    public static Specification<Task> hasEndDate(LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                endDate != null ? criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate) : null;
    }
}
