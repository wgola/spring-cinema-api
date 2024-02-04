package com.project.cinema.dto.screening;

import java.time.LocalDate;
import java.util.Optional;

public record ScreeningUpdateDto(
        Optional<LocalDate> screeningDate,
        Optional<LocalDate> screeningTime,
        Optional<Long> movieId) {
}
