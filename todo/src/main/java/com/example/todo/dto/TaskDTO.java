package com.example.todo.dto;

import com.example.todo.entity.TaskPriorityEnum;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TaskDTO(@NotBlank String taskName, String taskDescription, TaskPriorityEnum priorityType, Boolean taskCompleted, LocalDate taskDueDate) { }
