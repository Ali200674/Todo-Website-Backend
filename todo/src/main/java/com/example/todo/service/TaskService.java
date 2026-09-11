package com.example.todo.service;

import com.example.todo.entity.TaskEntity;
import com.example.todo.repo.TaskRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
        taskRepo.deleteById(id);
    }

    public void updateTask(Long id, TaskEntity taskEntity) {
            taskRepo.findById(id).ifPresent((entity) -> {
                entity.setTaskCompleted(taskEntity.getTaskCompleted());
                taskRepo.save(entity);
                }
            );
    }
}
