package com.project.cinema.dto.seat;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record SeatWriteDto(
        @Min(1) @Max(30) int seatNumber,
        @Min(1) @Max(30) int rowNumber) {
}
