package com.example.todoList.dto;

import com.example.todoList.entity.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskDTO {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    private Category category;

    private boolean isComplete;
}
