package com.project.cinema.dto.movie;

import java.time.LocalDate;
import java.util.List;

import com.project.cinema.validators.maxLength.MaxLength;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MovieWriteDto(
        @NotEmpty @MaxLength(max = 30, field = "title") String title,
        @NotEmpty @MaxLength(max = 255, field = "description") String description,
        @NotNull LocalDate releaseDate,
        @NotEmpty List<@NotEmpty @MaxLength(max = 20, field = "genre") String> genres,
        @NotEmpty @MaxLength(max = 20, field = "language") String language,
        @NotEmpty List<Long> actorsIds,
        @NotEmpty List<Long> directorsIds) {
}
