package com.project.cinema.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinema.dto.reservation.ReservationReadDto;
import com.project.cinema.dto.reservation.ReservationWriteDto;
import com.project.cinema.mapper.ReservationMapper;
import com.project.cinema.model.Reservation;
import com.project.cinema.model.Screening;
import com.project.cinema.model.Seat;
import com.project.cinema.service.reservation.ReservationService;
import com.project.cinema.service.screening.ScreeningService;
import com.project.cinema.service.seat.SeatService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final SeatService seatService;
    private final ScreeningService screeningService;
    private final ReservationMapper reservationMapper;

    public ReservationController(
            ReservationService reservationService,
            SeatService seatService,
            ScreeningService screeningService,
            ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.seatService = seatService;
        this.screeningService = screeningService;
        this.reservationMapper = reservationMapper;
    }

    @GetMapping
    public List<ReservationReadDto> getAllReservations() {
        return reservationService.getAll().stream()
                .map(reservationMapper::toReadDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ReservationReadDto getReservationById(@PathVariable Long id) {
        Reservation foundReservation = reservationService.getById(id);

        return reservationMapper.toReadDto(foundReservation);
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public ReservationReadDto createReservation(@RequestBody @Valid ReservationWriteDto reservation) {
        Reservation reservationToCreate = reservationMapper.fromWriteDto(reservation);

        List<Seat> takenSeats = reservation.takenSeatsIds().stream().map(seatService::getById).toList();
        Screening screening = screeningService.getById(reservation.screeningId());

        reservationToCreate.setTakenSeats(takenSeats);
        reservationToCreate.setScreening(screening);

        Reservation createdReservation = reservationService.save(reservationToCreate);

        return reservationMapper.toReadDto(createdReservation);
    }

    @PutMapping("/{id}")
    public ReservationReadDto updateReservation(
            @PathVariable Long id,
            @RequestBody @Valid ReservationWriteDto reservation) {
        Reservation reservationToUpdate = reservationMapper.fromWriteDto(reservation);
        Reservation updatedReservation = reservationService.update(id, reservationToUpdate);

        return reservationMapper.toReadDto(updatedReservation);
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteReservation(@PathVariable Long id) {
        return Map.of("id", reservationService.delete(id));
    }
}
