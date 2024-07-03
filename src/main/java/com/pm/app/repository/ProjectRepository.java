package com.pm.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pm.app.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
