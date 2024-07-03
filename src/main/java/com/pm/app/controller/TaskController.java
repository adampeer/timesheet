package com.pm.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.app.entity.Task;
import com.pm.app.service.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  // Get all tasks
  @RequestMapping("/all")
  public ResponseEntity<Iterable<Task>> getAllTasks() {
    List<Task> tasks = taskService.getAllTasks();
    return ResponseEntity.ok(tasks);
  }

  // Add new task
  @RequestMapping("/add")
  public ResponseEntity<Task> addTask(@RequestBody Task task) {
    Task addedTask = taskService.addTask(task);
    return ResponseEntity.ok(addedTask);
  }

}
