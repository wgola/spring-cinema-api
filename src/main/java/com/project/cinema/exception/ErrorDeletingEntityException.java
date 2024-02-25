package com.project.cinema.exception;

public class ErrorDeletingEntityException extends IllegalStateException {

    public ErrorDeletingEntityException(String name, Long id) {
        super("Error deleting entity '%s' with ID '%d'!".formatted(name, id));
    }
}
