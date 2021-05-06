package com.organicautonomy.tasks.controller;

import com.organicautonomy.tasks.exception.ExceptionDetails;
import com.organicautonomy.tasks.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> handleResourceNotFoundException(Exception e, WebRequest request) {
        ExceptionDetails details = new ExceptionDetails(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> outOfRangeException(IllegalArgumentException e, WebRequest request) {
        ExceptionDetails details = new ExceptionDetails(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity(details, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleValidationException(MethodArgumentNotValidException e, WebRequest request) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ExceptionDetails> errors = new ArrayList<>();

        Date date = new Date();
        for (FieldError fieldError: fieldErrors) {
            String message = fieldError.getField() + ": " + fieldError.getDefaultMessage();
            ExceptionDetails details = new ExceptionDetails(date, message, request.getDescription(false));
            errors.add(details);
        }

        return new ResponseEntity(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
