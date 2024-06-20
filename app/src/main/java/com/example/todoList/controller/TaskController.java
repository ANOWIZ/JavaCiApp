package com.example.todoList.controller;

import com.example.todoList.entity.Category;
import com.example.todoList.entity.Task;
import com.example.todoList.entity.User;
import com.example.todoList.repository.CategoryRepository;
import com.example.todoList.repository.TaskRepository;
import com.example.todoList.repository.UserRepository;
import com.example.todoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/tasks")
public class TaskController {


    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public @ResponseBody Iterable<Task> getAll() {
        return taskRepository.findAll();
    }

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<HttpStatus> addItem(@RequestParam String name,
                                                            @RequestParam String[] categories,
                                                            @RequestParam String description) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) authentication.getPrincipal();
        var categoryList = new ArrayList<Category>();
        for (String e : categories) {
            var optionalCategory = categoryRepository.findByName(e);
            if (optionalCategory.isPresent()) {
                categoryList.add(optionalCategory.get());
            } else {
                var c = new Category(e);
                categoryRepository.save(c);
                categoryList.add(c);
            }
        }
        taskRepository.save(new Task(categoryList, name, description, user));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
