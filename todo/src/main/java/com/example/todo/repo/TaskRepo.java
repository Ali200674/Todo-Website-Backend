package com.example.todo.repo;

import com.example.todo.entity.TaskEntity;
import com.example.todo.entity.TaskPriorityEnum;
import com.example.todo.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * A repository that gives us CRUD methods and database manipulation
 */
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {

    /**
     * Method that returns tasks based on the task name using pagination
     *
     * @param taskName The name of the task we will query with
     * @param pageable pagination information
     * @return A page containing tasks that matches the specific name.
     */
    Page<TaskEntity> findByTaskNameContaining(String taskName, Pageable pageable);

    /**
     * Method that returns tasks base on the filters passed in using pagination
     * Passing in a null value for any of the values will exclude it from the query.
     *
     * @param taskPriorityList A list of priorities
     * @param taskStatuses A list of statuses
     * @param taskDueDate A date object that represents the due date of a task
     * @return A page containing tasks based on the filters passed
     */
    @Query("""
    SELECT t FROM TaskEntity t
    WHERE (CAST(:taskDueDate AS localdate) IS NULL AND :priorityList IS NULL AND :taskStatuses IS NULL) OR(
        (:priorityList IS NULL OR t.priorityType IN :priorityList)
        AND (:taskStatuses IS NULL OR t.taskStatus IN :taskStatuses)
        AND ((CAST(:taskDueDate AS localdate) IS NULL AND t.taskDueDate IS NULL) OR (CAST(:taskDueDate AS localdate) IS NOT NULL AND t.taskDueDate = :taskDueDate)))
 """)
    Page<TaskEntity> findByTaskFilters(@Param("priorityList")List<TaskPriorityEnum> taskPriorityList,
                                       @Param("taskStatuses")List<TaskStatus> taskStatuses, @Param("taskDueDate")LocalDate taskDueDate, Pageable pageable);



}
