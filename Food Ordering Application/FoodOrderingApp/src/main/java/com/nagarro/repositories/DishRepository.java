package com.nagarro.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entities.Cuisine;
import com.nagarro.entities.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
		
	List<Dish> findByCuisine(Cuisine cuisine);
	
	List<Dish> findAllByNameIn(List<String> name);
	
	Dish findByName(String dishName);
	
	@EntityGraph(attributePaths = "feedbacks")
    Dish findByDishId(Long dishId);
	
}
