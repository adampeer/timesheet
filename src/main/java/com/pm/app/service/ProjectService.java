package com.pm.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pm.app.entity.Project;
import com.pm.app.repository.ProjectRepository;

@Service
public class ProjectService implements ProjectServiceInterface {

  private ProjectRepository projectRepository;

  public ProjectService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void addProject(Project project) {
    project.calculateTotalHours();
    projectRepository.save(project);
  }

  @Override
  public void deleteProjectById(Long id) {
    projectRepository.deleteById(id);
  }

  @Override
  public void updateProjectById(Long id, Project project) {
    projectRepository.save(project);
  }

  @Override
  public Project getProjectById(Long id) {
    Optional<Project> project = projectRepository.findById(id);
    if (project.isPresent()) {
      return project.get();
    }
    return null;
  }

  @Override
  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }

}
