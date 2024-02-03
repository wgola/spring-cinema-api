package com.project.cinema.service.screening;

import java.util.List;

import com.project.cinema.model.Screening;

public interface ScreeningService {

    List<Screening> getAll();

    Screening getById(Long id);

    Screening save(Screening screening);

    Screening update(Long id, Screening screening);

    Long delete(Long id);
}
