package com.organicautonomy.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
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
    @NotBlank(message = "Value is mandatory")
    private String value;
    @NotBlank(message = "Submission date is mandatory")
    @PastOrPresent
    private LocalDate submissionDate;
    @NotBlank(message = "Due date is mandatory")
    @Column(columnDefinition = "TIMESTAMP")
    @Future
    private LocalDateTime dueDate;
    @NotBlank(message = "Status is mandatory")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public Task(String value, LocalDate submissionDate, LocalDateTime dueDate, TaskStatus status) {
        this.value = value;
        this.submissionDate = submissionDate;
        this.dueDate = dueDate;
        this.status = status;
    }
}
