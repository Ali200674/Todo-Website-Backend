package com.example.todo.service;

import com.example.todo.dto.TaskDTO;
import com.example.todo.entity.TaskEntity;
import com.example.todo.entity.TaskPriorityEnum;
import com.example.todo.entity.TaskStatus;
import com.example.todo.repo.TaskRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A service class that has all the business logic
 *
 * @author Ali Izoyev
 * @version 1.0x
 */
@Service
@Transactional
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    /**
     * Method to return every single task in the database
     *
     * @return A list of all tasks in the database
     */
    public List<TaskEntity> getAllTasks()
    {
        return taskRepo.findAll();
    }

    /**
     * Method to save a task into the database
     *
     * @param taskDTO The task to be saved into the database
     * @return A TaskEntity with all the task information, including the task id
     */
    public TaskEntity saveOneTask(TaskDTO taskDTO) { return taskRepo.save(new TaskEntity(taskDTO)); }

    /**
     * Method to delete a task from the database using an id
     *
     * @throws NoSuchElementException Thrown if the task cannot be found in the database
     * @param id The id used to find the task in the database
     */
    public void removeOneTask(Long id) {
        // Try to find the task in the database, if not found, throw a NoSuchElementException
        TaskEntity taskEntity = taskRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Cannot find task from given id"));

        // Delete that task entity
        taskRepo.delete(taskEntity);
    }

    /**
     * Method used to update the status of the task.
     *
     * @throws NoSuchElementException Thrown if the task cannot be found in the database
     * @param id The id used to find the task in the database
     * @param taskCompletionStatus An enum that has information about a task status
     */
    public void updateTaskCompletion(Long id, TaskStatus taskCompletionStatus) {

        // Try to find the task in database. Throw exception if not found
        TaskEntity taskEntity = taskRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Cannot find task from given id"));

        // Assuming we did find a task
        taskEntity.setTaskStatus(taskCompletionStatus);
    }

    /**
     * Method to update the whole task
     *
     * @param id The id used to find the task in the database
     * @param taskDTO A DTO used to replace all the tasks fields
     */
    public void updateEntireTask(Long id, TaskDTO taskDTO) {

        // Try to find the task to update in the database
        TaskEntity taskEntity = taskRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Task was not found in database with given id"));

        // Assuming we have the task
        taskEntity.setTaskName(taskDTO.taskName());
        taskEntity.setTaskDescription(taskDTO.taskDescription());
        taskEntity.setTaskStatus(taskDTO.taskStatus());
        taskEntity.setTaskDueDate(taskDTO.taskDueDate());

        // Save that task entity back
        taskRepo.save(taskEntity);
    }

    /**
     * Method to get all tasks based on the search result sent.
     * Does not return tasks that includes the search result.
     * The task name (what it is being compared against) has to match the search result exactly.
     *
     * @param search The search result
     * @return A list of tasks that has the title exactly match the search result
     */
    public List<TaskEntity> getAllTasksBasedOnSearchResult(String search) { return taskRepo.findByTaskName(search); }


    /**
     * Method to get a list of tasks based on variables (filters)
     *
     * @param taskPriorityList A list of priority types
     * @param taskStatuses A list of status types
     * @param taskDueDate A date on when the task is due
     * @return A list of all the tasks based on the param variables
     */
    public List<TaskEntity> getAllTasksBasedOnFilters(List<TaskPriorityEnum> taskPriorityList,
    List<TaskStatus> taskStatuses, LocalDate taskDueDate) {

        // If the lists are empty, turn them into null. So that they will not be used in filtering
        if (CollectionUtils.isEmpty(taskPriorityList)) { taskPriorityList = null; }
        if (CollectionUtils.isEmpty(taskStatuses)) { taskStatuses = null; }

        return taskRepo.findByTaskFilters(taskPriorityList, taskStatuses, taskDueDate);
    }
}
