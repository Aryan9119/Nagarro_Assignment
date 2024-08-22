package com.example.demo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.entity.Author;
import com.repo.AuthorRepo;
import com.serviceImpl.AuthorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
	
	@Mock
    private AuthorRepo authorRepo;

    @InjectMocks
    private AuthorServiceImpl authorService;


    @Test
    public void testSaveOrUpdateAuthor() {
        Author author = new Author();
        author.setName("Author1");

        when(authorRepo.save(author)).thenReturn(author);
        
        Author savedAuthor = authorService.saveOrUpdateAuthor(author);
        
        assertEquals(author, savedAuthor);
        verify(authorRepo, times(1)).save(author);
    }

    @Test
    public void testGetAuthors() {
        Author author1 = new Author();
        author1.setName("Author1");
        Author author2 = new Author();
        author2.setName("Author2");

        List<Author> authors = Arrays.asList(author1, author2);
        when(authorRepo.findAll()).thenReturn(authors);

        List<Author> result = authorService.getAuthors();

        assertEquals(authors, result);

        verify(authorRepo, times(1)).findAll();
    }

    @Test
    public void testGetAuthor() {
        String authorName = "Author1";
        Author author = new Author();
        author.setName(authorName);

        when(authorRepo.findById(authorName)).thenReturn(Optional.of(author));

        Optional<Author> result = authorService.getAuthor(authorName);

        assertThat(result).isPresent();
        assertEquals(result.get().getName(),authorName);

        verify(authorRepo, times(1)).findById(authorName);
    }

    @Test
    public void testDeleteAuthor() {
        String authorName = "Author1";
        Author author = new Author();
        author.setName(authorName);

        when(authorRepo.getById(authorName)).thenReturn(author);
        doNothing().when(authorRepo).delete(author);

        authorService.deleteAuthor(authorName);

        verify(authorRepo, times(1)).getById(authorName);
        verify(authorRepo, times(1)).delete(author);
    }

}
