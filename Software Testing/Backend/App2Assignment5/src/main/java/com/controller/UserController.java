package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.repo.UserRepo;
import com.service.UserService;

@RestController
public class UserController {

//	@Autowired 
//	UserRepo repo;
	
	@Autowired
	UserService userService;
	
	@PostMapping(path="/users",consumes= {"application/json"})
	public User addUsers(@RequestBody User user) 
	{
		return userService.addUser(user);
		
	}
	
	@PutMapping(path="/users",consumes= {"application/json"})
	public User saveOrUpdateUsers(@RequestBody User user) 
	{
		return userService.saveOrUpdateUser(user);
		
	}
	
	@GetMapping(path="/users")
	public List<User> getUsers()
	{
		return userService.getUsers();
	}
	
	@RequestMapping("/users/{uname}")
	public Optional<User> getUser(@PathVariable("uname") String uname)
	{
		return userService.getUser(uname);

	}
	
	@DeleteMapping("/users/{uname}")
	public void deleteUser(@PathVariable("uname") String uname)
	{

		userService.deleteUser(uname);
		
	}
}
