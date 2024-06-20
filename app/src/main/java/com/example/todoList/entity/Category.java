package com.example.todoList.entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {

    }
}
