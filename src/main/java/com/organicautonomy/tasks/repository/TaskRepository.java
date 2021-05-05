package com.organicautonomy.tasks.repository;

import com.organicautonomy.tasks.domain.Task;
import com.organicautonomy.tasks.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByDueDate(LocalDateTime dueDate);
    List<Task> findTasksBySubmissionDate(LocalDate date);
    List<Task> findTasksByStatus(TaskStatus status);
}
