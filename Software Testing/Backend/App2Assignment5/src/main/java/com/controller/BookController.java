package com.controller;

import com.entity.Book;
import com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(consumes = {"application/json"})
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @PutMapping(consumes = {"application/json"})
    public Book saveOrUpdateBook(@RequestBody Book book) {
        return bookService.saveOrUpdateBook(book);
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{bookCode}")
    public Optional<Book> getBook(@PathVariable("bookCode") int bookCode) {
        return bookService.getBook(bookCode);
    }

    @DeleteMapping("/{bookCode}")
    public void deleteBook(@PathVariable("bookCode") int bookCode) {
        bookService.deleteBook(bookCode);
    }
}
