package com.example.demo.repository;

import com.entity.Author;
import com.repo.AuthorRepo;

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
public class AuthorRepoTest {

    @Autowired
    private AuthorRepo authorRepo;

    @BeforeEach
    public void setUp() {
        authorRepo.deleteAll();

        Author author1 = new Author();
        author1.setName("Test Author 1");
        authorRepo.save(author1);

        Author author2 = new Author();
        author2.setName("Test Author 2");
        authorRepo.save(author2);
    }

    @Test
    public void testFindById() {
        String authorName = "Test Author 1";
        Optional<Author> foundAuthor = authorRepo.findById(authorName);

        assertThat(foundAuthor).isPresent();
        assertThat(foundAuthor.get().getName()).isEqualTo(authorName);
    }

    @Test
    public void testSave() {
        Author author = new Author();
        author.setName("New Author");

        Author savedAuthor = authorRepo.save(author);

        assertThat(savedAuthor).isNotNull();
        assertThat(savedAuthor.getName()).isEqualTo("New Author");
    }

    @Test
    public void testDelete() {
        String authorName = "Test Author 1";
        Author author = authorRepo.findById(authorName).orElse(null);

        assertThat(author).isNotNull();

        authorRepo.delete(author);

        Optional<Author> deletedAuthor = authorRepo.findById(authorName);

        assertThat(deletedAuthor).isNotPresent();
    }

    @Test
    public void testFindAll() {
        List<Author> authors = authorRepo.findAll();

        assertThat(authors).hasSize(2);
    }
}
