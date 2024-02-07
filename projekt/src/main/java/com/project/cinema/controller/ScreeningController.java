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

import com.project.cinema.dto.screening.ScreeningReadDto;
import com.project.cinema.dto.screening.ScreeningUpdateDto;
import com.project.cinema.dto.screening.ScreeningWriteDto;
import com.project.cinema.mapper.ScreeningMapper;
import com.project.cinema.model.Movie;
import com.project.cinema.model.Screening;
import com.project.cinema.service.movie.MovieService;
import com.project.cinema.service.screening.ScreeningService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/screening")
public class ScreeningController {

    private final ScreeningService screeningService;
    private final MovieService movieService;
    private final ScreeningMapper screeningMapper;

    public ScreeningController(
            ScreeningService screeningService,
            MovieService movieService,
            ScreeningMapper screeningMapper) {
        this.screeningService = screeningService;
        this.movieService = movieService;
        this.screeningMapper = screeningMapper;
    }

    @GetMapping
    public List<ScreeningReadDto> getAllScreenings() {
        return screeningService.getAll().stream()
                .map(screeningMapper::toReadDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ScreeningReadDto getScreeningById(@PathVariable Long id) {
        Screening foundScreening = screeningService.getById(id);

        return screeningMapper.toReadDto(foundScreening);
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public ScreeningReadDto createScreening(@RequestBody @Valid ScreeningWriteDto screening) {
        Screening screeningToCreate = screeningMapper.fromWriteDto(screening);

        Movie movie = movieService.getById(screening.movieId());

        screeningToCreate.setMovie(movie);

        Screening createdScreening = screeningService.save(screeningToCreate);

        return screeningMapper.toReadDto(createdScreening);
    }

    @PutMapping("/{id}")
    public ScreeningReadDto updateScreening(
            @PathVariable Long id,
            @RequestBody @Valid ScreeningUpdateDto screening) {

        Screening screeningToUpdate = Screening.builder()
                .screeningDate(screening.screeningDate().orElse(null))
                .screeningTime(screening.screeningTime().orElse(null))
                .movie(screening.movieId().map(movieService::getById).orElse(null))
                .build();

        Screening updatedScreening = screeningService.update(id, screeningToUpdate);

        return screeningMapper.toReadDto(updatedScreening);
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteScreening(@PathVariable Long id) {
        return Map.of("id", screeningService.delete(id));
    }
}
