package com.project.cinema.service;

import org.springframework.stereotype.Service;

import com.project.cinema.repository.SeatRepository;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
}
