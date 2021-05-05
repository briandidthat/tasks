package com.organicautonomy.tasks.service;

import com.organicautonomy.tasks.domain.Task;
import com.organicautonomy.tasks.domain.TaskStatus;
import com.organicautonomy.tasks.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    private final Task TO_SAVE = new Task(0,"do the dishes", LocalDate.of(2021,4,2),
            LocalDateTime.of(LocalDate.of(2021, 4, 4), LocalTime.of(3,30)), TaskStatus.OPEN);
    private final Task TASK_1 = new Task(1,"do the dishes", LocalDate.of(2021,4,2),
            LocalDateTime.of(LocalDate.of(2021, 4, 4), LocalTime.of(3,30)), TaskStatus.OPEN);
    private final Task TASK_2 = new Task(2,"walk the dog", LocalDate.of(2021,4,3),
            LocalDateTime.of(LocalDate.of(2021, 4, 5), LocalTime.of(1,30)), TaskStatus.OPEN);
    private final Task TASK_3 = new Task(3,"clean the house", LocalDate.of(2021,4,3),
            LocalDateTime.of(LocalDate.of(2021, 4, 7), LocalTime.of(4,30)), TaskStatus.OPEN);

    @MockBean
    private TaskRepository repository;
    @Autowired
    private TaskService service;


    @BeforeEach
    void setUp() {
    }

    @Test
    void findTaskById() {
    }

    @Test
    void findAllTasks() {
    }

    @Test
    void findTasksBySubmissionDate() {
    }

    @Test
    void findTaskByDueDate() {
    }

    @Test
    void deleteTask() {
    }

    @Test
    void updateTask() {
    }
}