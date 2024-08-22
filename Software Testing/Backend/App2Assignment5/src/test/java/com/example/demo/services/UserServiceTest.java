package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.entity.User;
import com.repo.UserRepo;
import com.serviceImpl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	  @Mock
	  private UserRepo userRepo;
	  
	  @InjectMocks
	  private UserServiceImpl userService;
	  
	  @Test
	  public void testGetUsers() {
			  
	      List<User> users = Arrays.asList(new User("user1", "pass1"), new User("user2", "pass2"));
	      when(userRepo.findAll()).thenReturn(users);

	      List<User> response = userService.getUsers();
	      assertEquals(users, response);
	      verify(userRepo, times(1)).findAll();
	  }
	  
	    @Test
	    public void testGetUser() {
	        User user = new User("user1", "pass1");
	        when(userRepo.findById("user1")).thenReturn(Optional.of(user));

	        Optional<User> result = userService.getUser("user1");

	        assertEquals(Optional.of(user), result);
	        verify(userRepo, times(1)).findById("user1");
	    }
	    
	    @Test
	    public void testSaveOrUpdateUser() {

	        User user = new User("user1", "pass1");
	        when(userRepo.save(user)).thenReturn(user);

	        User updatedUser = userService.saveOrUpdateUser(user);

	        assertEquals(updatedUser, user);
	        verify(userRepo, times(1)).save(user);
	    }
	    
	    @Test
	    public void testDeleteUser() {
	        String username = "user1";

	        userService.deleteUser(username);

	        verify(userRepo, times(1)).deleteById(username);
	    }

}
