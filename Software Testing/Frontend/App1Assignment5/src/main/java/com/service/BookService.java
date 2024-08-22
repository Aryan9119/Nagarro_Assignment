package com.service;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.entity.Book;

/**
 *
 * 
 * This class provides methods to interact with the Book API.
 * 
 * @author aryanverma
 *
 */
@Component
public class BookService {

	private RestTemplate restTemplate = new RestTemplate();
	
	public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	/**
	 * 
	 * Retrieves a list of all books from the Book API.
	 * 
	 * @return a list of Book objects
	 */
	public List<Book> retrieveBooks() {
		String url = "http://localhost:8082/book";
		Book[] books = restTemplate.getForObject(url, Book[].class);
		return Arrays.asList(books);
	}

	/**
	 * Deletes a book from the Book API by bookcode.
	 * 
	 * @param bookcode the bookcode of the book to be deleted
	 */
	public void deleteBook(int bookcode) {
		String url = "http://localhost:8082/book/{bookcode}";
		restTemplate.delete(url, bookcode);
	}

	/**
	 * 
	 * Saves a book to the Book API.
	 * 
	 * @param book   the Book object to be saved
	 * @param method the HTTP method to use (POST or PUT)
	 */
	public Book saveBook(Book book, String method) {
	    String url = "http://localhost:8082/book";
	    if ("PUT".equals(method)) {
	        restTemplate.put(url, book);
	        return book;
	    } else {
	        return restTemplate.postForObject(url, book, Book.class);
	    }
	}

}
