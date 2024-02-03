package com.project.cinema.service.seat;

import java.util.List;

import com.project.cinema.model.Seat;

public interface SeatService {

    List<Seat> getAll();

    Seat getById(Long id);

    Seat save(Seat seat);

    Seat update(Long id, Seat seat);

    Long delete(Long id);
}
