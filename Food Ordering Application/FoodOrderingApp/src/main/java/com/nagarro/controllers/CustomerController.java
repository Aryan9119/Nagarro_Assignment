package com.nagarro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.payloads.request.CustomerRequest;
import com.nagarro.payloads.response.ApiResponse;
import com.nagarro.payloads.response.CustomerResponse;
import com.nagarro.services.CustomerService;
/**
 * Controller class for handling HTTP requests related to customers.
 * 
 * Provides endpoints to create, update, delete, and retrieve customer information.
 * 
 * @author Aryan Verma
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    
    /**
     * Creates a new customer.
     * 
     * @param customerRequest the details of the customer to be created
     * @return a response entity with a success message and the unique ID of the created customer
     */
 	@PostMapping("/register")
 	public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
 		
 		Long customerId = this.customerService.createCustomer(customerRequest);
 		return new ResponseEntity<ApiResponse>(new ApiResponse("User Added Successfully with unique id - "+ customerId, true), HttpStatus.OK);
 	}
 	
 	/**
     * Updates an existing customer.
     * 
     * @param customerRequest the details of the customer to be updated
     * @param uid the unique ID of the customer to be updated
     * @return a response entity with a success message and the unique ID of the updated customer
     */

 	@PutMapping("/{customerId}")
 	@PreAuthorize("hasRole('ADMIN')")
 	public ResponseEntity<ApiResponse> updateCustomer(@RequestBody CustomerResponse customerRequest, @PathVariable("customerId") Long uid) {
 		Long customerId = this.customerService.updateCustomer(customerRequest, uid);
 		return new ResponseEntity<ApiResponse>(new ApiResponse("User Updated Successfully with unique id - "+ customerId, true), HttpStatus.OK);
 	}
 	
 	/**
     * Deletes an existing customer.
     * 
     * @param customerId the unique ID of the customer to be deleted
     * @return a response entity with a success message
     */
 	@DeleteMapping("/{customerId}")
 	@PreAuthorize("hasRole('ADMIN')")
 	public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("customerId") Long customerId) {
 		this.customerService.deleteCustomer(customerId);
 		return new ResponseEntity<ApiResponse>(new ApiResponse("Customer deleted Successfully", true), HttpStatus.OK);
 	}
 	
 	/**
     * Retrieves all customers.
     * 
     * @return a response entity containing a list of all customers
     */
 	@GetMapping
 	@PreAuthorize("hasRole('ADMIN')")
 	public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
 		return ResponseEntity.ok(this.customerService.getAllCustomers());
 	}
 	
 	/**
     * Retrieves a single customer by ID.
     * 
     * @param customerId the unique ID of the customer to be retrieved
     * @return a response entity containing the details of the requested customer
     */
 	@GetMapping("/{customerId}")
 	@PreAuthorize("authentication.principal.customerId == #customerId")
 	public ResponseEntity<CustomerResponse> getSingleCustomer(@PathVariable("customerId") Long customerId) {
 		return ResponseEntity.ok(this.customerService.getCustomerById(customerId));
 	}
}

