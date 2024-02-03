package com.project.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.cinema.dto.reservation.ReservationReadDto;
import com.project.cinema.dto.reservation.ReservationWriteDto;
import com.project.cinema.model.Reservation;

@Mapper
public interface ReservationMapper {

    ReservationReadDto toReadDto(Reservation reservation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "takenSeats", ignore = true)
    @Mapping(target = "screening", ignore = true)
    Reservation fromWriteDto(ReservationWriteDto reservationWriteDto);
}
