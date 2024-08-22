package com.nagarro.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.net.ssl.SSLHandshakeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.nagarro.payloads.ApiResponse;

/**
 * Global exception handler for handling various exceptions across the application.
 * It provides centralized handling for specific exception types and returns appropriate responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    LocalDateTime currentTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss", Locale.ENGLISH);

    /**
     * Handles {@link IllegalArgumentException} and returns a custom response with a 400 status code.
     *
     * @param ex The exception instance.
     * @return ResponseEntity containing a custom {@link ApiResponse} with details about the error.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(IllegalArgumentException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, "400", currentTime.format(formatter));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link SSLHandshakeException} and returns a custom response with a 500 status code.
     *
     * @param ex The exception instance.
     * @return ResponseEntity containing a custom {@link ApiResponse} with details about the error.
     */
    @ExceptionHandler(SSLHandshakeException.class)
    public ResponseEntity<ApiResponse> handleSSLHandshakeException(SSLHandshakeException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, "500", currentTime.format(formatter));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link MethodArgumentTypeMismatchException} and returns a custom response with a 500 status code.
     *
     * @param ex The exception instance.
     * @return ResponseEntity containing a custom {@link ApiResponse} with details about the error.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, "500", currentTime.format(formatter));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
