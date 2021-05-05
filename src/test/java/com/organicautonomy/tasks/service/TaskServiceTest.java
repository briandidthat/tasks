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

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    private final Task TO_SAVE = new Task(0,"do the dishes", LocalDate.of(2021,4,5),
            LocalDate.of(2021,4,6), Time.valueOf("3:30:00"), TaskStatus.OPEN);
    private final Task TASK_1 = new Task(1,"do the dishes", LocalDate.of(2021,4,5),
            LocalDate.of(2021,4,6), Time.valueOf("3:30:00"), TaskStatus.OPEN);
    private final Task TASK_2 = new Task(0,"do the dishes", LocalDate.of(2021,4,3),
            LocalDate.of(2021,4,7), Time.valueOf("5:30:00"), TaskStatus.OPEN);
    private final Task TASK_3 = new Task(0,"do the dishes", LocalDate.of(2021,4,3),
            LocalDate.of(2021,4,7), Time.valueOf("1:00:00"), TaskStatus.OPEN);

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