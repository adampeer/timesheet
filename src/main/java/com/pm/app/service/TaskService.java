package com.pm.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pm.app.entity.Task;
import com.pm.app.repository.TaskRepository;

@Service
public class TaskService implements TaskServiceInterface {

  private TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public Task addTask(Task task) {
    task.getProject().calculateTotalHours();
    return taskRepository.save(task);
  }

  @Override
  public void deleteTaskById(Long id) {
    taskRepository.deleteById(id);
  }

  @Override
  public void updateTaskById(Long id, Task task) {
    taskRepository.save(task);
  }

  @Override
  public Task getTaskById(Long id) {
    Optional<Task> task = taskRepository.findById(id);
    if (task.isPresent()) {
      return task.get();
    }
    return null;
  }

  @Override
  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

}
