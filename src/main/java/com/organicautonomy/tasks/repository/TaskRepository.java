package com.organicautonomy.tasks.repository;

import com.organicautonomy.tasks.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByDueDate(LocalDate dueDate);
    List<Task> findTasksBySubmissionDate(LocalDate date);
}
