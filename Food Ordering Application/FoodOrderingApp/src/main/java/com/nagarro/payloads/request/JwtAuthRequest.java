package com.nagarro.payloads.request;

public class JwtAuthRequest {
	private String username;
	private String password;
	public JwtAuthRequest() {}
	public JwtAuthRequest(String userName, String password) {
		super();
		this.username = userName;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
