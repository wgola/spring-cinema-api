package com.project.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.cinema.dto.movie.MovieReadDto;
import com.project.cinema.dto.movie.MovieWriteDto;
import com.project.cinema.model.Movie;

@Mapper
public interface MovieMapper {

    MovieReadDto toReadDto(Movie movie);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "language", ignore = true)
    @Mapping(target = "actors", ignore = true)
    @Mapping(target = "directors", ignore = true)
    Movie fromWriteDto(MovieWriteDto movieWriteDto);
}
