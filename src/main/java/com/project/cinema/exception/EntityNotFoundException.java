package com.project.cinema.exception;

import java.util.NoSuchElementException;

public class EntityNotFoundException extends NoSuchElementException {

    public EntityNotFoundException(String name, Long id) {
        super("Entity '%s' with ID '%d' not found!".formatted(name, id));
    }
}
