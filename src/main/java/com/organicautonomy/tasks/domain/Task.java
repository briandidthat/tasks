package com.organicautonomy.tasks.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dueDate;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;


    public Task(String value, LocalDate submissionDate, LocalDateTime dueDate, TaskStatus status) {
        this.value = value;
        this.submissionDate = submissionDate;
        this.dueDate = dueDate;
        this.status = status;
    }
}
