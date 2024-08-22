package com.nagarro.services;

/**
 * Interface defining a generic validator for validating a string.
 */
public interface Validator {

    /**
     * Validates the given string according to the specific implementation.
     *
     * @param s The string to be validated.
     * @return {@code true} if the string is considered valid; {@code false} otherwise.
     */
    boolean validate(String s);
}

