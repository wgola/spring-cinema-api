package com.project.cinema.dto.language;

import com.project.cinema.validators.maxLength.MaxLength;

import jakarta.validation.constraints.NotEmpty;

public record LanguageWriteDto(
        @NotEmpty @MaxLength(max = 20, field = "name") String name) {
}
