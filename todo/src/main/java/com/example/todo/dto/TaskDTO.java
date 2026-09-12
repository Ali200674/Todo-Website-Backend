package com.example.todo.dto;

import com.example.todo.entity.TaskPriorityEnum;

import java.time.LocalDate;

public record TaskDTO(String taskName, String taskDescription, TaskPriorityEnum priorityType, Boolean taskCompleted, LocalDate taskDueDate) { }
