package com.example.todo.controller;


import com.example.todo.entity.TaskEntity;
import com.example.todo.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<TaskEntity> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/task")
    public TaskEntity saveOneTask(@RequestBody TaskEntity taskEntity) {

        taskService.getAllTasks().forEach(System.out::println);

        return taskService.saveOneTask(taskEntity);
    }

    @DeleteMapping("/task/{taskId}")
    public void deleteOneTask(@PathVariable Long taskId) {
        taskService.removeOneTask(taskId);
    }

    @PatchMapping("/task/{taskId}")
    public void updateTask(@PathVariable Long taskId, @RequestBody TaskEntity taskEntity) {
        taskService.updateTask(taskId, taskEntity);
    }
}
