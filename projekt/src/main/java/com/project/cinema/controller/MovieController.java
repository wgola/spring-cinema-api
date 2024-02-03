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

import com.project.cinema.dto.movie.MovieReadDto;
import com.project.cinema.dto.movie.MovieWriteDto;
import com.project.cinema.mapper.MovieMapper;
import com.project.cinema.model.Genre;
import com.project.cinema.model.Language;
import com.project.cinema.model.Movie;
import com.project.cinema.model.Person;
import com.project.cinema.service.genre.GenreService;
import com.project.cinema.service.language.LanguageService;
import com.project.cinema.service.movie.MovieService;
import com.project.cinema.service.person.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieService movieService;
    private final GenreService genreService;
    private final LanguageService languageService;
    private final PersonService personService;
    private final MovieMapper movieMapper;

    public MovieController(
            MovieService movieService,
            GenreService genreService,
            LanguageService languageService,
            PersonService personService,
            MovieMapper movieMapper) {
        this.movieService = movieService;
        this.genreService = genreService;
        this.languageService = languageService;
        this.personService = personService;
        this.movieMapper = movieMapper;
    }

    @GetMapping
    public List<MovieReadDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(movieMapper::toReadDto)
                .toList();
    }

    @GetMapping("/{id}")
    public MovieReadDto getMovieById(@PathVariable Long id) {
        Movie foundMovie = movieService.getById(id);

        return movieMapper.toReadDto(foundMovie);
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public MovieReadDto createMovie(@RequestBody @Valid MovieWriteDto movie) {
        Movie movieToCreate = movieMapper.fromWriteDto(movie);

        List<Genre> genres = movie.genresIds().stream().map(genreService::getById).toList();
        Language language = languageService.getById(movie.languageId());
        List<Person> actors = movie.actorsIds().stream().map(personService::getById).toList();
        List<Person> directors = movie.directorsIds().stream().map(personService::getById).toList();

        movieToCreate.setGenres(genres);
        movieToCreate.setLanguage(language);
        movieToCreate.setActors(actors);
        movieToCreate.setDirectors(directors);

        Movie createdMovie = movieService.save(movieToCreate);

        return movieMapper.toReadDto(createdMovie);
    }

    @PutMapping("/{id}")
    public MovieReadDto updateMovie(@PathVariable Long id, @RequestBody @Valid MovieWriteDto movie) {
        Movie movieToUpdate = movieMapper.fromWriteDto(movie);
        Movie updatedMovie = movieService.update(id, movieToUpdate);

        return movieMapper.toReadDto(updatedMovie);
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteMovie(@PathVariable Long id) {
        return Map.of("id", movieService.delete(id));
    }
}
