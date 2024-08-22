package com.nagarro.services.Impl;

import com.nagarro.services.Validator;

/**
 * Validator implementation for validating strings containing only English alphabets.
 */
public class EnglishAlphabetsValidator implements Validator {

    // Singleton instance
    private static final EnglishAlphabetsValidator instance = new EnglishAlphabetsValidator();

    /**
     * Private constructor to enforce singleton pattern.
     */
    private EnglishAlphabetsValidator() {
    }

    /**
     * Returns the singleton instance of the validator.
     *
     * @return The singleton instance of the validator.
     */
    public static EnglishAlphabetsValidator getInstance() {
        return instance;
    }

    /**
     * Validates a string to ensure it contains only English alphabets (both uppercase and lowercase).
     *
     * @param input The string to be validated.
     * @return {@code true} if the string contains only English alphabets; {@code false} otherwise.
     */
    public boolean validate(String input) {
        return input.matches("^[a-zA-Z]+$");
    }
}

