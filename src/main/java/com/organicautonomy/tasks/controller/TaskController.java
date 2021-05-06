package com.organicautonomy.tasks.controller;

import com.organicautonomy.tasks.domain.Task;
import com.organicautonomy.tasks.exception.ResourceNotFoundException;
import com.organicautonomy.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody @Valid Task task) {
        return service.saveTask(task);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks() {
        List<Task> tasks = service.findAllTasks();

        if (tasks == null) throw new ResourceNotFoundException("There are no tasks.");

        return tasks;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskById(@PathVariable Long id) {
        Task task = service.findTaskById(id);

        if (task == null) throw new ResourceNotFoundException("There are no tasks with the id: " + id);

        return task;
    }

    @GetMapping("/submission-date/{submissionDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTasksBySubmissionDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate submissionDate) {
        List<Task> tasks = service.findTasksBySubmissionDate(submissionDate);

        if (tasks == null)
            throw new ResourceNotFoundException("There were no tasks submitted on: " + submissionDate.toString());

        return tasks;
    }

    @GetMapping("/due-date/{dueDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTasksByDueDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dueDate) {
        List<Task> tasks = service.findTasksByDueDate(dueDate);

        if (tasks == null) throw new ResourceNotFoundException("There are no tasks due on: " + dueDate.toString());

        return tasks;
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable Long id, @RequestBody @Valid Task task) {
        if (task.getId() != id) {
            throw new IllegalArgumentException("Path id must match object id.");
        }

        boolean updated = service.updateTask(id, task);

        if (!updated) throw new ResourceNotFoundException("There is no task associated with the id: " + id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        boolean deleted = service.deleteTask(id);

        if (!deleted) throw new ResourceNotFoundException("There is no task associated with the id: " + id);
    }
}
