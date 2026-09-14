package com.example.todo.controller;


import com.example.todo.dto.TaskDTO;
import com.example.todo.entity.TaskEntity;
import com.example.todo.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A class that is a RestController.
 */
@Tag(name = "Task Controller", description = "Controller for creating, modifying, or getting existing tasks")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {
    
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    @Operation(summary = "Gets all of the tasks from the database")
    public List<TaskEntity> getAllTasks() {
        return taskService.getAllTasks();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/task")
    @Operation(summary = "Saves a task into the database")
    public TaskEntity saveOneTask(@RequestBody @Valid TaskDTO taskEntity) {
        return taskService.saveOneTask(taskEntity);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/task/{taskId}")
    @Operation(summary = "Removes a task fro database based on it's id")
    public void deleteOneTask(@PathVariable Long taskId) {
        taskService.removeOneTask(taskId);
    }

    
    @PatchMapping("/task/{taskId}")
    @Operation(summary = "Updates a task completion only.")
    public void updateTask(@PathVariable Long taskId, @RequestBody @Valid TaskDTO taskEntity) {
        taskService.updateTask(taskId, taskEntity);
    }
}
