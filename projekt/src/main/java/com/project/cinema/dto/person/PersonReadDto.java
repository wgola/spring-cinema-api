package com.project.cinema.dto.person;

import java.time.LocalDate;

public record PersonReadDto(
        Long id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth) {
}
