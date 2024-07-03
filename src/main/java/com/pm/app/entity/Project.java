package com.pm.app.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "projects")
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "project_id")
  private Long projectId;

  private String projectName;

  private String projectDescription;

  private float totalHours;

  private float remainingHours;

  private LocalDate deadline;

  @ManyToMany(mappedBy = "projects", cascade = CascadeType.ALL)
  private Set<User> users = new HashSet<>();

  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
  private Set<Task> tasks = new HashSet<>();

  public Project() {
  }

  public Project(Long projectId, String projectName, String projectDescription, float totalHours, float remainingHours,
      LocalDate deadline, Set<User> users, Set<Task> tasks) {
    this.projectId = projectId;
    this.projectName = projectName;
    this.projectDescription = projectDescription;
    this.totalHours = totalHours;
    this.remainingHours = remainingHours;
    this.deadline = deadline;
    this.users = users;
    this.tasks = tasks;
  }

  public Long getProjectId() {
    return projectId;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getProjectDescription() {
    return projectDescription;
  }

  public void setProjectDescription(String projectDescription) {
    this.projectDescription = projectDescription;
  }

  public float getTotalHours() {
    return totalHours;
  }

  public void setTotalHours(float totalHours) {
    this.totalHours = calculateTotalHours();
  }

  public float getRemainingHours() {
    return remainingHours;
  }

  public void setRemainingHours(float remainingHours) {
    this.remainingHours = remainingHours;
  }

  public LocalDate getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDate deadline) {
    this.deadline = deadline;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  public Set<Task> getTasks() {
    return tasks;
  }

  public void setTasks(Set<Task> tasks) {
    this.tasks = tasks;
  }

  public float calculateTotalHours() {
    return (float) tasks.stream().mapToDouble(Task::getTotalHours).sum();
  }

}
