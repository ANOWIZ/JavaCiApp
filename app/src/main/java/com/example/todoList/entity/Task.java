package com.example.todoList.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String name;
    private String description;
    @ManyToMany
    private List<Category> categories;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private boolean isComplete;

    public Task(List<Category> categories, String name, String description, User user) {
        this.categories = categories;
        this.name = name;
        this.description = description;
        this.isComplete = false;
        this.user = user;
    }

    public Task() {

    }
}
