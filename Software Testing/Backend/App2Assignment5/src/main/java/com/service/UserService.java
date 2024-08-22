package com.service;

import com.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUser(User user);
    User saveOrUpdateUser(User user);
    List<User> getUsers();
    Optional<User> getUser(String username);
    void deleteUser(String username);
}
