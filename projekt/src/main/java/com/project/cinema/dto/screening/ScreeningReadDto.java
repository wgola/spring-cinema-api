package com.project.cinema.dto.screening;

import java.time.LocalDate;
import java.time.LocalTime;

import com.project.cinema.dto.movie.MovieReadDto;

public record ScreeningReadDto(
        Long id,
        LocalDate screeningDate,
        LocalTime screeningTime,
        MovieReadDto movie) {
}
