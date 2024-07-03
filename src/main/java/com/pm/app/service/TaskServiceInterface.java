package com.pm.app.service;

import java.util.List;

import com.pm.app.entity.Task;

public interface TaskServiceInterface {

  // Add Task
  public Task addTask(Task task);

  // Delete Task by id
  public void deleteTaskById(Long id);

  // Update Task by id
  public void updateTaskById(Long id, Task task);

  // Get Task by id
  public Task getTaskById(Long id);

  // Get All Tasks
  public List<Task> getAllTasks();

}
