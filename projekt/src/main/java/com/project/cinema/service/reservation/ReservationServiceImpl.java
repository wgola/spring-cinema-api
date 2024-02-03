package com.project.cinema.service.reservation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.cinema.exception.EntityNotFoundException;
import com.project.cinema.exception.ErrorCreatingEntityException;
import com.project.cinema.exception.ErrorDeletingEntityException;
import com.project.cinema.model.Reservation;
import com.project.cinema.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
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
    public Reservation update(Long id, Reservation reservation) {
        Reservation reservationToUpdate = getById(id);

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
}
