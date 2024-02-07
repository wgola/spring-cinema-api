package com.project.cinema.service.movie;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.project.cinema.model.Movie;

public interface MovieService {

    Page<Movie> getAll(Supplier<Specification<Movie>> movieSearchCriteria, Pageable pageable);

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
