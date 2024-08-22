package com.nagarro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import com.entity.User;
import com.service.RegisterService;

public class RegisterServiceTest {

    private RegisterService registerService;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        restTemplate = mock(RestTemplate.class);
        registerService = new RegisterService();
        registerService.setRestTemplate(restTemplate);
    }

    @Test
    public void testSaveUser() {

        User userToSave = new User("John", "password123");

        when(restTemplate.postForObject(anyString(), any(User.class), eq(User.class)))
                .thenReturn(userToSave);

        User savedUser = registerService.saveUser(userToSave);

        verify(restTemplate).postForObject(anyString(), any(User.class), eq(User.class));

        // Additional assertions
        assertEquals(userToSave.getUname(), savedUser.getUname());
        assertEquals(userToSave.getPass(), savedUser.getPass());
    }
}
