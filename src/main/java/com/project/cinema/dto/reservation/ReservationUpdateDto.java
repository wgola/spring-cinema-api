package com.project.cinema.dto.reservation;

import java.util.Optional;
import java.util.Set;

import com.project.cinema.validators.maxLength.MaxLength;

public record ReservationUpdateDto(
        Optional<@MaxLength(max = 30, field = "customerFullName") String> customerFullName,
        Optional<Set<Long>> takenSeatsIds) {
}
