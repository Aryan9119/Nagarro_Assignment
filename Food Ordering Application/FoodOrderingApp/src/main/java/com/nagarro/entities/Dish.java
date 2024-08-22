package com.nagarro.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dishId;

    private String name;
    private String description;
    private double price;
    private boolean availability;
    private double averageRating;
    private int stockQuantity;
    private int orderCount; 
    


	@ManyToOne
    @JoinColumn(name = "cuisine")
    private Cuisine cuisine;
    
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Feedback> feedbacks;
    
    public void incrementOrderCount() {
        this.orderCount++;
    }

	public Dish() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
    public Dish(Long dishId, String name, String description, double price, boolean availability, double averageRating,
			int stockQuantity, int orderCount, Cuisine cuisine, List<Feedback> feedbacks) {
		super();
		this.dishId = dishId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.availability = availability;
		this.averageRating = averageRating;
		this.stockQuantity = stockQuantity;
		this.orderCount = orderCount;
		this.cuisine = cuisine;
		this.feedbacks = feedbacks;
	}




	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public Long getDishId() {
		return dishId;
	}
	public void setDishId(Long id) {
		this.dishId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}
	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
	

	

    
}

