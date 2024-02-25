package com.project.cinema.exception;

public class ErrorCreatingEntityException extends IllegalArgumentException {

    public ErrorCreatingEntityException(String name, String message) {
        super("Error saving entity '%s': %s!".formatted(name, message));
    }
}
