package com.project.cinema.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.cinema.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query(value = """
            SELECT seat.*
            FROM
                seat
                JOIN reservation_taken_seats ON seat.id = reservation_taken_seats.taken_seats_id
                JOIN reservation ON reservation_taken_seats.reservation_id = reservation.id
                JOIN screening ON screening.id = reservation.screening_id
            WHERE
                screening.id = :screeningId
            """, nativeQuery = true)
    Set<Seat> getTakenSeatsForScreening(@Param("screeningId") Long screeningId);
}
