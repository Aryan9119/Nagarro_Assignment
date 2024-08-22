package com.service;

import com.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    void addBook(Book book);
    Book saveOrUpdateBook(Book book);
    List<Book> getBooks();
    Optional<Book> getBook(int bookCode);
    void deleteBook(int bookCode);
}

