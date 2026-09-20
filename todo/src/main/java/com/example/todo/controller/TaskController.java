package com.example.todo.controller;


import com.example.todo.dto.TaskDTO;
import com.example.todo.entity.TaskEntity;
import com.example.todo.entity.TaskPriorityEnum;
import com.example.todo.entity.TaskStatus;
import com.example.todo.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Handles all HTTP requests related to tasks
 *
 * @author Ali Izoyev
 * @version 1.0x
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

    /**
     * Method to return a Page of TaskEntities with or without a search param
     *
     *
     * @param taskTitleName Used to find tasks based on it's name
     * @return A list of either every task in the database normally or by the search result
     */
    @GetMapping("/tasks")
    @Operation(summary = "Gets all of the tasks from the database")
    public Page<TaskEntity> getAllTasks(@RequestParam(name = "search",required = false) String taskTitleName) {
        // If the search param is empty, return all tasks, else return all tasks based on search result
        return !StringUtils.hasText(taskTitleName) ? taskService.getAllTasks() : taskService.getAllTasksBasedOnSearchResult(taskTitleName);
    }

    /**
     * Method to get a Page of TaskEntities based on the priority, status, and due date of task
     *
     * @param taskPriorityList A list of priorities
     * @param taskStatuses A list of statuses
     * @param taskDueDate The due date of the object
     * @return A list tasks based on the params
     */
    @GetMapping("/tasks/filters")
    @Operation(summary = "Retrieve a list of tasks based on the priority, status, and due date of the task")
    public Page<TaskEntity> getAllTasksBasedOnFilters(@RequestParam(required = false) List<TaskPriorityEnum> taskPriorityList, @RequestParam(required = false)
    List<TaskStatus> taskStatuses, @RequestParam(required = false)LocalDate taskDueDate) {
        return taskService.getAllTasksBasedOnFilters(taskPriorityList, taskStatuses, taskDueDate);
    }

    /**
     * Method to return a Page of TaskEntities based on the page
     *
     * @param page The page as an Integer
     * @return A Page of TaskEntities based on the page variable
     */
    @GetMapping("/tasks/page/{page}")
    @Operation()
    public Page<TaskEntity> getAllContentBasedOnPage(@PathVariable Integer page) {
        return taskService.getAllContentBasedOnPage(page);
    }

    /**
     * Method to create one task
     *
     * @param taskEntity The task to be saved into the database
     * @return The task that was saved into the database
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/task")
    @Operation(summary = "Saves a task into the database")
    public TaskEntity saveOneTask(@RequestBody @Valid TaskDTO taskEntity) {
        return taskService.saveOneTask(taskEntity);
    }

    /**
     * Method to delete a task from database
     *
     * @param taskId The id used to find the task to delete
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/task/{taskId}")
    @Operation(summary = "Removes a task from database based on it's id")
    public void deleteOneTask(@PathVariable Long taskId) {
        taskService.removeOneTask(taskId);
    }

    /**
     * Method to update the completion status of the task
     *
     * @param taskId The id used to find task in database
     * @param taskCompleted The status of the task to be updated to
     */
    @PatchMapping("/task/{taskId}")
    @Operation(summary = "Updates a task completion only.")
    public void updateTaskCompletion(@PathVariable Long taskId, @RequestBody TaskStatus taskCompleted) {
        taskService.updateTaskCompletion(taskId, taskCompleted);
    }

    /**
     * Method to update the whole task in database
     *
     * @param taskId The id used to find task in database
     * @param taskDTO The updated task used to update the old task in database
     */
    @PutMapping("/task/{taskId}")
    @Operation(summary = "Updates or replaces the whole task")
    public void updateEntireTask(@PathVariable Long taskId, @RequestBody @Valid TaskDTO taskDTO) {
         taskService.updateEntireTask(taskId, taskDTO);
    }
}
