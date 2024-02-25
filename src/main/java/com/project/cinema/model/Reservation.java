package com.project.cinema.model;

import static jakarta.persistence.FetchType.EAGER;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class Reservation extends AbstractEntity {

    @Column(length = 30, nullable = false)
    private String customerFullName;

    @ManyToMany(fetch = EAGER)
    private Set<Seat> takenSeats;

    @ManyToOne(fetch = EAGER)
    private Screening screening;

    @Override
    public void update(AbstractEntity other) {
        if (other instanceof Reservation r) {

            if (r.customerFullName != null && !r.customerFullName.equals(customerFullName)) {
                customerFullName = r.customerFullName;
            }

            if (r.takenSeats != null && !r.takenSeats.isEmpty()) {
                takenSeats = r.takenSeats;
            }

            if (r.screening != null) {
                screening = r.screening;
            }
        }
    }
}
