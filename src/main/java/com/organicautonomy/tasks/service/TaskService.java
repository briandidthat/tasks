package com.organicautonomy.tasks.service;

import com.organicautonomy.tasks.domain.Task;
import com.organicautonomy.tasks.domain.TaskStatus;
import com.organicautonomy.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public Task saveTask(Task task) {
        return repository.save(task);
    }

    public Task findTaskById(Long id) {
        Optional<Task> task = repository.findById(id);

        if (task.isEmpty()) {
            return null;
        }

        return task.get();
    }

    public List<Task> findAllTasks() {
        List<Task> tasks = repository.findAll();

        if (tasks.size() == 0) {
            return null;
        }

        return tasks;
    }

    public List<Task> findTasksBySubmissionDate(LocalDate submissionDate) {
        List<Task> tasks = repository.findTasksBySubmissionDate(submissionDate);

        if (tasks.size() == 0) {
            return null;
        }

        return tasks;
    }

    public List<Task> findTasksByDueDate(LocalDateTime dueDate) {
        List<Task> tasks = repository.findTasksByDueDate(dueDate);

        if (tasks.size() == 0) {
            return null;
        }

        return tasks;
    }

    public List<Task> findTasksByStatus(TaskStatus status) {
        List<Task> tasks = repository.findTasksByStatus(status);

        if (tasks.size() == 0) {
            return null;
        }

        return tasks;
    }

    public boolean deleteTask(Long id) {
        Optional<Task> task = repository.findById(id);

        if (task.isPresent()) {
            // the task exists in the db and can be deleted
            repository.delete(task.get());
            return true;
        }

        return false;
    }

    public boolean updateTask(Long id, Task toUpdate) {
        Optional<Task> task = repository.findById(id);

        if (task.isPresent()) {
            // the task exists and can be updated
            repository.save(toUpdate);
            return true;
        }

        return false;
    }
}
