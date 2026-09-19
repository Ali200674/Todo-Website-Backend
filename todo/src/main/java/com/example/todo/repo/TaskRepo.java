package com.example.todo.repo;

import com.example.todo.entity.TaskEntity;
import com.example.todo.entity.TaskPriorityEnum;
import com.example.todo.entity.TaskStatus;
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
     * Method that returns a task by its name
     *
     * @param taskName The name of the task we will query with
     * @return A list of all the tasks that equals the taskName
     */
    List<TaskEntity> findByTaskName(String taskName);


    /**
     * Method that gets all tasks based on the filters passed through.
     * Passing in a null with exclude the filter.
     *
     * @param taskPriorityList A list of priorities
     * @param taskStatuses A list of statuses
     * @param taskDueDate A date object that represents the due date of a task
     * @return A list of all the tasks that equal all the filters given
     */
    @Query("""
    SELECT t FROM TaskEntity t
    WHERE (:priorityList IS NULL OR t.priorityType IN :priorityList)
     AND (:taskStatuses IS NULL OR t.taskStatus IN :taskStatuses)
      AND (:taskDueDate IS NULL OR t.taskDueDate = :taskDueDate)
 """)
    List<TaskEntity> findByTaskFilters(@Param("priorityList")List<TaskPriorityEnum> taskPriorityList,
                                       @Param("taskStatuses")List<TaskStatus> taskStatuses, @Param("taskDueDate")LocalDate taskDueDate);

}
