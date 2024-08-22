package com.example.demo.services;

import com.entity.Author;
import com.entity.Book;
import com.repo.BookRepo;
import com.serviceImpl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void testSaveOrUpdateBook() {
        Book book = new Book(1, "Book1", "Author1", "2023-01-01");

        when(bookRepo.save(book)).thenReturn(book);
        
        Book savedBook = bookService.saveOrUpdateBook(book);;
        
        assertEquals(book, savedBook);

        verify(bookRepo, times(1)).save(book);
    }

    @Test
    public void testGetBooks() {
        Book book1 = new Book(1, "Book1", "Author1", "2023-01-01");
        Book book2 = new Book(2, "Book2", "Author2", "2023-01-02");

        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepo.findAll()).thenReturn(books);

        List<Book> result = bookService.getBooks();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(book1, book2);

        verify(bookRepo, times(1)).findAll();
    }

    @Test
    public void testGetBook() {
        int bookCode = 1;
        Book book = new Book(bookCode, "Book1", "Author1", "2023-01-01");

        when(bookRepo.findById(bookCode)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.getBook(bookCode);

        assertThat(result).isPresent();
        assertThat(result.get().getBookCode()).isEqualTo(bookCode);

        verify(bookRepo, times(1)).findById(bookCode);
    }

    @Test
    public void testDeleteBook() {
        int bookCode = 1;
        Book book = new Book(bookCode, "Book1", "Author1", "2023-01-01");

        when(bookRepo.getById(bookCode)).thenReturn(book);
        doNothing().when(bookRepo).delete(book);

        bookService.deleteBook(bookCode);

        verify(bookRepo, times(1)).getById(bookCode);
        verify(bookRepo, times(1)).delete(book);
    }
}

