package com.project.cinema.dto.reservation;

import java.util.List;
import java.util.Optional;

import com.project.cinema.validators.maxLength.MaxLength;

public record ReservationUpdateDto(
        Optional<@MaxLength(max = 30, field = "customerFullName") String> customerFullName,
        Optional<List<Long>> takenSeatsIds,
        Optional<Long> screeningId) {
}
