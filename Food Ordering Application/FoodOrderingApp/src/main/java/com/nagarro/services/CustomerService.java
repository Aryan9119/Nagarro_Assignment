package com.nagarro.services;

import java.util.List;

import com.nagarro.payloads.request.CustomerRequest;
import com.nagarro.payloads.response.CustomerResponse;

/**
 * Service interface for managing customers.
 * Provides methods for creating, updating, deleting, and retrieving customer information.
 */
public interface CustomerService {

    /**
     * Creates a new customer.
     *
     * @param customer the customer request data transfer object containing the details of the customer to be created
     * @return the unique identifier of the created customer
     */
    Long createCustomer(CustomerRequest customer);

    /**
     * Updates an existing customer.
     *
     * @param customer the updated customer response data transfer object
     * @param customerId the unique identifier of the customer to be updated
     * @return the unique identifier of the updated customer
     */
    Long updateCustomer(CustomerResponse customer, Long customerId);

    /**
     * Retrieves a specific customer by their unique identifier.
     *
     * @param customerId the unique identifier of the customer to be retrieved
     * @return the customer response data transfer object containing the details of the retrieved customer
     */
    CustomerResponse getCustomerById(Long customerId);

    /**
     * Retrieves a list of all customers.
     *
     * @return a list of customer response data transfer objects containing the details of all customers
     */
    List<CustomerResponse> getAllCustomers();

    /**
     * Deletes a customer.
     *
     * @param customerId the unique identifier of the customer to be deleted
     */
    void deleteCustomer(Long customerId);
}
