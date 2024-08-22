package com.nagarro.services;

import java.util.List;
import com.nagarro.payloads.request.CuisineDto;

/**
 * Service interface for managing cuisines.
 * Provides methods for creating, updating, deleting, and retrieving cuisines.
 */
public interface CuisineService {

    /**
     * Creates a new cuisine.
     *
     * @param cuisine the cuisine data transfer object containing the details of the cuisine to be created
     */
    void createCuisine(CuisineDto cuisine);

    /**
     * Updates an existing cuisine.
     *
     * @param savedCuisine the updated cuisine data transfer object
     * @param cuisineToUpdate the name or identifier of the cuisine to be updated
     */
    void updateCuisine(CuisineDto savedCuisine, String cuisineToUpdate);

    /**
     * Deletes a cuisine.
     *
     * @param cuisineToDelete the name or identifier of the cuisine to be deleted
     */
    void deleteCuisine(String cuisineToDelete);

    /**
     * Retrieves a specific cuisine by its name or identifier.
     *
     * @param cuisineToGet the name or identifier of the cuisine to be retrieved
     * @return the cuisine data transfer object containing the details of the retrieved cuisine
     */
    CuisineDto getCuisine(String cuisineToGet);

    /**
     * Retrieves a list of all cuisines.
     *
     * @return a list of cuisine data transfer objects containing the details of all cuisines
     */
    List<CuisineDto> getAllCuisine();
}
