package com.example.demo.repository;

import com.entity.Book;
import com.repo.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class BookRepoTest {

    @Autowired
    private BookRepo bookRepo;

    @BeforeEach
    public void setUp() {
        bookRepo.deleteAll();

        Book book1 = new Book();
        book1.setBookCode(1);
        book1.setBookName("Test Book 1");
        bookRepo.save(book1);

        Book book2 = new Book();
        book2.setBookCode(2);
        book2.setBookName("Test Book 2");
        bookRepo.save(book2);
    }

    @Test
    public void testFindById() {
        int bookCode = 1;
        Optional<Book> foundBook = bookRepo.findById(bookCode);

        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getBookCode()).isEqualTo(bookCode);
    }

    @Test
    public void testSave() {
        Book book = new Book();
        book.setBookCode(3);
        book.setBookName("New Book");

        Book savedBook = bookRepo.save(book);

        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getBookName()).isEqualTo("New Book");
    }

    @Test
    public void testDelete() {
        int bookCode = 1;
        Book book = bookRepo.findById(bookCode).orElse(null);

        assertThat(book).isNotNull();

        bookRepo.delete(book);

        Optional<Book> deletedBook = bookRepo.findById(bookCode);

        assertThat(deletedBook).isNotPresent();
    }

    @Test
    public void testFindAll() {
        List<Book> books = bookRepo.findAll();

        assertThat(books).hasSize(2);
    }
}
