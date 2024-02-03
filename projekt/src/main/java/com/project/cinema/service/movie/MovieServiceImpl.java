package com.project.cinema.service.movie;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.cinema.exception.EntityNotFoundException;
import com.project.cinema.exception.ErrorCreatingEntityException;
import com.project.cinema.exception.ErrorDeletingEntityException;
import com.project.cinema.model.Movie;
import com.project.cinema.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie", id));
    }

    @Override
    public Movie save(Movie movie) {
        try {
            return movieRepository.save(movie);
        } catch (Exception e) {
            throw new ErrorCreatingEntityException("Movie", e.getClass().getSimpleName());
        }
    }

    @Override
    public Movie update(Long id, Movie movie) {
        Movie movieToUpdate = getById(id);

        movieToUpdate.update(movie);

        return save(movieToUpdate);
    }

    @Override
    public Long delete(Long id) {
        Movie movieToDelete = getById(id);

        try {
            movieRepository.delete(movieToDelete);
            return id;
        } catch (Exception e) {
            throw new ErrorDeletingEntityException("Movie", id);
        }
    }
}
