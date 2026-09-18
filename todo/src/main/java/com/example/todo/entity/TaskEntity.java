package com.example.todo.entity;

import com.example.todo.dto.TaskDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * An entity class that represents a single task.
 */
@Entity
@Data
public class TaskEntity {

    public TaskEntity() {}

    public TaskEntity(TaskDTO taskDTO) {
        this.taskName = taskDTO.taskName();
        this.taskDescription = taskDTO.taskDescription();
        this.priorityType = taskDTO.priorityType();
        this.taskStatus = taskDTO.taskStatus();
        this.taskDueDate = taskDTO.taskDueDate();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String taskName;

    @Column
    private String taskDescription;

    @Column
    private TaskPriorityEnum priorityType;

    @Column
    private TaskStatus taskStatus;

    @Column
    private LocalDate taskDueDate;
}
