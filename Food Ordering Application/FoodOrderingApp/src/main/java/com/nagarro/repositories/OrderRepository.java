package com.nagarro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entities.Customer;
import com.nagarro.entities.Dish;
import com.nagarro.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByCustomer(Customer customer);
	List<Order> findByCustomerAndDishes(Customer customer, Dish dish);

}
