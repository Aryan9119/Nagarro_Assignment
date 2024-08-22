package com.nagarro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findByUsername(String username);

}
