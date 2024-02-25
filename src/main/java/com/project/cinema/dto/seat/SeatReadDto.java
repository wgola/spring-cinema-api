package com.project.cinema.dto.seat;

public record SeatReadDto(
        Long id,
        int seatNumber,
        int rowNumber) {
}
