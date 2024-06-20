package com.example.todoList.repository;

import com.example.todoList.entity.Task;
import com.example.todoList.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {


    @Override
    Optional<Task> findById(Long id);


    Optional<List<Task>> findByUser(User user);

    @Override
    List<Task> findAll();

}
