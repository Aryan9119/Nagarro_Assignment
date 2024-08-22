package com.nagarro.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String username;
    private String email;
    private String address;
    
    private String password;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
    
    @OneToMany(mappedBy = "customer")
    private List<Feedback> feedbacks;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
	joinColumns = @JoinColumn(name = "user", referencedColumnName = "customerId"),
	inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "roleId"))
	private Set<Role> roles = new HashSet<>();

	public Customer() {
		super();
	}





	public Customer(Long customerId, String username, String email, String address, String password, List<Order> orders,
			List<Feedback> feedbacks, Set<Role> roles) {
		super();
		this.customerId = customerId;
		this.username = username;
		this.email = email;
		this.address = address;
		this.password = password;
		this.orders = orders;
		this.feedbacks = feedbacks;
		this.roles = roles;
	}





	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long id) {
		this.customerId = id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	

	public Set<Role> getRoles() {
		return roles;
	}



	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}



	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}



	public void setFeedback(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}



//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		List<SimpleGrantedAuthority> authories = this.roles.stream()
//				.map((role) -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
//		return authories;
//	}



    
    
}

