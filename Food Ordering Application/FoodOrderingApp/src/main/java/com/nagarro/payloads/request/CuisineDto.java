package com.nagarro.payloads.request;

public class CuisineDto {
	
	private String cuisineTitle;

	public String getCuisineTitle() {
		return cuisineTitle;
	}

	public void setCuisineTitle(String cuisineTitle) {
		this.cuisineTitle = cuisineTitle;
	}

	public CuisineDto(String cuisineTitle) {
		super();
		this.cuisineTitle = cuisineTitle;
	}

	public CuisineDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
