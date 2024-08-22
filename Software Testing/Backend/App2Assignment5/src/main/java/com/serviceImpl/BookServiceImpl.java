package com.serviceImpl;

import com.entity.Book;
import com.repo.BookRepo;
import com.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo repo;

    @Override
    public void addBook(Book book) {
        Optional<Book> check = repo.findById(book.getBookCode());
        if (check.isEmpty()) {
            repo.save(book);
        }
    }

    @Override
    public Book saveOrUpdateBook(Book book) {
        return repo.save(book);
    }

    @Override
    public List<Book> getBooks() {
        return repo.findAll();
    }

    @Override
    public Optional<Book> getBook(int bookCode) {
        return repo.findById(bookCode);
    }

    @Override
    public void deleteBook(int bookCode) {
        Book a = repo.getById(bookCode);
        repo.delete(a);
    }
}
