package com.nagarro.payloads.response;

public class CustomerResponse {
	
	private String username;
    private String email;
    private String address;
    
    
    
    public CustomerResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerResponse(String username, String email, String address) {
		super();
		this.username = username;
		this.email = email;
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
