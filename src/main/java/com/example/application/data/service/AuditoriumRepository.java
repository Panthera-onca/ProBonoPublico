package com.example.application.data.service;

import com.example.application.data.entity.Auditorium;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriumRepository extends JpaRepository<Auditorium, Integer>{

	Optional<Auditorium> findById(Iterable<Integer> id);

}
