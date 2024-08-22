package com.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.entity.Author;
import com.entity.Book;

/**
 * Service class to retrieve authors from a REST API  
 */
@Component
public class AuthorService {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieves all authors from the REST API.
     * 
     * @return a list of all authors
     */
    public List<Author> retrieveAuthors() {
    	
        String queryUrl = "http://localhost:8082/author";
        Author[] authors = restTemplate.getForObject(queryUrl, Author[].class);
		return Arrays.asList(authors);
    }
}
