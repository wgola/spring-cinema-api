package com.project.cinema.service.reservation;

import java.util.List;

import com.project.cinema.model.Reservation;

public interface ReservationService {

    List<Reservation> getAll();

    Reservation getById(Long id);

    Reservation save(Reservation reservation);

    Reservation update(Long id, Reservation reservation);

    Long delete(Long id);
}
