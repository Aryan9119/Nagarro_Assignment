package com.nagarro.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import com.service.LoginService;

public class LoginServiceTest {

    private LoginService loginService;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        restTemplate = mock(RestTemplate.class);
        loginService = new LoginService();
        loginService.setRestTemplate(restTemplate);
    }

    @Test
    public void testCheckLoginSuccess() throws Exception {
        String uname = "testUser";
        String pass = "testPass";

        // Mock the response from the REST service
        JSONObject mockResponse = new JSONObject();
        mockResponse.put("uname", uname);
        mockResponse.put("pass", pass);

        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(mockResponse.toJSONString());

        boolean result = loginService.checkLogin(uname, pass);

        assertTrue(result);
    }

    @Test
    public void testCheckLoginFailure() throws Exception {
        String uname = "testUser";
        String pass = "testPass";
        String wrongPass = "wrongPass";

        // Mock the response from the REST service
        JSONObject mockResponse = new JSONObject();
        mockResponse.put("uname", uname);
        mockResponse.put("pass", pass);

        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(mockResponse.toJSONString());

        boolean result = loginService.checkLogin(uname, wrongPass);

        assertFalse(result);
    }

    @Test
    public void testCheckLoginException() throws Exception {
        String uname = "testUser";
        String pass = "testPass";

        when(restTemplate.getForObject(anyString(), eq(String.class))).thenThrow(new RuntimeException("Service not available"));

        boolean result = loginService.checkLogin(uname, pass);

        assertFalse(result);
    }
}
