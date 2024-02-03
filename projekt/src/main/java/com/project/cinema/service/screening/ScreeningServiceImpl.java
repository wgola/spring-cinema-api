package com.project.cinema.service.screening;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.cinema.exception.EntityNotFoundException;
import com.project.cinema.exception.ErrorCreatingEntityException;
import com.project.cinema.exception.ErrorDeletingEntityException;
import com.project.cinema.model.Screening;
import com.project.cinema.repository.ScreeningRepository;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;

    public ScreeningServiceImpl(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    @Override
    public List<Screening> getAll() {
        return screeningRepository.findAll();
    }

    @Override
    public Screening getById(Long id) {
        return screeningRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Screening", id));
    }

    @Override
    public Screening save(Screening screening) {
        try {
            return screeningRepository.save(screening);
        } catch (Exception e) {
            throw new ErrorCreatingEntityException("Screening", e.getClass().getSimpleName());
        }
    }

    @Override
    public Screening update(Long id, Screening screening) {
        Screening screeningToUpdate = getById(id);

        screeningToUpdate.update(screening);

        return save(screeningToUpdate);
    }

    @Override
    public Long delete(Long id) {
        Screening screeningToDelete = getById(id);

        try {
            screeningRepository.delete(screeningToDelete);
            return id;
        } catch (Exception e) {
            throw new ErrorDeletingEntityException("Screening", id);
        }
    }
}
