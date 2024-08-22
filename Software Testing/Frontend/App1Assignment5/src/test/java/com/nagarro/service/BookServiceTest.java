package com.nagarro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import com.entity.Book;
import com.service.BookService;


public class BookServiceTest {

    private BookService bookService;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        restTemplate = mock(RestTemplate.class);
        bookService = new BookService();
        bookService.setRestTemplate(restTemplate);
    }

    @Test
    public void testRetrieveBooks() {

        Book book1 = new Book(1, "Book1", "Author1", "2023-05-10");
        Book book2 = new Book(2, "Book2", "Author2", "2023-05-12");
        Book[] mockBooks = { book1, book2 };

        String url = "http://localhost:8082/book";
        when(restTemplate.getForObject(url, Book[].class)).thenReturn(mockBooks);

        List<Book> actualBooks = bookService.retrieveBooks();

        assertEquals(Arrays.asList(mockBooks), actualBooks);
    }

    @Test
    public void testDeleteBook() {
        int bookCode = 1;
        String url = "http://localhost:8082/book/{bookcode}";

        bookService.deleteBook(bookCode);

        verify(restTemplate).delete(url, bookCode);
    }

    @Test
    public void testSaveBook() {
        Book bookToSave = new Book(1, "Book1", "Author1", "2023-05-10");
        String method = "POST";

        when(restTemplate.postForObject(anyString(), any(Book.class), eq(Book.class)))
                .thenReturn(bookToSave);

        Book savedBook = bookService.saveBook(bookToSave, method);
        
        assertEquals(bookToSave.getBookName(), savedBook.getBookName());
        assertEquals(bookToSave.getBookCode(), savedBook.getBookCode());

        verify(restTemplate).postForObject(anyString(), any(Book.class), eq(Book.class));
    }
}
