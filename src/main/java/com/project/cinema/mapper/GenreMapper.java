package com.project.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.cinema.dto.genre.GenreReadDto;
import com.project.cinema.dto.genre.GenreWriteDto;
import com.project.cinema.model.Genre;

@Mapper
public interface GenreMapper {

    GenreReadDto toReadDto(Genre genre);

    @Mapping(target = "id", ignore = true)
    Genre fromWriteDto(GenreWriteDto genreWriteDto);
}
