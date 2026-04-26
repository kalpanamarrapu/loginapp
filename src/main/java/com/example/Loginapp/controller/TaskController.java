package com.example.Loginapp.controller;

import com.example.Loginapp.model.Task;
import com.example.Loginapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // Add task
    @PostMapping
    public Task addTask(@RequestBody Task task, Authentication auth) {
        task.setUsername(auth.getName());
        return taskRepository.save(task);
    }

    // Get tasks
    @GetMapping
    public List<Task> getTasks(Authentication auth) {
        return taskRepository.findByUsername(auth.getName());
    }

    // DELETE task 🔥
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        System.out.println("DELETE HIT: " + id); // debug
        taskRepository.deleteById(id);
    }
}