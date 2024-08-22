package com.nagarro.payloads.response;

public class FeedbackResponse {
	
	private CustomerResponse customer;
	private String review;	
	private double rating;
	
	public String getCustomerName() {
		return customer.getUsername();
	}
	public void setCustomerName(CustomerResponse customerName) {
		this.customer = customerName;
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
	
	

}
