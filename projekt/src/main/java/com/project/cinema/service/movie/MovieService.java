package com.project.cinema.service.movie;

import java.util.List;
import java.util.Optional;

import com.project.cinema.model.Movie;

public interface MovieService {

    List<Movie> getAll();

    Movie getById(Long id);

    Movie save(Movie movie);

    Movie update(
            Long id,
            Movie movie,
            Optional<String> language,
            Optional<List<String>> genres,
            Optional<List<Long>> actorsIds,
            Optional<List<Long>> directorsIds);

    Long delete(Long id);

    Movie create(
            Movie movie,
            List<String> genres,
            String language,
            List<Long> actorsIds,
            List<Long> directorsIds);
}
