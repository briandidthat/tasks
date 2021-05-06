package com.organicautonomy.tasks.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.organicautonomy.tasks.domain.Task;
import com.organicautonomy.tasks.domain.TaskStatus;
import com.organicautonomy.tasks.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
@ExtendWith(SpringExtension.class)
class TaskControllerTest {
    private final Task TO_SAVE = new Task(0,"do the dishes", LocalDate.of(2021,4,2),
            LocalDateTime.of(LocalDate.of(2021, 4, 6), LocalTime.of(3,30)), TaskStatus.OPEN);
    private final Task TASK_1 = new Task(1,"do the dishes", LocalDate.of(2021,4,2),
            LocalDateTime.of(LocalDate.of(2021, 4, 6), LocalTime.of(3,30)), TaskStatus.OPEN);
    private final Task TASK_2 = new Task(2,"walk the dog", LocalDate.of(2021,4,3),
            LocalDateTime.of(LocalDate.of(2021, 4, 7), LocalTime.of(1,30)), TaskStatus.OPEN);
    private final Task TASK_3 = new Task(3,"clean the house", LocalDate.of(2021,4,3),
            LocalDateTime.of(LocalDate.of(2021, 4, 7), LocalTime.of(4,30)), TaskStatus.OPEN);

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createTask() throws Exception {
        String inputJson = mapper.writeValueAsString(TO_SAVE);
        String outputJson = mapper.writeValueAsString(TASK_1);

        when(service.saveTask(TO_SAVE)).thenReturn(TASK_1);

        this.mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson))
                .andDo(print());
    }

    @Test
    void getAllTasks() throws Exception {
        List<Task> tasks = List.of(TASK_1, TASK_2, TASK_3);
        String outputJson = mapper.writeValueAsString(tasks);

        when(service.findAllTasks()).thenReturn(tasks);

        this.mockMvc.perform(get("/tasks"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getTaskById() throws Exception {
        String outputJson = mapper.writeValueAsString(TASK_2);

        when(service.findTaskById(TASK_2.getId())).thenReturn(TASK_2);

        this.mockMvc.perform(get("/tasks/{id}", TASK_2.getId()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getTasksBySubmissionDate() throws Exception {
        List<Task> tasks = List.of(TASK_2, TASK_3);
        String outputJson = mapper.writeValueAsString(tasks);

        when(service.findTasksBySubmissionDate(TASK_2.getSubmissionDate())).thenReturn(tasks);

        this.mockMvc.perform(get("/tasks/submission-date/{submissionDate}", TASK_2.getSubmissionDate()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getTasksByDueDate() throws Exception {
        List<Task> tasks = List.of(TASK_2, TASK_3);
        String outputJson = mapper.writeValueAsString(tasks);

        when(service.findTasksByDueDate(TASK_2.getDueDate())).thenReturn(tasks);

        this.mockMvc.perform(get("/tasks/due-date/{dueDate}", TASK_2.getDueDate()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateTask() {

    }

    @Test
    void deleteTask() {
    }
}