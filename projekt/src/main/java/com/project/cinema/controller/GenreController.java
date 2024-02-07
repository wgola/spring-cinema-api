package com.project.cinema.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinema.dto.genre.GenreReadDto;
import com.project.cinema.dto.genre.GenreWriteDto;
import com.project.cinema.mapper.GenreMapper;
import com.project.cinema.model.Genre;
import com.project.cinema.service.genre.GenreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    private final GenreService genreService;
    private final GenreMapper genreMapper;

    public GenreController(GenreService genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

    @GetMapping
    public List<GenreReadDto> getAllGenres() {
        return genreService.getAll().stream()
                .map(genreMapper::toReadDto)
                .toList();
    }

    @GetMapping("/person/{personId}")
    public List<GenreReadDto> getPersonGenres(@PathVariable Long personId) {
        return genreService.getPersonsGenres(personId).stream()
                .map(genreMapper::toReadDto)
                .toList();
    }

    @GetMapping("/{id}")
    public GenreReadDto getGenreById(@PathVariable Long id) {
        Genre foundGenre = genreService.getById(id);

        return genreMapper.toReadDto(foundGenre);
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public GenreReadDto createGenre(@RequestBody @Valid GenreWriteDto genre) {
        Genre genreToCreate = genreMapper.fromWriteDto(genre);
        Genre createdGenre = genreService.save(genreToCreate);

        return genreMapper.toReadDto(createdGenre);
    }

    @PutMapping("/{id}")
    public GenreReadDto updateGenre(@PathVariable Long id, @RequestBody @Valid GenreWriteDto genre) {
        Genre genreToUpdate = genreMapper.fromWriteDto(genre);
        Genre updatedGenre = genreService.update(id, genreToUpdate);

        return genreMapper.toReadDto(updatedGenre);
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteGenre(@PathVariable Long id) {
        return Map.of("id", genreService.delete(id));
    }
}
