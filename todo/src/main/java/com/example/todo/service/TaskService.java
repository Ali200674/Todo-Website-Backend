package com.example.todo.service;

import com.example.todo.dto.TaskDTO;
import com.example.todo.entity.TaskEntity;
import com.example.todo.repo.TaskRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;

/**
 * A service class that has all the business logic
 */
@Service
@Transactional
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    // Method to return all tasks
    public List<TaskEntity> getAllTasks()
    {
        return taskRepo.findAll();
    }

    // Method to save one task
    public TaskEntity saveOneTask(TaskDTO taskDTO) {

        TaskEntity taskEntity = new TaskEntity(taskDTO);

        return taskRepo.save(taskEntity);
    }

    // Method to remove a task from database by id
    public void removeOneTask(Long id) {
        // Try to find the task in the database, if not found, throw a NoSuchElementException
        TaskEntity taskEntity = taskRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Cannot find task from given id"));

        // Delete that task entity
        taskRepo.delete(taskEntity);
    }

    // This method is only used to update the taskCompleted variable.
    public void updateTask(Long id, TaskDTO taskDTO) {
        TaskEntity taskEntity = taskRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Cannot find task from given id"));

        // Assuming we did find a task
        taskEntity.setTaskCompleted(taskDTO.taskCompleted());

        taskRepo.save(taskEntity);
    }
}
