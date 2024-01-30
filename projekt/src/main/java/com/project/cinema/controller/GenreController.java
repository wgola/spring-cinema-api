package com.project.cinema.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinema.dto.GenreReadDto;
import com.project.cinema.dto.GenreWriteDto;
import com.project.cinema.mapper.GenreMapper;
import com.project.cinema.model.Genre;
import com.project.cinema.service.GenreService;

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
        return genreService.getAllGenres().stream()
                .map(genreMapper::toReadDto)
                .toList();
    }

    @GetMapping("/{id}")
    public GenreReadDto getGenreById(@PathVariable Long id) {
        Genre foundGenre = genreService.getGenreById(id);

        return genreMapper.toReadDto(foundGenre);
    }

    @PostMapping
    public GenreReadDto createGenre(@RequestBody @Valid GenreWriteDto genre) {
        Genre genreToCreate = genreMapper.fromWriteDto(genre);
        Genre createdGenre = genreService.createGenre(genreToCreate);

        return genreMapper.toReadDto(createdGenre);
    }

    @PutMapping("/{id}")
    public GenreReadDto updateGenre(@PathVariable Long id, @RequestBody GenreWriteDto genre) {
        Genre genreToUpdate = genreMapper.fromWriteDto(genre);
        Genre updatedGenre = genreService.updateGenre(id, genreToUpdate);

        return genreMapper.toReadDto(updatedGenre);
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteGenre(@PathVariable Long id) {
        return Map.of("id", genreService.deleteGenre(id));
    }
}
