package com.project.cinema.service.movie;

import java.util.List;

import com.project.cinema.model.Movie;

public interface MovieService {

    List<Movie> getAll();

    Movie getById(Long id);

    Movie save(Movie movie);

    Movie update(Long id, Movie movie);

    Long delete(Long id);
}
