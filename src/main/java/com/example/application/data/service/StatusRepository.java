package com.example.application.data.service;

import com.example.application.data.entity.Status;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {

	Optional<Status> findById(Iterable<Integer> id);

}