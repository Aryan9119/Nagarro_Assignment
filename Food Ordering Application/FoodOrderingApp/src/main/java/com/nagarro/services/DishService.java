package com.nagarro.services;

import java.util.List;

import com.nagarro.payloads.request.DishRequestDto;
import com.nagarro.payloads.response.DishResponse;
import com.nagarro.payloads.response.PostResponse;

/**
 * Service interface for managing dishes.
 * Provides methods for creating, updating, deleting, and retrieving dish information.
 */
public interface DishService {

    /**
     * Saves a new dish with the specified cuisine.
     *
     * @param dish the dish request data transfer object containing the details of the dish to be created
     * @param cuisine the cuisine associated with the dish
     */
    void saveDish(DishRequestDto dish, String cuisine);

    /**
     * Updates an existing dish.
     *
     * @param newDish the updated dish request data transfer object
     * @param dishId the unique identifier of the dish to be updated
     */
    void updateDish(DishRequestDto newDish, Long dishId);

    /**
     * Retrieves a specific dish by its unique identifier.
     *
     * @param dishId the unique identifier of the dish to be retrieved
     * @return the dish response data transfer object containing the details of the retrieved dish
     */
    DishResponse getDishById(Long dishId);

    /**
     * Deletes a dish.
     *
     * @param dishId the unique identifier of the dish to be deleted
     */
    void deleteDish(Long dishId);

    /**
     * Retrieves a list of dishes by cuisine.
     *
     * @param cuisine the cuisine title to filter dishes by
     * @return a list of dish response data transfer objects containing the details of the filtered dishes
     */
    List<DishResponse> getDishesByCuisine(String cuisine);

    /**
     * Retrieves a paginated list of all dishes.
     *
     * @param pageNumber the page number for pagination
     * @param pageSize the page size for pagination
     * @param sortBy the attribute to sort the dishes by
     * @return a post response data transfer object containing the details of the paginated dishes
     */
    PostResponse getAllDishes(Integer pageNumber, Integer pageSize, String sortBy);
}
