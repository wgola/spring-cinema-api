package com.project.cinema.exception;

public class ErrorCreatingEntityException extends IllegalArgumentException {

    public ErrorCreatingEntityException(String name) {
        super("Error creating entity '" + name + "'!");
    }
}
