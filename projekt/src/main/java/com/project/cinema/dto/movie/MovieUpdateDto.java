package com.project.cinema.dto.movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.project.cinema.validators.maxLength.MaxLength;

public record MovieUpdateDto(
        Optional<@MaxLength(max = 30, field = "title") String> title,
        Optional<@MaxLength(max = 255, field = "description") String> description,
        Optional<LocalDate> releaseDate,
        Optional<List<@MaxLength(max = 20, field = "genre") String>> genres,
        Optional<@MaxLength(max = 20, field = "language") String> language,
        Optional<List<Long>> actorsIds,
        Optional<List<Long>> directorsIds) {
}
