package com.example.demo.repository;

import com.entity.User;
import com.repo.UserRepo;
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
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @BeforeEach
    public void setUp() {
        userRepo.deleteAll();

        User user1 = new User();
        user1.setUname("user1");
        user1.setPass("pass1");
        userRepo.save(user1);

        User user2 = new User();
        user2.setUname("user2");
        user2.setPass("pass2");
        userRepo.save(user2);
    }

    @Test
    public void testFindById() {
        String username = "user1";
        Optional<User> foundUser = userRepo.findById(username);

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUname()).isEqualTo(username);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUname("user3");
        user.setPass("pass3");

        User savedUser = userRepo.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUname()).isEqualTo("user3");
    }

    @Test
    public void testDelete() {
        String username = "user1";
        User user = userRepo.findById(username).orElse(null);

        assertThat(user).isNotNull();

        userRepo.delete(user);

        Optional<User> deletedUser = userRepo.findById(username);

        assertThat(deletedUser).isNotPresent();
    }

    @Test
    public void testFindAll() {
        List<User> users = userRepo.findAll();

        assertThat(users).hasSize(2);
    }
}
