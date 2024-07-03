package com.pm.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pm.app.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
