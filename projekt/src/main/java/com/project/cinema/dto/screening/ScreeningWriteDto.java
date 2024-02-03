package com.project.cinema.dto.screening;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record ScreeningWriteDto(
        @NotNull LocalDate screeningDate,
        @NotNull LocalDate screeningTime,
        @NotNull Long movieId) {
}
