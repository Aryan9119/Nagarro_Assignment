package com.nagarro.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

import com.controller.DeleteController;
import com.entity.Book;
import com.service.BookService;

@ExtendWith(MockitoExtension.class)
public class DeleteControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private DeleteController deleteController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(deleteController).build();
    }

    @Test
    public void testDeleteBook() throws Exception {

        Book bookToDelete = new Book(1, "Book1", "Author1", "2023-05-10");

        doNothing().when(bookService).deleteBook(bookToDelete.getBookCode());

        Book book2 = new Book(2, "Book2", "Author2", "2023-05-12");
        List<Book> remainingBooks = Arrays.asList(book2);
        when(bookService.retrieveBooks()).thenReturn(remainingBooks);

        mockMvc.perform(post("/Delete")
            .param("bookCode", String.valueOf(bookToDelete.getBookCode())))
            .andExpect(status().isOk())
            .andExpect(view().name("bookListing"))
            .andExpect(request().sessionAttribute("books", remainingBooks));

        verify(bookService, times(1)).deleteBook(bookToDelete.getBookCode());

        verify(bookService, times(1)).retrieveBooks();
    }
}
