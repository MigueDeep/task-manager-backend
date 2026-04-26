package task.manager.task_manager.model.project;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class ProjectSpecification {

    public static Specification<Project> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(name) ? criteriaBuilder.like(root.get("name"), "%" + name + "%") : null;
    }

    public static Specification<Project> hasStatus(String status) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(status) ? criteriaBuilder.equal(root.get("status"), status) : null;
    }

    public static Specification<Project> hasStartDate(LocalDate startDate) {
        return (root, query, criteriaBuilder) ->
                startDate != null ? criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate) : null;
    }

    public static Specification<Project> hasEndDate(LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                endDate != null ? criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate) : null;
    }

    public static Specification<Project> hasUserId(String userId) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(userId) ? criteriaBuilder.equal(root.get("user").get("id"), userId) : null;
    }
}