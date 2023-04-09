package com.example.zadanie25_1;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByCompletionTimeIsNotNull();

    List<Task> findAllByOrderByCompletionTimeDesc();

    List<Task> findTasksByCompletionTimeIsNull();
}
