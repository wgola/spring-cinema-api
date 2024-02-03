package com.project.cinema.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.cinema.exception.EntityNotFoundException;
import com.project.cinema.exception.ErrorCreatingEntityException;
import com.project.cinema.exception.ErrorDeletingEntityException;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> entityNotFoundExceptionHandler(
            EntityNotFoundException exception) {
        return ResponseEntity.status(404).body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(ErrorCreatingEntityException.class)
    public ResponseEntity<Map<String, String>> errorCreatingEntityExceptionHandler(
            ErrorCreatingEntityException exception) {
        return ResponseEntity.unprocessableEntity().body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(ErrorDeletingEntityException.class)
    public ResponseEntity<Map<String, String>> errorDeletingEntityExceptionHandler(
            ErrorDeletingEntityException exception) {
        return ResponseEntity.unprocessableEntity().body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> invalidArgumentHandler(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return ResponseEntity.badRequest().body(Map.of("errors", errors));
    }
}
