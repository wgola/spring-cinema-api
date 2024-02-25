package com.project.cinema.model;

import jakarta.persistence.Entity;
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
public class Seat extends AbstractEntity {

    private int seatNumber;
    private int rowNumber;

    @Override
    public void update(AbstractEntity other) {
        if (other instanceof Seat s) {

            if (s.seatNumber != seatNumber) {
                seatNumber = s.seatNumber;
            }

            if (s.rowNumber != rowNumber) {
                rowNumber = s.rowNumber;
            }
        }
    }
}
