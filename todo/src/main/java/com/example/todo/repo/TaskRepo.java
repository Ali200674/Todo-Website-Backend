package com.example.todo.repo;

import com.example.todo.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repository that gives us CRUD methods and database manipulation
 */
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
}
