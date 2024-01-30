package com.project.cinema.exception;

import java.util.NoSuchElementException;

public class EntityNotFoundException extends NoSuchElementException {

    public EntityNotFoundException(String name, Long id) {
        super("Entity '" + name + "' with ID '" + id + "' not found!");
    }
}
