package com.nagarro.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.controller.AddController;
import com.entity.Author;
import com.entity.Book;
import com.service.AuthorService;
import com.service.BookService;

@ExtendWith(MockitoExtension.class)
public class AddControllerTest {

    @Mock
    private AuthorService authorService;

    @Mock
    private BookService bookService;

    @Mock
    private HttpSession session;
    
    @Mock
    private Book book;

    @InjectMocks
    private AddController addController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(addController).build();
    }

    @Test
    public void testEditBook() throws Exception {
        Author author1 = new Author();
        author1.setName("Author1");
        Author author2 = new Author();
        author2.setName("Author2");
        List<Author> authors = Arrays.asList(author1, author2);

        when(authorService.retrieveAuthors()).thenReturn(authors);

        mockMvc.perform(post("/Add"))
            .andExpect(status().isOk())
            .andExpect(view().name("AddBook"))
            .andExpect(model().attribute("Author", authors))
            .andExpect(model().attributeExists("date"));

        verify(authorService, times(1)).retrieveAuthors();
    }

    @Test
    public void testAddBook() throws Exception {
        Book book1 = new Book(1, "Book1", "Author1", LocalDate.now().toString());
        List<Book> books = Arrays.asList(book1);

        when(bookService.retrieveBooks()).thenReturn(books);

        mockMvc.perform(post("/Addbook")
            .param("bookCode", "1")
            .param("bookName", "Book1")
            .param("author", "Author1")
            .param("addedOn", LocalDate.now().toString()))
            .andExpect(status().isOk())
            .andExpect(view().name("bookListing"))
            .andExpect(request().sessionAttribute("books", books));

        verify(bookService, times(1)).saveBook(any(Book.class), eq("POST"));
        verify(bookService, times(1)).retrieveBooks();
    }
}
