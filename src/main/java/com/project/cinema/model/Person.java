package com.project.cinema.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Person extends AbstractEntity {

    @Column(length = 20, nullable = false)
    private String firstName;

    @Column(length = 20, nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @ManyToMany(mappedBy = "directors")
    private List<Movie> directedMovies;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> actedInMovies;

    @Override
    public void update(AbstractEntity other) {
        if (other instanceof Person p) {

            if (p.firstName != null && !p.firstName.equals(firstName)) {
                firstName = p.firstName;
            }

            if (p.lastName != null && !p.lastName.equals(lastName)) {
                lastName = p.lastName;
            }

            if (p.dateOfBirth != null && p.dateOfBirth != dateOfBirth) {
                dateOfBirth = p.dateOfBirth;
            }
        }
    }
}
