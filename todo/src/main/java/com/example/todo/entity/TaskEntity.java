package com.example.todo.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * An entity class that represents a single task.
 */
@Entity
@Data
public class TaskEntity {

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
    private Boolean taskCompleted;
}
