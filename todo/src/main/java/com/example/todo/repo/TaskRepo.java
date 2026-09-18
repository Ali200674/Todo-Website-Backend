package com.example.todo.repo;

import com.example.todo.dto.TaskFiltersDTO;
import com.example.todo.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * A repository that gives us CRUD methods and database manipulation
 */
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByTaskName(String taskName);

    @Query("""
    SELECT t FROM TaskEntity t
    WHERE (:#{#filters.taskPriorityEnums.isEmpty()} = true OR t.priorityType IN :#{#filters.taskPriorityEnums})
    AND (:#{#filters.taskStatuses.isEmpty()} = true OR t.taskStatus IN :#{#filters.taskStatuses}) AND
     (:#{#filters.taskDueDate} IS NULL OR t.taskDueDate = :#{#filters.taskDueDate})
""")
    List<TaskEntity> findByTaskFilters(@Param("filters") TaskFiltersDTO taskFiltersDTO);

}
