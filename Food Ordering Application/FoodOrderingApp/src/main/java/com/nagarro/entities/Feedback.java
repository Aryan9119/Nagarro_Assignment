package com.nagarro.entities;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "feedbacks",uniqueConstraints = @UniqueConstraint(columnNames = {"customer_id", "dish_id"}))
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feedbackId;

	private String review;
	
	private double rating;

	@ManyToOne
	@JoinColumn(name = "dish_id")
	private Dish dish;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	

	public Feedback(int feedbackId, String review, double rating, Dish dish, Customer customer) {
		super();
		this.feedbackId = feedbackId;
		this.review = review;
		this.rating = rating;
		this.dish = dish;
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
