package com.project.cinema.dto.person;

import java.time.LocalDate;

import com.project.cinema.validators.maxLength.MaxLength;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PersonWriteDto(
        @NotEmpty @MaxLength(max = 20, field = "firstName") String firstName,
        @NotEmpty @MaxLength(max = 20, field = "lastName") String lastName,
        @NotNull LocalDate dateOfBirth) {
}
