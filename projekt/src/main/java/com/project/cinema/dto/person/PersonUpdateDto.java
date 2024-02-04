package com.project.cinema.dto.person;

import java.time.LocalDate;
import java.util.Optional;

import com.project.cinema.validators.maxLength.MaxLength;

public record PersonUpdateDto(
        Optional<@MaxLength(max = 20, field = "firstName") String> firstName,
        Optional<@MaxLength(max = 20, field = "lastName") String> lastName,
        Optional<LocalDate> dateOfBirth) {
}
