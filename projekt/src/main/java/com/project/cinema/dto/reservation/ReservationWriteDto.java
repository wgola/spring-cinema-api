package com.project.cinema.dto.reservation;

import java.util.Set;

import com.project.cinema.validators.maxLength.MaxLength;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ReservationWriteDto(
        @NotEmpty @MaxLength(max = 30, field = "customerFullName") String customerFullName,
        @NotEmpty Set<Long> takenSeatsIds,
        @NotNull Long screeningId) {
}
