package com.organicautonomy.tasks.repository;

import com.organicautonomy.tasks.domain.Task;
import com.organicautonomy.tasks.domain.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TaskRepositoryTest {
    @Autowired
    private TaskRepository repository;
    private Task task1, task2;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        task1 = new Task("Take out the trash.", LocalDate.of(2021, 4, 3),
                LocalDate.of(2021, 4, 4), Time.valueOf("3:30:00"), TaskStatus.OPEN);

        task2 = new Task("Walk the dogs.", LocalDate.of(2021, 4, 3),
                LocalDate.of(2021, 4, 4), Time.valueOf("1:30:00"), TaskStatus.OPEN);

    }

    @Test
    void saveFindTask() {
        task1 = repository.save(task1);

        Optional<Task> fromRepository = repository.findById(task1.getId());
        assertEquals(task1, fromRepository.get());
    }

    @Test
    void findTasksByDueDate() {
        task1 = repository.save(task1);
        task2 = repository.save(task2);

        List<Task> tasks = repository.findTasksByDueDate(LocalDate.of(2021, 4, 4));
        assertEquals(2, tasks.size());
    }

    @Test
    void findTasksBySubmissionDate() {
        task1 = repository.save(task1);
        task2 = repository.save(task2);

        List<Task> tasks = repository.findTasksBySubmissionDate(LocalDate.of(2021, 4, 3));
        assertEquals(2, tasks.size());
    }

    @Test
    void findTasksByDueTime() {
        task1 = repository.save(task1);

        List<Task> tasks = repository.findTasksByDueTime(Time.valueOf("3:30:00"));
        assertEquals(1, tasks.size());
    }

    @Test
    void findTasksByStatus() {
        task1 = repository.save(task1);
        task2 = repository.save(task2);

        List<Task> tasks = repository.findTasksByStatus(TaskStatus.OPEN);
        assertEquals(2, tasks.size());
    }

}