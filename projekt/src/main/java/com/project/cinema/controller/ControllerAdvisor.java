package com.project.cinema.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.cinema.exception.EntityNotFoundException;
import com.project.cinema.exception.ErrorCreatingEntityException;
import com.project.cinema.exception.ErrorDeletingEntityException;
import com.project.cinema.exception.TakenSeatException;

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
    public ResponseEntity<Map<String, List<Map<String, String>>>> invalidArgumentHandler(
            MethodArgumentNotValidException exception) {

        List<Map<String, String>> errors = exception.getFieldErrors().stream()
                .map(fieldError -> Map.of(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return ResponseEntity.badRequest().body(Map.of("errors", errors));
    }

    @ExceptionHandler(TakenSeatException.class)
    public ResponseEntity<Map<String, String>> takenSeatExceptionHandler(TakenSeatException exception) {
        return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
    }
}
