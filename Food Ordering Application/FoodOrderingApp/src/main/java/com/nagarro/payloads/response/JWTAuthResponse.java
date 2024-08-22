package com.nagarro.payloads.response;

public class JWTAuthResponse {
	
	private String token;	
	 
	public JWTAuthResponse() {}

	public JWTAuthResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
	 

}
