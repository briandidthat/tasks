package com.organicautonomy.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotNull(message = "Value is mandatory")
    private String value;
    @NotNull(message = "Submission date is mandatory")
    private LocalDate submissionDate;
    @NotNull(message = "Due date is mandatory.")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dueDate;
    @NotNull(message = "Status is mandatory")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public Task(String value, LocalDate submissionDate, LocalDateTime dueDate, TaskStatus status) {
        this.value = value;
        this.submissionDate = submissionDate;
        this.dueDate = dueDate;
        this.status = status;
    }
}
