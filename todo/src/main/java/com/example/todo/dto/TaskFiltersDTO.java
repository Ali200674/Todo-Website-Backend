package com.example.todo.dto;

import com.example.todo.entity.TaskPriorityEnum;
import com.example.todo.entity.TaskStatus;

import java.time.LocalDate;
import java.util.List;

public record TaskFiltersDTO(List<TaskPriorityEnum> taskPriorityEnums, List<TaskStatus> taskStatuses, LocalDate taskDueDate) {}
