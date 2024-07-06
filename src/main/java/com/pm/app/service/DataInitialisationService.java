package com.pm.app.service;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.pm.app.entity.Project;
import com.pm.app.entity.Role;
import com.pm.app.entity.Task;
import com.pm.app.entity.User;
import com.pm.app.utils.StringUtils;

import jakarta.transaction.Transactional;

@Service
public class DataInitialisationService {

  private UserServiceInterface userServiceInterface;

  private TaskServiceInterface taskServiceInterface;

  private ProjectServiceInterface projectServiceInterface;

  public DataInitialisationService(UserServiceInterface userServiceInterface, TaskServiceInterface taskServiceInterface,
      ProjectServiceInterface projectServiceInterface) {
    this.userServiceInterface = userServiceInterface;
    this.taskServiceInterface = taskServiceInterface;
    this.projectServiceInterface = projectServiceInterface;
  }

  @Transactional
  public void initialiseData() {

    // Create and add projects
    Project project1 = createAndAddProject(1L, "Project 1", "Project 1 description", LocalDate.of(2024, 10, 10));
    Project project2 = createAndAddProject(2L, "Project 2", "Project 2 description", LocalDate.of(2024, 12, 10));

    // Create and add tasks
    createAndAddTask(1L, "Task 1", "Task 1 description", 8.0f, project1);
    createAndAddTask(2L, "Task 2", "Task 2 description", 8.0f, project1);
    createAndAddTask(3L, "Task 3", "Task 3 description", 8.0f, project1);

    createAndAddTask(4L, "Task 4", "Task 4 description", 8.0f, project2);
    createAndAddTask(5L, "Task 5", "Task 5 description", 8.0f, project2);
    createAndAddTask(6L, "Task 6", "Task 6 description", 8.0f, project2);

    // Create and add users
    createAndAddUser(1L, "John", "Doe", "john.doe@email.com", "password", StringUtils.ADMIN, project1, project2);
    createAndAddUser(2L, "Jim", "Doe", "jim.doe@email.com", "password", StringUtils.USER, project2);
  }

  // Helper method to create and add a project
  private Project createAndAddProject(Long id, String name, String description, LocalDate deadline) {
    Project project = new Project();
    project.setProjectId(id);
    project.setProjectName(name);
    project.setProjectDescription(description);
    project.setDeadline(deadline);
    projectServiceInterface.addProject(project);
    return project;
  }

  // Helper method to create and add a task
  private Task createAndAddTask(Long id, String name, String description, float totalHours, Project project) {
    Task task = new Task();
    task.setTaskId(id);
    task.setTaskName(name);
    task.setTaskDescription(description);
    task.setTotalHours(totalHours);
    task.setProject(project);
    taskServiceInterface.addTask(task);
    return task;
  }

  // Helper method to create and add a user
  private User createAndAddUser(Long id, String firstName, String lastName, String email, String password,
      String role, Project... projects) {
    User user = new User();
    user.setUserId(id);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setPassword(password);
    user.setAuthorities(Stream.of(new Role(role)).collect(Collectors.toSet()));
    user.setProjects(Stream.of(projects).collect(Collectors.toSet()));
    userServiceInterface.save(user);
    return user;
  }
}