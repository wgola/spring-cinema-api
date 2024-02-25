package com.project.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.cinema.dto.screening.ScreeningReadDto;
import com.project.cinema.dto.screening.ScreeningWriteDto;
import com.project.cinema.model.Screening;

@Mapper
public interface ScreeningMapper {

    ScreeningReadDto toReadDto(Screening screening);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movie", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    Screening fromWriteDto(ScreeningWriteDto screeningWriteDto);
}
