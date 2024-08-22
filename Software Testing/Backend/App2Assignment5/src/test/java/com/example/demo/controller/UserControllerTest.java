package com.example.demo.controller;

import com.controller.UserController;
import com.entity.User;
import com.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    
    @Test
    public void testSaveOrUpdateUser() throws Exception {
        User user = new User("user1", "pass1");

        when(userService.saveOrUpdateUser(any(User.class))).thenAnswer(invocation -> {
            User argument = invocation.getArgument(0);
            assertThat(argument.getUname()).isEqualTo("user1");
            assertThat(argument.getPass()).isEqualTo("pass1");
            return argument;
        });

        mockMvc.perform(MockMvcRequestBuilders.put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"uname\":\"user1\",\"pass\":\"pass1\"}"))
                .andExpect(status().isOk())
        		.andExpect(jsonPath("$.uname").value("user1"))
        		.andExpect(jsonPath("$.pass").value("pass1"));

        verify(userService, times(1)).saveOrUpdateUser(any(User.class));
    }

    @Test
    public void testGetUsers() throws Exception {
        User user1 = new User("user1", "pass1");
        User user2 = new User("user2", "pass2");

        List<User> users = Arrays.asList(user1, user2);
        when(userService.getUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].uname").value("user1"))
                .andExpect(jsonPath("$[0].pass").value("pass1"))
                .andExpect(jsonPath("$[1].uname").value("user2"))
                .andExpect(jsonPath("$[1].pass").value("pass2"));

        verify(userService, times(1)).getUsers();
    }

    @Test
    public void testGetUser() throws Exception {
        String username = "user1";
        User user = new User(username, "pass1");

        when(userService.getUser(username)).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{uname}", username)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uname").value(username))
                .andExpect(jsonPath("$.pass").value("pass1"));

        verify(userService, times(1)).getUser(username);
    }

    @Test
    public void testDeleteUser() throws Exception {
        String username = "user1";

        doNothing().when(userService).deleteUser(username);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{uname}", username))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(username);
    }
}
