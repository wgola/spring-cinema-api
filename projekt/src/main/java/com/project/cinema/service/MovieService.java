package com.project.cinema.service;

import org.springframework.stereotype.Service;

import com.project.cinema.repository.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
}
