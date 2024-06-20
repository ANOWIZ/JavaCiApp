package com.example.todoList.controller;

import com.example.todoList.entity.User;
import com.example.todoList.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller("/")
public class IndexController {
    @Autowired
    private TaskRepository taskRepository;
    @GetMapping("")
    public String index(Model model) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) authentication.getPrincipal();
        var tasks = taskRepository.findByUser(user).get();
        model.addAttribute("tasks", tasks);

        return "index";
    }
}
