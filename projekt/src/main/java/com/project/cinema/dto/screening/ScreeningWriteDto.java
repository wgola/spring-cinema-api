package com.project.cinema.dto.screening;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public record ScreeningWriteDto(
        @NotNull LocalDate screeningDate,
        @NotNull LocalTime screeningTime,
        @NotNull Long movieId) {
}
