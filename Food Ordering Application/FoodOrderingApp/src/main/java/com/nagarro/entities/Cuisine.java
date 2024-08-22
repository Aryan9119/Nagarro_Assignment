package com.nagarro.entities;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table
public class Cuisine {

	
	@Id
	@Column(name="cuisine",length = 100,nullable = false)
	private String cuisineTitle;
	
	@OneToMany(mappedBy = "cuisine")
	private List<Dish> dishes;

	
	public Cuisine(String cuisineTitle, List<Dish> dishes) {
		super();
		this.cuisineTitle = cuisineTitle;
		this.dishes = dishes;
	}

	public String getCuisineTitle() {
		return cuisineTitle;
	}

	public void setCuisineTitle(String cuisineTitle) {
		this.cuisineTitle = cuisineTitle;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}


	public Cuisine() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
