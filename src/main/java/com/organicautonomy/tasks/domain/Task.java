package com.organicautonomy.tasks.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String value;
    private LocalDate submissionDate;
    private LocalDate dueDate;
    private Time dueTime;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public Task(String value, LocalDate submissionDate, LocalDate dueDate, Time dueTime, TaskStatus status) {
        this.value = value;
        this.submissionDate = submissionDate;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.status = status;
    }
}
