package com.project.cinema.dto.reservation;

import java.util.List;

import com.project.cinema.validators.maxLength.MaxLength;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ReservationWriteDto(
        @NotEmpty @MaxLength(max = 30, field = "customerFullName") String customerFullName,
        @NotEmpty List<Long> takenSeatsIds,
        @NotNull Long screeningId) {
}
