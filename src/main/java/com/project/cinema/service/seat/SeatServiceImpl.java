package com.project.cinema.service.seat;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.cinema.exception.EntityNotFoundException;
import com.project.cinema.exception.ErrorCreatingEntityException;
import com.project.cinema.exception.ErrorDeletingEntityException;
import com.project.cinema.model.Seat;
import com.project.cinema.repository.SeatRepository;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> getAll() {
        return seatRepository.findAll();
    }

    @Override
    public Seat getById(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seat", id));
    }

    @Override
    public Seat save(Seat seat) {
        try {
            return seatRepository.save(seat);
        } catch (Exception e) {
            throw new ErrorCreatingEntityException("Seat", e.getClass().getSimpleName());
        }

    }

    @Override
    public Seat update(Long id, Seat seat) {
        Seat seatToUpdate = getById(id);

        seatToUpdate.update(seat);

        return save(seatToUpdate);
    }

    @Override
    public Long delete(Long id) {
        Seat seatToDelete = getById(id);

        try {
            seatRepository.delete(seatToDelete);
            return id;
        } catch (Exception e) {
            throw new ErrorDeletingEntityException("Seat", id);
        }
    }
}
