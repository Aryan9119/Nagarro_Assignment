package com.nagarro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exceptions.ApiException;
import com.nagarro.payloads.request.JwtRequest;
import com.nagarro.payloads.response.JwtResponse;
import com.nagarro.security.CustomUserDetailsService;
import com.nagarro.security.JwtUtils;
/**
 * Controller class for handling authentication-related requests.
 * 
 * Provides endpoints for user authentication and token generation.
 * 
 * @author Aryan Verma
 */
@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtTokenUtil;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    /**
     * Authenticates the user and generates a JWT token.
     * 
     * @param authenticationRequest the request containing the username and password
     * @return a response entity containing the JWT token
     * @throws Exception if authentication fails
     */
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        String token = this.jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
    /**
     * Authenticates the user credentials.
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @throws Exception if the user is disabled or credentials are invalid
     */
    private void authenticate(String username, String password) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new ApiException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new ApiException("INVALID_CREDENTIALS");
        }
    }
}

