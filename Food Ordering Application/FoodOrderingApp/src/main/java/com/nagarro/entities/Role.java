package com.nagarro.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Role {

	@Id	
	private int roleid;
	
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
    private Set<Customer> customers = new HashSet<>();
	
	public Set<Customer> getAllCustomers(){
		return customers;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
}
