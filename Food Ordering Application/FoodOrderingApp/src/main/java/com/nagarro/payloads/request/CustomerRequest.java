package com.nagarro.payloads.request;

import java.util.HashSet;
import java.util.Set;

import com.nagarro.entities.Role;

public class CustomerRequest {
    private String username;
    private String email;
    private String address;
    private String password;
    
    private Set<RoleDto> roles = new HashSet<>();
    
	public CustomerRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public CustomerRequest(String username, String email, String address, String password, Set<RoleDto> roles) {
		super();
		this.username = username;
		this.email = email;
		this.address = address;
		this.password = password;
		this.roles = roles;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	
	
    
    
}
