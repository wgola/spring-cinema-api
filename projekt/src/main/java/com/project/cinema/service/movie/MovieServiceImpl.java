package com.project.cinema.service.movie;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.cinema.exception.EntityNotFoundException;
import com.project.cinema.exception.ErrorCreatingEntityException;
import com.project.cinema.exception.ErrorDeletingEntityException;
import com.project.cinema.model.Genre;
import com.project.cinema.model.Language;
import com.project.cinema.model.Movie;
import com.project.cinema.model.Person;
import com.project.cinema.repository.GenreRepository;
import com.project.cinema.repository.LanguageRepository;
import com.project.cinema.repository.MovieRepository;
import com.project.cinema.service.person.PersonService;

import jakarta.transaction.Transactional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final LanguageRepository languageRepository;
    private final GenreRepository genreRepository;
    private final PersonService personService;

    public MovieServiceImpl(
            MovieRepository movieRepository,
            LanguageRepository languageRepository,
            GenreRepository genreRepository,
            PersonService personService) {
        this.movieRepository = movieRepository;
        this.languageRepository = languageRepository;
        this.genreRepository = genreRepository;
        this.personService = personService;
    }

    @Override
    public Page<Movie> getAll(Supplier<Specification<Movie>> movieSearchCriteria, Pageable pageable) {
        return movieRepository.findAll(movieSearchCriteria.get(), pageable);
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
            e.printStackTrace();
            throw new ErrorCreatingEntityException("Movie", e.getClass().getSimpleName());
        }
    }


    @Override
    @Transactional
    public Movie update(
            Long id,
            Movie movie,
            Optional<String> language,
            Optional<List<String>> genres,
            Optional<List<Long>> actorsIds,
            Optional<List<Long>> directorsIds) {

        Movie movieToUpdate = getById(id);

        List<Genre> mappedGenres = genres.map(genresList -> genresList.stream()
                .map(this::mapGenre)
                .toList()).orElse(null);

        Language mappedLanguage = language.map(this::mapLanguage).orElse(null);

        List<Person> mappedActors = actorsIds.map(actorsIdsList -> actorsIdsList.stream()
                .map(personService::getById)
                .toList()).orElse(null);
        List<Person> mappedDirectors = directorsIds.map(directorsIdsList -> directorsIdsList.stream()
                .map(personService::getById)
                .toList()).orElse(null);

        movie.setGenres(mappedGenres);
        movie.setLanguage(mappedLanguage);
        movie.setActors(mappedActors);
        movie.setDirectors(mappedDirectors);

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

    @Override
    @Transactional
    public Movie create(
            Movie movie,
            List<String> genres,
            String language,
            List<Long> actorsIds,
            List<Long> directorsIds) {

        List<Genre> mappedGenres = genres.stream().map(this::mapGenre).toList();
        Language mappedLanguage = mapLanguage(language);
        List<Person> actors = actorsIds.stream().map(personService::getById).toList();
        List<Person> directors = directorsIds.stream().map(personService::getById).toList();

        movie.setGenres(mappedGenres);
        movie.setLanguage(mappedLanguage);
        movie.setActors(actors);
        movie.setDirectors(directors);

        return save(movie);
    }

    private Language mapLanguage(String language) {
        return languageRepository.findByName(language)
                .orElse(new Language(language));
    }

    private Genre mapGenre(String genre) {
        return genreRepository.findByName(genre)
                .orElse(new Genre(genre));
    }
}
