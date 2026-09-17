package com.example.todo.repo;

import com.example.todo.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * A repository that gives us CRUD methods and database manipulation
 */
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByTaskName(String taskName);

}
