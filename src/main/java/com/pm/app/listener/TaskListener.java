package com.pm.app.listener;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pm.app.entity.Project;
import com.pm.app.entity.Task;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

@Service
public class TaskListener {

  @PersistenceContext
  private EntityManager entityManager;

  @PostPersist
  @PostUpdate
  @Transactional
  public void onTaskChange(Task task) {
    // Update the Project's totalHours
    Project project = task.getProject();
    project.setTotalHours(calculateTotalHours(project));
  }

  @PreRemove
  public void onTaskRemove(Task task) {
    // Update the Project's totalHours
    Project project = task.getProject();
    // Calculate the total hours for the project, excluding the removed task
    project.setTotalHours(calculateTotalHours(project, task));
  }

  private float calculateTotalHours(Project project) {
    // Calculate the sum of totalHours from all tasks in the project
    float totalHours = 0;
    for (Task task : project.getTasks()) {
      totalHours += task.getTotalHours();
    }
    return totalHours;
  }

  private float calculateTotalHours(Project project, Task taskToRemove) {
    // Calculate the sum of totalHours from all tasks in the project, excluding the
    // taskToRemove
    float totalHours = 0;
    for (Task task : project.getTasks()) {
      if (task != taskToRemove) {
        totalHours += task.getTotalHours();
      }
    }
    return totalHours;
  }
}