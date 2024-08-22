package com.nagarro.payloads.request;

import com.nagarro.payloads.response.CustomerResponse;

public class FeedbackDto {
	
	private int feedbackId;

	private String review;
	
	private double rating;
	
	private CustomerResponse customer;

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

	public CustomerResponse getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerResponse customer) {
		this.customer = customer;
	}
	
	
	

}
