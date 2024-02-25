package com.project.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cinema.model.Screening;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
}
