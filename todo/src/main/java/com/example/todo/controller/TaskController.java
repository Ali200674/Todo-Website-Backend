package com.example.todo.controller;


import com.example.todo.dto.TaskDTO;
import com.example.todo.dto.TaskFiltersDTO;
import com.example.todo.entity.TaskEntity;
import com.example.todo.entity.TaskPriorityEnum;
import com.example.todo.entity.TaskStatus;
import com.example.todo.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public List<TaskEntity> getAllTasks(@RequestParam(required = false) String search) {
        return !StringUtils.hasText(search) ? taskService.getAllTasks() : taskService.getAllTasksBasedOnSearchResult(search);
    }

    @GetMapping("/tasks/filters")
    public List<TaskEntity> getAllTasksBasedOnFilters(@RequestParam(required = false) List<TaskPriorityEnum> taskPriorityList, @RequestParam(required = false)
    List<TaskStatus> taskStatuses, @RequestParam(required = false)LocalDate taskDueDate) {
        TaskFiltersDTO taskFiltersDTO = new TaskFiltersDTO(taskPriorityList, taskStatuses, taskDueDate);
        System.out.println(taskFiltersDTO);
        return taskService.getAllTasksBasedOnFilters(taskFiltersDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/task")
    @Operation(summary = "Saves a task into the database")
    public TaskEntity saveOneTask(@RequestBody @Valid TaskDTO taskEntity) {
        return taskService.saveOneTask(taskEntity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/task/{taskId}")
    @Operation(summary = "Removes a task from database based on it's id")
    public void deleteOneTask(@PathVariable Long taskId) {
        taskService.removeOneTask(taskId);
    }

    
    @PatchMapping("/task/{taskId}")
    @Operation(summary = "Updates a task completion only.")
    public void updateTaskCompletion(@PathVariable Long taskId, @RequestBody TaskStatus taskCompleted) {
        taskService.updateTaskCompletion(taskId, taskCompleted);
    }

    @PutMapping("/task/{taskId}")
    @Operation(summary = "Updates or replaces the whole task")
    public void updateEntireTask(@PathVariable Long taskId, @RequestBody @Valid TaskDTO taskDTO) {
         taskService.updateEntireTask(taskId, taskDTO);
    }
}
