package com.project.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.cinema.dto.seat.SeatReadDto;
import com.project.cinema.dto.seat.SeatWriteDto;
import com.project.cinema.model.Seat;

@Mapper
public interface SeatMapper {

    SeatReadDto toReadDto(Seat seat);

    @Mapping(target = "id", ignore = true)
    Seat fromWriteDto(SeatWriteDto seatWriteDto);
}
