package com.controller;
//
//import java.util.List;
//import java.util.Optional;
//
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.entity.Author;
//import com.repo.AuthorRepo;

//package com.controller;

import com.entity.Author;
import com.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping(consumes = {"application/json"})
    public void addAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
    }

    @PutMapping(consumes = {"application/json"})
    public void saveOrUpdateAuthor(@RequestBody Author author) {
        Author savedAuthor =authorService.saveOrUpdateAuthor(author);
    }

    @GetMapping
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping("/{name}")
    public Optional<Author> getAuthor(@PathVariable("name") String name) {
        return authorService.getAuthor(name);
    }

    @DeleteMapping("/{name}")
    public void deleteAuthor(@PathVariable("name") String name) {
        authorService.deleteAuthor(name);
    }
}

