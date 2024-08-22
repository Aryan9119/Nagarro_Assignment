package com.nagarro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entities.Cuisine;

public interface CuisineRepository extends JpaRepository<Cuisine, Integer> {

	Optional<Cuisine> findByCuisineTitle(String cuisine);

}
