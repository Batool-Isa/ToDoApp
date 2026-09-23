package com.ga.ToDoApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.security.PrivateKey;
import java.time.LocalDate;

@Data
@Entity
@Table
public class Item {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private LocalDate dueDate;

    @Column
    private Long categoryId;
}
