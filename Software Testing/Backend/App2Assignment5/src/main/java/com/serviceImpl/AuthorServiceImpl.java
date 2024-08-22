package com.serviceImpl;

import com.entity.Author;
import com.repo.AuthorRepo;
import com.service.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepo repo;

    @Override
    public void addAuthor(Author author) {
        repo.save(author);
    }

    @Override
    public Author saveOrUpdateAuthor(Author author) {
        return repo.save(author);
    }

    @Override
    public List<Author> getAuthors() {
        return repo.findAll();
    }

    @Override
    public Optional<Author> getAuthor(String name) {
        return repo.findById(name);
    }

    @Override
    public void deleteAuthor(String name) {
        Author a = repo.getById(name);
        repo.delete(a);
    }
}
