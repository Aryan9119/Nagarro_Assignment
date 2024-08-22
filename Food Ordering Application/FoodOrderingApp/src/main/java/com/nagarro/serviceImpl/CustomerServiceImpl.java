package com.nagarro.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.entities.Customer;
import com.nagarro.exceptions.ApiException;
import com.nagarro.exceptions.ResourceNotFoundException;
import com.nagarro.payloads.request.CustomerRequest;
import com.nagarro.payloads.response.CustomerResponse;
import com.nagarro.repositories.CustomerRepository;
import com.nagarro.repositories.RoleRepository;
import com.nagarro.services.CustomerService;

/**
 * Service implementation for managing customers.
 * This class provides methods for creating, updating, retrieving, and deleting customers.
 * 
 * @author Aryan Verma
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	/**
	 * Creates a new customer with the given details.
	 *
	 * @param customerRequest the details of the customer to create
	 * @return the ID of the created customer
	 * @throws ApiException if the customer username or email is invalid or if the username already exists
	 */
	@Override
	public Long createCustomer(CustomerRequest customerRequest) {
		
		if (customerRequest.getUsername() == null || !customerRequest.getUsername().matches("^[a-zA-Z ]+$")) {
            throw new ApiException("Enter valid name");
        }
        if (customerRequest.getEmail() == null || !customerRequest.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")) {
            throw new ApiException("Enter valid email");
        }
        Customer customer1 = customerRepository.findByUsername(customerRequest.getUsername());
        if (customer1 != null) {
            throw new ApiException("Customer with given username already exists, try different one.");
        }
        Customer customer = this.modelMapper.map(customerRequest, Customer.class);
		customer.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
		Customer savedUser = this.customerRepository.save(customer);
		return savedUser.getCustomerId();
	}

	/**
	 * Updates the details of an existing customer.
	 *
	 * @param customerRequest the new details of the customer
	 * @param customerId the ID of the customer to update
	 * @return the ID of the updated customer
	 * @throws ResourceNotFoundException if the customer with the given ID is not found
	 * @throws ApiException if the email is invalid or the username is modified
	 */
	@Override
	public Long updateCustomer(CustomerResponse customerRequest, Long customerId) {
		Customer customer = this.customerRepository.findById(customerId).orElseThrow(() ->
        new ResourceNotFoundException("Customer with given id is not found on server !! : " + customerId));
		
        if (customerRequest.getEmail() == null || !customerRequest.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")) {
            throw new ApiException("Enter valid email");
        }
        if(customerRequest.getUsername()==customer.getUsername()) {
			customer.setEmail(customerRequest.getEmail());
			customer.setAddress(customerRequest.getAddress());
	
			Customer updatedCustomer = this.customerRepository.save(customer);
			
			return updatedCustomer.getCustomerId();
        }else {
        	throw new ApiException("Username can't be modified");
        }
		
	}

	/**
	 * Retrieves the customer with the specified ID.
	 *
	 * @param customerId the ID of the customer to retrieve
	 * @return the details of the customer
	 * @throws ResourceNotFoundException if the customer with the given ID is not found
	 */
	@Override
	public CustomerResponse getCustomerById(Long customerId) {
		// TODO Auto-generated method stub
		Customer customer = this.customerRepository.findById(customerId).orElseThrow(() ->
        new ResourceNotFoundException("Customer with given id is not found on server !! : " + customerId));

		return this.modelMapper.map(customer, CustomerResponse.class);
	}

	/**
	 * Retrieves all customers.
	 *
	 * @return a list of all customers
	 */
	@Override
	public List<CustomerResponse> getAllCustomers(){
		// TODO Auto-generated method stub
		List<Customer> customers = this.customerRepository.findAll();
		List<CustomerResponse> customerDtos = customers.stream().map(customer -> this.modelMapper.map(customer, CustomerResponse.class)).collect(Collectors.toList());

		return customerDtos;
	}

	/**
	 * Deletes the customer with the specified ID.
	 *
	 * @param customerId the ID of the customer to delete
	 * @throws ResourceNotFoundException if the customer with the given ID is not found
	 */
	@Override
	@Transactional
	public void deleteCustomer(Long customerId) {
		// TODO Auto-generated method stub
		Customer customer = this.customerRepository.findById(customerId).orElseThrow(() ->
        new ResourceNotFoundException("Customer with given id is not found on server !! : " + customerId));
		customer.getRoles().removeAll(getAllCustomers());
	
		this.customerRepository.delete(customer);

	}
	

}
