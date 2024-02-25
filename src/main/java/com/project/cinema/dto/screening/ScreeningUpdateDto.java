package com.project.cinema.dto.screening;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public record ScreeningUpdateDto(
        Optional<LocalDate> screeningDate,
        Optional<LocalTime> screeningTime,
        Optional<Long> movieId) {
}
