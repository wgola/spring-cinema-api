package com.project.cinema.criteria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.data.jpa.domain.Specification;

import com.project.cinema.model.Genre;
import com.project.cinema.model.Language;
import com.project.cinema.model.Movie;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieSearchCriteria implements Supplier<Specification<Movie>> {

    private static final String RELEASE_DATE_FIELD_NAME = "releaseDate";
    private static final String LANGUAGE_FIELD_NAME = "language";
    private static final String GENRES_FIELD_NAME = "genres";
    private static final String TITLE_FIELD_NAME = "title";

    private String title;
    private List<Long> genresIds;
    private Long languageId;
    private LocalDate releaseDate;

    @Override
    public Specification<Movie> get() {

        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            addTitlePredicate(root, builder, predicates);

            addGenresPredicate(root, predicates);

            addLanguagePredicate(root, builder, predicates);

            addReleaseDatePredicate(root, builder, predicates);

            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }

    private void addReleaseDatePredicate(Root<Movie> root, CriteriaBuilder builder, List<Predicate> predicates) {
        if (releaseDate != null) {
            predicates.add(builder.greaterThan(root.get(RELEASE_DATE_FIELD_NAME), releaseDate));
        }
    }

    private void addLanguagePredicate(Root<Movie> root, CriteriaBuilder builder, List<Predicate> predicates) {
        if (languageId != null) {
            Join<Language, Movie> languageMovie = root.join(LANGUAGE_FIELD_NAME);
            predicates.add(
                    builder.equal(languageMovie.get("id"), languageId));
        }
    }

    private void addGenresPredicate(Root<Movie> root, List<Predicate> predicates) {
        if (isNotEmpty(genresIds)) {
            Join<Genre, Movie> genreMovie = root.join(GENRES_FIELD_NAME);
            predicates.add(genreMovie.get("id").in(genresIds));
        }
    }

    private void addTitlePredicate(Root<Movie> root, CriteriaBuilder builder, List<Predicate> predicates) {
        if (title != null) {
            predicates.add(builder.like(root.get(TITLE_FIELD_NAME), "%" + title + "%"));
        }
    }

    private boolean isNotEmpty(List<?> list) {
        return list != null && !list.isEmpty();
    }
}
