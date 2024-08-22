package com.service;

import com.entity.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    void addAuthor(Author author);
    Author saveOrUpdateAuthor(Author author);
    List<Author> getAuthors();
    Optional<Author> getAuthor(String name);
    void deleteAuthor(String name);
}
