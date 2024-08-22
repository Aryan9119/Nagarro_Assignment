package com.nagarro.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.controller.RegisterController;
import com.entity.User;
import com.service.RegisterService;

@ExtendWith(MockitoExtension.class)
public class RegisterControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RegisterController registerController;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RegisterService registerService;
    
    @Mock
    private User user;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
    }

    @Test
    public void testAddUsers() throws Exception {
    	
    	String uname = "testuser";
        String pass = "testpass";

        User user1 = new User();
        user1.setUname(uname);
        user1.setPass(pass);

        when(registerService.saveUser(any(User.class))).thenReturn(user1);

        mockMvc.perform(post("/Register")
                        .param("uname", uname)
                        .param("pass", pass))
               .andExpect(status().isOk())
               .andExpect(view().name("login"));
    }
}
