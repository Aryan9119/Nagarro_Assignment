package com.nagarro.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.controller.LoginController;
import com.service.BookService;
import com.service.LoginService;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService mockBookService;

    @Mock
    private LoginService mockLoginService;
    

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void testCheckUserSuccess() throws Exception {
        String username = "testuser";
        String password = "testpass";

        when(mockLoginService.checkLogin(username, password)).thenReturn(true);

        mockMvc.perform(post("/Login")
                .param("uname", username)
                .param("pass", password))
                .andExpect(status().isOk())
                .andExpect(view().name("bookListing"))
                .andExpect(model().attribute("username", username));
        
        HttpSession session = mockMvc.perform(post("/Login")
                .param("uname", username)
                .param("pass", password))
                .andReturn().getRequest().getSession();
        
        String actualUsername = (String) session.getAttribute("username");
        
        assertEquals(username, actualUsername);
    }
}