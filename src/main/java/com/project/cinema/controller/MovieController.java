package com.project.cinema.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinema.criteria.MovieSearchCriteria;
import com.project.cinema.dto.movie.MovieReadDto;
import com.project.cinema.dto.movie.MovieUpdateDto;
import com.project.cinema.dto.movie.MovieWriteDto;
import com.project.cinema.mapper.MovieMapper;
import com.project.cinema.model.Movie;
import com.project.cinema.service.movie.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping
    public Page<MovieReadDto> getAllMovies(
            @SortDefaults({
                    @SortDefault(sort = "title")
            }) @PageableDefault Pageable pageable,
            MovieSearchCriteria movieSearchCriteria) {

        return movieService.getAll(movieSearchCriteria, pageable)
                .map(movieMapper::toReadDto);
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

        Movie createdMovie = movieService.create(
                movieToCreate,
                movie.genres(),
                movie.language(),
                movie.actorsIds(),
                movie.directorsIds());

        return movieMapper.toReadDto(createdMovie);
    }

    @PutMapping("/{id}")
    public MovieReadDto updateMovie(@PathVariable Long id, @RequestBody @Valid MovieUpdateDto movie) {
        Movie movieToUpdate = Movie.builder()
                .title(movie.title().orElse(null))
                .description(movie.description().orElse(null))
                .releaseDate(movie.releaseDate().orElse(null))
                .build();

        Movie updatedMovie = movieService.update(
                id,
                movieToUpdate,
                movie.language(),
                movie.genres(),
                movie.actorsIds(),
                movie.directorsIds());

        return movieMapper.toReadDto(updatedMovie);
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteMovie(@PathVariable Long id) {
        return Map.of("id", movieService.delete(id));
    }
}
