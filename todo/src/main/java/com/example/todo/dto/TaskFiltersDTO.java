package com.example.todo.dto;

import com.example.todo.entity.TaskPriorityEnum;
import com.example.todo.entity.TaskStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * A record DTO used to filter through the tasks in the database.
 *
 * @param taskPriorityEnums A list of enums
 * @param taskStatuses A list of statuses
 * @param taskDueDate A date that represents the due date of a task
 */
public record TaskFiltersDTO(List<TaskPriorityEnum> taskPriorityEnums, List<TaskStatus> taskStatuses, LocalDate taskDueDate) {}
