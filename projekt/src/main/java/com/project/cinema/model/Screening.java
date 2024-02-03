package com.project.cinema.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Screening extends AbstractEntity {

    @Column(nullable = false)
    private LocalDate screeningDate;

    @Column(nullable = false)
    private LocalDate screeningTime;

    @ManyToOne
    private Movie movie;

    @OneToMany(mappedBy = "screening")
    private List<Reservation> reservations;

    @Override
    public void update(AbstractEntity other) {
        if (other instanceof Screening s) {

            if (s.screeningDate != null && s.screeningDate != screeningDate) {
                screeningDate = s.screeningDate;
            }

            if (s.screeningTime != null && s.screeningTime != screeningTime) {
                screeningTime = s.screeningTime;
            }
        }
    }
}
