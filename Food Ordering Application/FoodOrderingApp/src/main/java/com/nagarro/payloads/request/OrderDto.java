package com.nagarro.payloads.request;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.nagarro.payloads.response.CustomerResponse;

public class OrderDto {
	
  private Long orderId;
  private String deliveryAddress;
  private String deliveryStatus;
  private Date orderDate;
  private double totalPrice;
  
  private CustomerResponse customer;
  private List<DishRequestDto> dishes;
  
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public CustomerResponse getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerResponse customer) {
		this.customer = customer;
	}
	public List<DishRequestDto> getDishes() {
		return dishes;
	}
	public void setDishes(List<DishRequestDto> dishes) {
		this.dishes = dishes;
	}
	
  
  

}
