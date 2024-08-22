package com.nagarro.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.controller.EditController;
import com.entity.Author;
import com.entity.Book;
import com.service.AuthorService;
import com.service.BookService;

@ExtendWith(MockitoExtension.class)
public class EditControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private EditController editController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(editController).build();
    }

    @Test
    public void testEditBook() throws Exception {

        Book book = new Book(1, "Book1", "Author1", "2023-06-15");

        Author author1 = new Author();
        author1.setName("Author1");
        Author author2 = new Author();
        author2.setName("Author2");
        List<Author> authors = Arrays.asList(author1, author2);
        when(authorService.retrieveAuthors()).thenReturn(authors);

        mockMvc.perform(post("/Edit")
            .param("bookcode", "1")
            .param("bookname", "Book1")
            .param("author", "Author1")
            .param("addedOn", "2023-06-15"))
            .andExpect(status().isOk())
            .andExpect(view().name("EditBook"))
            .andExpect(model().attribute("book", book))
            .andExpect(model().attribute("Author", authors));

        verify(authorService, times(1)).retrieveAuthors();
    }

    @Test
    public void testSaveEditedBook() throws Exception {

        Book updatedBook = new Book(1, "Book1", "Author1", "2023-06-15");

        doReturn(updatedBook).when(bookService).saveBook(any(Book.class), any(String.class));
        
        List<Book> bookList = Arrays.asList(updatedBook);
        when(bookService.retrieveBooks()).thenReturn(bookList);

        mockMvc.perform(post("/Editbook")
            .param("bookCode", "1")
            .param("bookName", "Book1")
            .param("author", "Author1")
            .param("addedOn", "2023-06-15"))
            .andExpect(status().isOk())
            .andExpect(view().name("bookListing"))
            .andExpect(request().sessionAttribute("books", bookList));
    }
}
