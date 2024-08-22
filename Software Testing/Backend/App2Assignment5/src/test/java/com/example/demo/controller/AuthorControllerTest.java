package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.controller.AuthorController;
import com.entity.Author;
import com.service.AuthorService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Test
    public void testGetAuthors() throws Exception {
        Author author1 = new Author();
        author1.setName("John Doe");
        Author author2 = new Author();
        author2.setName("Jane Smith");

        List<Author> authors = Arrays.asList(author1, author2);
        when(authorService.getAuthors()).thenReturn(authors);

        mockMvc.perform(MockMvcRequestBuilders.get("/author")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));

        verify(authorService, times(1)).getAuthors();
    }

    @Test
    public void testGetAuthor() throws Exception {
        String authorName = "John Doe";
        Author author = new Author();
        author.setName(authorName);

        when(authorService.getAuthor(authorName)).thenReturn(Optional.of(author));

        mockMvc.perform(MockMvcRequestBuilders.get("/author/{name}", authorName)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(authorName));

        verify(authorService, times(1)).getAuthor(authorName);
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        String authorName = "John Doe";

        doNothing().when(authorService).deleteAuthor(authorName);

        mockMvc.perform(MockMvcRequestBuilders.delete("/author/{name}", authorName))
                .andExpect(status().isOk());

        verify(authorService, times(1)).deleteAuthor(authorName);
    }

    @Test
    public void testSaveOrUpdateAuthor() throws Exception {
        Author author = new Author();
        author.setName("John Doe");
        
        when(authorService.saveOrUpdateAuthor(any(Author.class))).thenAnswer(invocation -> {
            Author argument = invocation.getArgument(0);
            assertThat(argument.getName()).isEqualTo("John Doe");
            return argument;
        });

        mockMvc.perform(MockMvcRequestBuilders.put("/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\"}"))
                .andExpect(status().isOk());
    }
}

