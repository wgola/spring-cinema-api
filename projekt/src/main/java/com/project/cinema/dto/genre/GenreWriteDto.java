package com.project.cinema.dto.genre;

import com.project.cinema.validators.maxLength.MaxLength;

import jakarta.validation.constraints.NotEmpty;

public record GenreWriteDto(
        @NotEmpty @MaxLength(max = 20, field = "name") String name) {
}
