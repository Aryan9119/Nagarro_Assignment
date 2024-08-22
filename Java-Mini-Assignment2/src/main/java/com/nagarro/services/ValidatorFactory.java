package com.nagarro.services;

import com.nagarro.services.Impl.EnglishAlphabetsValidator;
import com.nagarro.services.Impl.NumericValidator;

/**
 * Factory class for obtaining instances of validators based on the parameter type.
 */
public class ValidatorFactory {

    /**
     * Returns an instance of a validator based on the provided parameter type.
     *
     * @param parameterType The type of parameter for which a validator is needed.
     * @return A validator instance corresponding to the provided parameter type, or {@code null} if no match is found.
     */
    public static Validator getValidator(String parameterType) {
        if (parameterType.equals("limit") || parameterType.equals("offset") || parameterType.equals("size")) {
            return NumericValidator.getInstance();
        } else if (parameterType.equals("name") || parameterType.equals("age")) {
            return EnglishAlphabetsValidator.getInstance();
        }
        return null;
    }
}

