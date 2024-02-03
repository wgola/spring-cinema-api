package com.project.cinema.dto.screening;

import java.time.LocalDate;

import com.project.cinema.dto.movie.MovieReadDto;

public record ScreeningReadDto(
        Long id,
        LocalDate screeningDate,
        LocalDate screeningTime,
        MovieReadDto movie) {
}
