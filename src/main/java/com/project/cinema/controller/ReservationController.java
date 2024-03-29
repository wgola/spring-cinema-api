package com.project.cinema.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinema.dto.reservation.ReservationCountPerUsername;
import com.project.cinema.dto.reservation.ReservationReadDto;
import com.project.cinema.dto.reservation.ReservationUpdateDto;
import com.project.cinema.dto.reservation.ReservationWriteDto;
import com.project.cinema.mapper.ReservationMapper;
import com.project.cinema.model.Reservation;
import com.project.cinema.service.reservation.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationController(
            ReservationService reservationService,
            ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @GetMapping
    public List<ReservationReadDto> getAllReservations() {
        return reservationService.getAll().stream()
                .map(reservationMapper::toReadDto)
                .toList();
    }

    @GetMapping("/byUsername")
    public List<ReservationCountPerUsername> getReservationCountPerUsernames() {
        return reservationService.getReservationsCountPerUsername();
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

        Reservation createdReservation = reservationService.create(
                reservationToCreate,
                reservation.takenSeatsIds(),
                reservation.screeningId());

        return reservationMapper.toReadDto(createdReservation);
    }

    @PutMapping("/{id}")
    public ReservationReadDto updateReservation(
            @PathVariable Long id,
            @RequestBody @Valid ReservationUpdateDto reservation) {
        Reservation reservationToUpdate = Reservation.builder()
                .customerFullName(reservation.customerFullName().orElse(null))
                .build();

        Reservation updatedReservation = reservationService.update(
                id,
                reservationToUpdate,
                reservation.takenSeatsIds());

        return reservationMapper.toReadDto(updatedReservation);
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteReservation(@PathVariable Long id) {
        return Map.of("id", reservationService.delete(id));
    }
}
