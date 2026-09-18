package com.example.todo.dto;

import com.example.todo.entity.TaskPriorityEnum;
import com.example.todo.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TaskDTO(@NotBlank(message = "Task name cannot be blank!") String taskName, String taskDescription, @NotNull(message = "Priority type cannot be empty!") TaskPriorityEnum priorityType, TaskStatus taskStatus, LocalDate taskDueDate) { }
