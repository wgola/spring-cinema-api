package com.project.cinema.dto.reservation;

import java.util.List;

import com.project.cinema.dto.screening.ScreeningReadDto;
import com.project.cinema.dto.seat.SeatReadDto;

public record ReservationReadDto(
        Long id,
        String customerFullName,
        List<SeatReadDto> takenSeats,
        ScreeningReadDto screening) {
}
