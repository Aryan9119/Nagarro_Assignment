package com.nagarro.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.controller.LogoutController;

@ExtendWith(MockitoExtension.class)
public class LogoutControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private LogoutController logoutController;

    @Mock
    private HttpSession session;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(logoutController).build();
    }

    @Test
    public void testLogout() throws Exception {
        when(session.getAttribute("username")).thenReturn("testuser");

        mockMvc.perform(post("/Logout"))
               .andExpect(status().isOk());

        verify(session).removeAttribute("username");
        verify(session).invalidate();
    }
}
