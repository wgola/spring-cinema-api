package com.project.cinema.dto.movie;

import java.time.LocalDate;
import java.util.List;

import com.project.cinema.dto.genre.GenreReadDto;
import com.project.cinema.dto.language.LanguageReadDto;
import com.project.cinema.dto.person.PersonReadDto;

public record MovieReadDto(
        Long id,
        String title,
        String description,
        LocalDate releaseDate,
        List<GenreReadDto> genres,
        LanguageReadDto language,
        List<PersonReadDto> actors,
        List<PersonReadDto> directors) {
}
