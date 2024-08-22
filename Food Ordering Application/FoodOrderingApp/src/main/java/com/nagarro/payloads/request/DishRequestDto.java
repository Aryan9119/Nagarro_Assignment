package com.nagarro.payloads.request;

import java.util.List;

import com.nagarro.entities.Cuisine;
import com.nagarro.entities.Feedback;

public class DishRequestDto {
	
    private String name;
    private String description;
    private double price;
    private CuisineDto cuisine;
    private boolean availability;
    public double averageRating;
    private List<FeedbackDto> feedbacks;
    
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
	public CuisineDto getCuisine() {
		return cuisine;
	}
	public void setCuisine(CuisineDto cuisine) {
		this.cuisine = cuisine;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	public List<FeedbackDto> getFeedbacks() {
		return feedbacks;
	}
	public void setFeedbacks(List<FeedbackDto> feedbacks) {
		this.feedbacks = feedbacks;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	
    
    

}
