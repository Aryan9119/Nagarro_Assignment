package com.nagarro.services;

import org.springframework.stereotype.Service;

/**
 * Service class responsible for performing validations on input parameters.
 */
@Service
public class ValidatorService {

    /**
     * Validates the provided sorting type, sorting order, limit, and offset parameters.
     *
     * @param sortType  The sorting type parameter to be validated.
     * @param sortOrder The sorting order parameter to be validated.
     * @param limit     The limit parameter to be validated.
     * @param offset    The offset parameter to be validated.
     */
    public void validations(String sortType, String sortOrder, String limit, String offset) {
        validateParameter("name", sortType);
        validateParameter("age", sortOrder);
        validateParameter("limit", limit);
        validateParameter("offset", offset);
    }

    /**
     * Validates a specific input parameter based on its type.
     *
     * @param parameterType The type of the parameter to be validated.
     * @param inputData     The value of the parameter to be validated.
     * @throws IllegalArgumentException If the validation fails, an exception is thrown with an appropriate message.
     */
    public void validateParameter(String parameterType, String inputData) {
        Validator validator = ValidatorFactory.getValidator(parameterType);
        if (!validator.validate(inputData)) {
            throw new IllegalArgumentException("Pass valid " + parameterType + " parameter to process the data.");
        }
        if (parameterType.equals("size") || parameterType.equals("limit")) {
            int inputValue = Integer.parseInt(inputData);
            if (inputValue < 1 || inputValue > 5) {
                throw new IllegalArgumentException(parameterType + " should be between 1 and 5");
            }
        }
    }
} 
