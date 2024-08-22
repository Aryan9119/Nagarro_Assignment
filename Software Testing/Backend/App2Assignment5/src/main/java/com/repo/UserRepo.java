package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.User;

public interface UserRepo extends JpaRepository<User,String>{

}
