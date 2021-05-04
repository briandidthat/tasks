package com.organicautonomy.tasks.repository;

import com.organicautonomy.tasks.domain.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TaskRepositoryTest {
    @Autowired
    private TaskRepository repository;
    private Task task1, task2;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        task1 = new Task();
        task1.setValue("Take out the trash.");
        task1.setSubmissionDate(LocalDate.of(2021, 4, 3));
        task1.setDueDate(LocalDate.of(2021,4,4));

        task2 = new Task();
        task2.setValue("Walk the dogs.");
        task2.setSubmissionDate(LocalDate.of(2021, 4, 3));
        task2.setDueDate(LocalDate.of(2021,4,4));
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

        List<Task> tasks = repository.findTasksByDueDate(LocalDate.of(2021, 4,4));
        assertEquals(2, tasks.size());
    }

    @Test
    void findTasksBySubmissionDate() {
        task1 = repository.save(task1);
        task2 = repository.save(task2);

        List<Task> tasks = repository.findTasksBySubmissionDate(LocalDate.of(2021, 4, 3));
        assertEquals(2, tasks.size());
    }
}