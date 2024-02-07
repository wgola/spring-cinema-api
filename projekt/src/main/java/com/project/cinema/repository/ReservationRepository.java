package com.project.cinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.cinema.dto.reservation.ReservationCountPerUsername;
import com.project.cinema.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
            SELECT r.customerFullName AS customerFullName, COUNT(r.customerFullName) as count
            FROM Reservation r
            GROUP BY r.customerFullName
            """)
    List<ReservationCountPerUsername> getReservationsCountByUsername();
}
