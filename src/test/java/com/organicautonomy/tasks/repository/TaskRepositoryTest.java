package com.organicautonomy.tasks.repository;

import com.organicautonomy.tasks.domain.Task;
import com.organicautonomy.tasks.domain.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

        task1 = new Task("do the dishes", LocalDate.of(2021,4,2),
                LocalDateTime.of(LocalDate.of(2021, 4, 4), LocalTime.of(3,30)), TaskStatus.OPEN);

        task2 = new Task("Walk the dogs.", LocalDate.of(2021, 4, 2),
                LocalDateTime.of(LocalDate.of(2021, 4, 4), LocalTime.of(3,30)), TaskStatus.OPEN);

    }

    @Test
    void saveFindTask() {
        task1 = repository.save(task1);

        Optional<Task> fromRepository = repository.findById(task1.getId());
        assertEquals(task1, fromRepository.get());
    }

    @Test
    void findTasksBySubmissionDate() {
        task1 = repository.save(task1);
        task2 = repository.save(task2);

        List<Task> tasks = repository.findTasksBySubmissionDate(LocalDate.of(2021, 4, 2));
        assertEquals(2, tasks.size());
    }

    @Test
    void findTasksByDueDate() {
        task1 = repository.save(task1);
        task2 = repository.save(task2);

        List<Task> tasks = repository.findTasksByDueDate(task1.getDueDate());
        assertEquals(2, tasks.size());
    }


    @Test
    void findTasksByStatus() {
        task1 = repository.save(task1);
        task2 = repository.save(task2);

        List<Task> tasks = repository.findTasksByStatus(TaskStatus.OPEN);
        assertEquals(2, tasks.size());
    }

}