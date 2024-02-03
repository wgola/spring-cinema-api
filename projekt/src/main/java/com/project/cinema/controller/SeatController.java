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

import com.project.cinema.dto.seat.SeatReadDto;
import com.project.cinema.dto.seat.SeatWriteDto;
import com.project.cinema.mapper.SeatMapper;
import com.project.cinema.model.Seat;
import com.project.cinema.service.seat.SeatService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/seat")
public class SeatController {

    private final SeatService seatService;
    private final SeatMapper seatMapper;

    public SeatController(SeatService seatService, SeatMapper seatMapper) {
        this.seatService = seatService;
        this.seatMapper = seatMapper;
    }

    @GetMapping
    public List<SeatReadDto> getAllSeats() {
        return seatService.getAll().stream()
                .map(seatMapper::toReadDto)
                .toList();
    }

    @GetMapping("/{id}")
    public SeatReadDto getSeatById(@PathVariable Long id) {
        Seat foundSeat = seatService.getById(id);

        return seatMapper.toReadDto(foundSeat);
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public SeatReadDto createSeat(@RequestBody @Valid SeatWriteDto seat) {
        Seat seatToCreate = seatMapper.fromWriteDto(seat);
        Seat createdSeat = seatService.save(seatToCreate);

        return seatMapper.toReadDto(createdSeat);
    }

    @PutMapping("/{id}")
    public SeatReadDto updateSeat(
            @PathVariable Long id,
            @RequestBody @Valid SeatWriteDto seat) {
        Seat seatToUpdate = seatMapper.fromWriteDto(seat);
        Seat updatedSeat = seatService.update(id, seatToUpdate);

        return seatMapper.toReadDto(updatedSeat);
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteSeat(@PathVariable Long id) {
        return Map.of("id", seatService.delete(id));
    }
}
