package com.project.cinema.service.reservation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.project.cinema.dto.reservation.ReservationCountPerUsername;
import com.project.cinema.model.Reservation;

public interface ReservationService {

    List<Reservation> getAll();

    Reservation getById(Long id);

    Reservation save(Reservation reservation);

    Reservation update(
            Long id,
            Reservation reservation,
            Optional<Set<Long>> takenSeatsIds);

    Long delete(Long id);

    Reservation create(Reservation reservation, Set<Long> takenSeatsIds, Long movieId);

    List<ReservationCountPerUsername> getReservationsCountPerUsername();
}
