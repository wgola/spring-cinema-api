package com.project.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.cinema.dto.person.PersonReadDto;
import com.project.cinema.dto.person.PersonWriteDto;
import com.project.cinema.model.Person;

@Mapper
public interface PersonMapper {

    PersonReadDto toReadtDto(Person person);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "directedMovies", ignore = true)
    @Mapping(target = "actedInMovies", ignore = true)
    Person fromWriteDto(PersonWriteDto personWriteDto);
}
