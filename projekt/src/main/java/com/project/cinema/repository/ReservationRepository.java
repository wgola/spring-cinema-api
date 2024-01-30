package com.project.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cinema.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
