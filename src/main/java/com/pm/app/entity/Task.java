package com.pm.app.entity;

import com.pm.app.listener.TaskListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
@EntityListeners(TaskListener.class)
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "task_id")
  private Long taskId;

  private String taskName;

  private String taskDescription;

  private float totalHours;

  private float remainingHours;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "project_id")
  private Project project;

  public Task() {

  }

  public Task(Long taskId, String taskName, String taskDescription, float totalHours, float remainingHours,
      Project project) {
    this.taskId = taskId;
    this.taskName = taskName;
    this.taskDescription = taskDescription;
    this.totalHours = totalHours;
    this.remainingHours = remainingHours;
    this.project = project;
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getTaskDescription() {
    return taskDescription;
  }

  public void setTaskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
  }

  public float getTotalHours() {
    return totalHours;
  }

  public void setTotalHours(float totalHours) {
    this.totalHours = totalHours;
  }

  public float getRemainingHours() {
    return remainingHours;
  }

  public void setRemainingHours(float remainingHours) {
    this.remainingHours = remainingHours;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

}