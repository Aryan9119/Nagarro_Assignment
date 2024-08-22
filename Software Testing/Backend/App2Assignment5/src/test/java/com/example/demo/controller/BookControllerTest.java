package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.controller.BookController;
import com.entity.Book;
import com.service.BookService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    
    @Test
    public void testSaveOrUpdateBook() throws Exception {
        Book book = new Book(1, "Book 1", "Author 1", "2022-06-07");

        when(bookService.saveOrUpdateBook(any(Book.class))).thenAnswer(invocation -> {
            Book argument = invocation.getArgument(0);
            assertThat(argument.getBookCode()).isEqualTo(1);
            assertThat(argument.getBookName()).isEqualTo("Book 1");
            assertThat(argument.getAuthor()).isEqualTo("Author 1");
            assertThat(argument.getAddedOn()).isEqualTo("2022-06-07");
            return argument;
        });

        mockMvc.perform(MockMvcRequestBuilders.put("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"bookCode\":1,\"bookName\":\"Book 1\",\"author\":\"Author 1\",\"addedOn\":\"2022-06-07\"}"))
                .andExpect(status().isOk())
        		.andExpect(jsonPath("$.bookCode").value(1))
			    .andExpect(jsonPath("$.bookName").value("Book 1"))
			    .andExpect(jsonPath("$.author").value("Author 1"))
			    .andExpect(jsonPath("$.addedOn").value("2022-06-07"));
    }

    @Test
    public void testGetBooks() throws Exception {
        Book book1 = new Book(1, "Book 1", "Author 1", "2022-06-07");
        Book book2 = new Book(2, "Book 2", "Author 2", "2022-06-08");

        List<Book> books = Arrays.asList(book1, book2);
        when(bookService.getBooks()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/book")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].bookCode").value(1))
                .andExpect(jsonPath("$[0].bookName").value("Book 1"))
                .andExpect(jsonPath("$[0].author").value("Author 1"))
                .andExpect(jsonPath("$[0].addedOn").value("2022-06-07"))
                .andExpect(jsonPath("$[1].bookCode").value(2))
                .andExpect(jsonPath("$[1].bookName").value("Book 2"))
                .andExpect(jsonPath("$[1].author").value("Author 2"))
                .andExpect(jsonPath("$[1].addedOn").value("2022-06-08"));

        verify(bookService, times(1)).getBooks();
    }

    @Test
    public void testGetBook() throws Exception {
        int bookCode = 1;
        Book book = new Book(bookCode, "Book 1", "Author 1", "2022-06-07");

        when(bookService.getBook(bookCode)).thenReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/{bookCode}", bookCode)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookCode").value(bookCode))
                .andExpect(jsonPath("$.bookName").value("Book 1"))
                .andExpect(jsonPath("$.author").value("Author 1"))
                .andExpect(jsonPath("$.addedOn").value("2022-06-07"));

        verify(bookService, times(1)).getBook(bookCode);
    }

    @Test
    public void testDeleteBook() throws Exception {
        int bookCode = 1;

        doNothing().when(bookService).deleteBook(bookCode);

        mockMvc.perform(MockMvcRequestBuilders.delete("/book/{bookCode}", bookCode))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(bookCode);
    }
}

