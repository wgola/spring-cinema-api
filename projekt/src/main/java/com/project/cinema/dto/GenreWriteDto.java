package com.project.cinema.dto;

import jakarta.validation.constraints.Max;

public record GenreWriteDto(
        @Max(value = 20, message = "Genre's name must be max 20 characters long!") String name) {
}
