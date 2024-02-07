package com.project.cinema.service.reservation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.cinema.dto.reservation.ReservationCountPerUsername;
import com.project.cinema.exception.EntityNotFoundException;
import com.project.cinema.exception.ErrorCreatingEntityException;
import com.project.cinema.exception.ErrorDeletingEntityException;
import com.project.cinema.exception.TakenSeatException;
import com.project.cinema.model.Reservation;
import com.project.cinema.model.Screening;
import com.project.cinema.model.Seat;
import com.project.cinema.repository.ReservationRepository;
import com.project.cinema.repository.SeatRepository;
import com.project.cinema.service.screening.ScreeningService;
import com.project.cinema.service.seat.SeatService;

import jakarta.transaction.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    private final ScreeningService screeningService;
    private final SeatService seatService;

    public ReservationServiceImpl(
            ReservationRepository reservationRepository,
            SeatRepository seatRepository,
            ScreeningService screeningService,
            SeatService seatService) {
        this.reservationRepository = reservationRepository;
        this.seatRepository = seatRepository;
        this.screeningService = screeningService;
        this.seatService = seatService;
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation", id));
    }

    @Override
    public Reservation save(Reservation reservation) {
        try {
            return reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new ErrorCreatingEntityException("Reservation", e.getClass().getSimpleName());
        }
    }

    @Override
    @Transactional
    public Reservation update(
            Long id,
            Reservation reservation,
            Optional<Set<Long>> takenSeatsIds) {

        Reservation reservationToUpdate = getById(id);

        Set<Seat> takenSeats = seatRepository.takenSeatsForScreening(reservationToUpdate.getScreening().getId());

        Set<Seat> chosenSeats = takenSeatsIds
                .map(seats -> mapTakenSeats(seats, takenSeats, reservationToUpdate.getTakenSeats()))
                .orElse(null);

        reservation.setTakenSeats(chosenSeats);

        reservationToUpdate.update(reservation);

        return save(reservationToUpdate);
    }

    @Override
    public Long delete(Long id) {
        Reservation reservationToDelete = getById(id);

        try {
            reservationRepository.delete(reservationToDelete);
            return id;
        } catch (Exception e) {
            throw new ErrorDeletingEntityException("Reservation", id);
        }
    }

    @Override
    @Transactional
    public Reservation create(Reservation reservation, Set<Long> takenSeatsIds, Long screeningId) {
        Screening screening = screeningService.getById(screeningId);
        Set<Seat> takenSeats = seatRepository.takenSeatsForScreening(screeningId);
        Set<Seat> chosenSeats = mapTakenSeats(takenSeatsIds, takenSeats);

        reservation.setScreening(screening);
        reservation.setTakenSeats(chosenSeats);

        return save(reservation);
    }

    @Override
    public List<ReservationCountPerUsername> getReservationsCountPerUsername() {
        return reservationRepository.getReservationsCountByUsername();
    }

    private Set<Seat> mapTakenSeats(Set<Long> chosenSeatsIds, Set<Seat> takenSeats) {
        Set<Seat> mappedSeats = chosenSeatsIds.stream().map(seatService::getById)
                .collect(Collectors.toCollection(HashSet::new));

        mappedSeats.forEach(seat -> {
            if (takenSeats.contains(seat)) {
                throw new TakenSeatException();
            }
        });

        return mappedSeats;
    }

    private Set<Seat> mapTakenSeats(Set<Long> chosenSeatsIds, Set<Seat> takenSeats, Set<Seat> seats2) {
        Set<Seat> mappedSeats = chosenSeatsIds.stream().map(seatService::getById)
                .collect(Collectors.toCollection(HashSet::new));

        mappedSeats.forEach(seat -> {
            if (!seats2.contains(seat) && takenSeats.contains(seat)) {
                throw new TakenSeatException();
            }
        });

        return mappedSeats;
    }
}
