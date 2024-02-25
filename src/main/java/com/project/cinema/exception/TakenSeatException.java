package com.project.cinema.exception;

public class TakenSeatException extends IllegalArgumentException {

    public TakenSeatException() {
        super("You have chosen taken seat!");
    }
}
