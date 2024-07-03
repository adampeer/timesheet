package com.pm.app.service;

import java.util.List;

import com.pm.app.entity.Project;

public interface ProjectServiceInterface {

  // Add Project
  public void addProject(Project project);

  // Delete Project by id
  public void deleteProjectById(Long id);

  // Update Project by id
  public void updateProjectById(Long id, Project project);

  // Get Project by id
  public Project getProjectById(Long id);

  // Get All Projects
  public List<Project> getAllProjects();

}
