package com.example.zadanie25_1;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "INSERT INTO task(title, description, start_time, completion_time) VALUES (?, ?, ?, ?)", nativeQuery = true)
    @Modifying
    @Transactional
    void addTask(String title, String description, LocalDateTime startTime, LocalDateTime completionTime);
    List<Task> findTasksByCompletionTimeIsNotNull();
    List<Task> findAllByOrderByCompletionTimeDesc();
}
