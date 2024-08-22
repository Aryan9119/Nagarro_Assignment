package com.serviceImpl;

import com.entity.User;
import com.repo.UserRepo;
import com.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo;

    @Override
    public User addUser(User user) {
        return repo.save(user);
    }

    @Override
    public User saveOrUpdateUser(User user) {
        return repo.save(user);
    }

    @Override
    public List<User> getUsers() {
        return repo.findAll();
    }

    @Override
    public Optional<User> getUser(String username) {
        return repo.findById(username);
    }

    @Override
    public void deleteUser(String username) {

    	repo.deleteById(username);


    }
}
