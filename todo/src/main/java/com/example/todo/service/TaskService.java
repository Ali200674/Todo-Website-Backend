package com.example.todo.service;

import com.example.todo.entity.TaskEntity;
import com.example.todo.repo.TaskRepo;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.config.Task;
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

    public List<TaskEntity> getAllTasks()
    {
        return taskRepo.findAll();
    }

    public TaskEntity saveOneTask(TaskEntity taskEntity) {

        return taskRepo.save(taskEntity);
    }


    public void removeOneTask(Long id) {

        // Try to find the task in the database, if now found, throw a NoSuchElementException
        TaskEntity taskEntity = taskRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Cannot find task from given id"));

        // Delete that task entity
        taskRepo.delete(taskEntity);
    }

    public void updateTask(Long id, TaskEntity taskEntity) {
        TaskEntity taskEntity1 = taskRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Cannot find task from given id"));

        // Assuming we did find a task
        taskEntity1.setTaskCompleted(taskEntity.getTaskCompleted());

        taskRepo.save(taskEntity1);
    }
}
