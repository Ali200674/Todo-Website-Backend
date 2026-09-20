package com.example.todo.dto;

import com.example.todo.entity.TaskPriorityEnum;
import com.example.todo.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * A dto that represents a task besides having a unique id
 *
 * @param taskName Name of task
 * @param taskDescription Description of task
 * @param priorityType Priority type of task
 * @param taskStatus Status of task
 * @param taskDueDate Due date of a task
 *
 * @author Ali Izoyev
 * @version 1.0x
 */
public record TaskDTO(@NotBlank String taskName, String taskDescription, @NotNull TaskPriorityEnum priorityType, TaskStatus taskStatus, LocalDate taskDueDate) { }
