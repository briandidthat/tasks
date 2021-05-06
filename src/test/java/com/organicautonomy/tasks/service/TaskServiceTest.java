package com.organicautonomy.tasks.service;

import com.organicautonomy.tasks.domain.Task;
import com.organicautonomy.tasks.domain.TaskStatus;
import com.organicautonomy.tasks.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
    void saveTask() {
        when(repository.save(TO_SAVE)).thenReturn(TASK_1);

        Task saved = service.saveTask(TO_SAVE);
        assertEquals(TASK_1, saved);
    }

    @Test
    void findTaskById() {
        when(repository.findById(1L)).thenReturn(Optional.of(TASK_1));

        Task task = service.findTaskById(TASK_1.getId());
        assertEquals(TASK_1, task);
    }

    @Test
    void findAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(TASK_1);
        tasks.add(TASK_2);
        tasks.add(TASK_3);

        when(repository.findAll()).thenReturn(tasks);

        List<Task> fromService = service.findAllTasks();
        assertEquals(3, fromService.size());
    }

    @Test
    void findTasksBySubmissionDate() {
        LocalDate date = LocalDate.of(2021,4,3);

        List<Task> tasks = new ArrayList<>();
        tasks.add(TASK_2);
        tasks.add(TASK_3);

        when(repository.findTasksBySubmissionDate(date)).thenReturn(tasks);

        List<Task> fromService = service.findTasksBySubmissionDate(date);
        assertEquals(2, fromService.size());
    }

    @Test
    void findTaskByDueDate() {
        LocalDateTime dueDate = LocalDateTime.of(LocalDate.of(2021, 4, 7), LocalTime.of(4,30));

        List<Task> tasks = new ArrayList<>();
        tasks.add(TASK_3);

        when(repository.findTasksByDueDate(dueDate)).thenReturn(tasks);

        List<Task> fromService = service.findTasksByDueDate(dueDate);
        assertEquals(1, fromService.size());
    }

    @Test
    void findTasksByStatus() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(TASK_2);
        tasks.add(TASK_3);

        when(repository.findTasksByStatus(TaskStatus.OPEN)).thenReturn(tasks);

        List<Task> fromService = service.findTasksByStatus(TaskStatus.OPEN);
        assertEquals(2, fromService.size());
    }

    @Test
    void deleteTask() {
        when(repository.findById(1L)).thenReturn(Optional.of(TASK_1));
        doNothing().when(repository).delete(TASK_1);

        boolean deleted = service.deleteTask(TASK_1.getId());
        assertTrue(deleted);
    }

    @Test
    void updateTask() {
        when(repository.findById(1L)).thenReturn(Optional.of(TASK_1));
        when(repository.save(TASK_1)).thenReturn(TASK_1);

        boolean updated = service.updateTask(TASK_1.getId(), TASK_1);
        assertTrue(updated);
    }
}