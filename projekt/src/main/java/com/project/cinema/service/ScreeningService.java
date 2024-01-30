package com.project.cinema.service;

import org.springframework.stereotype.Service;

import com.project.cinema.repository.ScreeningRepository;

@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }
}
