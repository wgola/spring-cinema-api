package com.project.cinema.exception;

public class ErrorDeletingEntityException extends IllegalStateException {

    public ErrorDeletingEntityException(String name, Long id) {
        super("Error deleting entity '" + name + "' with ID '" + id + "'!");
    }
}
