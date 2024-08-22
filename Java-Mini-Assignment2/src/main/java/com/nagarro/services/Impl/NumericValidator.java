package com.nagarro.services.Impl;

import com.nagarro.services.Validator;

/**
 * Validator implementation for validating strings containing numeric values.
 */
public class NumericValidator implements Validator {

    // Singleton instance
    private static final NumericValidator instance = new NumericValidator();

    /**
     * Private constructor to enforce singleton pattern.
     */
    private NumericValidator() {
    }

    /**
     * Returns the singleton instance of the validator.
     *
     * @return The singleton instance of the validator.
     */
    public static NumericValidator getInstance() {
        return instance;
    }

    /**
     * Validates a string to ensure it represents a valid numeric value.
     *
     * @param input The string to be validated.
     * @return {@code true} if the string represents a valid numeric value; {@code false} otherwise.
     */
    public boolean validate(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

