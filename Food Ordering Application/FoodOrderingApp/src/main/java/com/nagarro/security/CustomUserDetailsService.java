package com.nagarro.security;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.entities.Customer;
import com.nagarro.entities.Role;
import com.nagarro.payloads.request.CustomUserDetails;
import com.nagarro.repositories.CustomerRepository;

/**
 * CustomUserDetailsService is an implementation of the UserDetailsService interface
 * that loads user-specific data from the database.
 * 
 * This service fetches the user details from the database using the CustomerRepository.
 * 
 * Author: Aryan Verma
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private CustomerRepository customerRepository;
    
    /**
     * Loads the user by username and returns a UserDetails object.
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated UserDetails object (never null).
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            logger.error("User not found with username: {}", username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Set<Role> roles = customer.getRoles();
        return new CustomUserDetails(customer, roles);
    }
}
