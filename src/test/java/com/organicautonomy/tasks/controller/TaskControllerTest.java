package com.organicautonomy.tasks.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(TaskController.class)
@ExtendWith(SpringExtension.class)
class TaskControllerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void createTask() {
    }

    @Test
    void getAllTasks() {
    }

    @Test
    void getTaskById() {
    }

    @Test
    void getTasksBySubmissionDate() {
    }

    @Test
    void getTasksByDueDate() {
    }

    @Test
    void updateTask() {
    }

    @Test
    void deleteTask() {
    }
}