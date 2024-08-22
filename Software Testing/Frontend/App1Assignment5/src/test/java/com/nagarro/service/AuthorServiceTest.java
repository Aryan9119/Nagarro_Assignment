package com.nagarro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.entity.Author;
import com.service.AuthorService;

public class AuthorServiceTest {
	
	 private AuthorService authorService;
	 private RestTemplate restTemplate;
	 
	    @BeforeEach
	    public void setup() {
	        restTemplate = mock(RestTemplate.class);
	        authorService = new AuthorService();
	        authorService.setRestTemplate(restTemplate);
	    }
	    
	    @Test
	    public void testRetrieveBooks() {

	        Author author1 = new Author("John");
	        Author author2 = new Author("Jack");
	        Author[] mockAuthors = { author1, author2 };

	        String url = "http://localhost:8082/author";
	        when(restTemplate.getForObject(url, Author[].class)).thenReturn(mockAuthors);

	        List<Author> actualAuthors = authorService.retrieveAuthors();
	        
	        assertEquals(Arrays.asList(mockAuthors), actualAuthors);
	    }

}
