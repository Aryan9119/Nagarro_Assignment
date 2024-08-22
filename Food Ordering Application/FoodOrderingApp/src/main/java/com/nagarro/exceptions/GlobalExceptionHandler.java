package com.nagarro.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.nagarro.payloads.response.ApiResponse;

/**
 * Global exception handler for managing exceptions across the application.
 * 
 * This class handles various exceptions and provides appropriate responses
 * with suitable HTTP status codes.
 * 
 * @author Aryan Verma
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException.
     * 
     * @param ex the exception
     * @return a response entity containing an error message and HTTP status 404 (NOT_FOUND)
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles MethodArgumentNotValidException.
     * 
     * @param ex the exception
     * @return a response entity containing a map of field errors and HTTP status 400 (BAD_REQUEST)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
    	String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles ApiException.
     * 
     * @param ex the exception
     * @return a response entity containing an error message and HTTP status 400 (BAD_REQUEST)
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> apiExceptionHandler(ApiException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles AuthorizationDeniedException.
     * 
     * @param ex the exception
     * @return a response entity containing an error message and HTTP status 404 (NOT_FOUND)
     */
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiResponse> authorizationDeniedExceptionHandler(AuthorizationDeniedException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles RuntimeException.
     * 
     * @param ex the exception
     * @return a response entity containing an error message and HTTP status 404 (NOT_FOUND)
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> runTimeExceptionHandler(RuntimeException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles DataIntegrityViolationException.
     * 
     * @param ex the exception
     * @return a response entity containing an error message and HTTP status 409 (CONFLICT)
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = "Sorry, you have already provided feedback for this dish.";
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handles NoResourceFoundException.
     * 
     * @param ex the exception
     * @return a response entity containing an error message and HTTP status 404 (NOT_FOUND)
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse> handleNoHandlerFoundException(NoResourceFoundException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Handles Unauthorized exceptions.
     * 
     * @param ex the exception
     * @return a response entity containing an error message and HTTP status 401 (UNAUTHORIZED)
     */
    @ExceptionHandler({AccessDeniedException.class, BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<ApiResponse> handleUnauthorizedException(Exception ex) {
        String message = "Unauthorized: " + ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.UNAUTHORIZED);
    }
}
