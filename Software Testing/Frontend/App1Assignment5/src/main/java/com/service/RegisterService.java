package com.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.entity.User;

/**
 * This class represents a service for registering new users. It allows the user
 * to save the information of a new user by sending a POST request to the server
 * 
 * @author aryanverma
 *
 */
@Component
public class RegisterService {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	/**
	 * Saves the information of a new user to the server.
	 * @param user The user object containing the information of the user to be saved.
	 */
	public User saveUser(User user) {
		
		String url = "http://localhost:8082/users";
	    
	    return restTemplate.postForObject(url, user, User.class);
	    
	}

}
