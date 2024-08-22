package com.nagarro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.payloads.request.DishRequestDto;
import com.nagarro.payloads.response.ApiResponse;
import com.nagarro.payloads.response.DishResponse;
import com.nagarro.payloads.response.PostResponse;
import com.nagarro.services.DishService;

/**
 * Controller class for handling HTTP requests related to dishes.
 * 
 * Provides endpoints to create, update, delete, and retrieve dish information,
 * including filtering dishes by cuisine and paginating the list of dishes.
 * 
 * @author Aryan Verma
 */
@RestController
@RequestMapping("/api/dishes")
public class DishController {
    @Autowired
    private DishService dishService;
    
    /**
     * Updates an existing dish.
     * 
     * @param dishRequestDto the details of the dish to be updated
     * @param dishId the unique ID of the dish to be updated
     * @return a response entity with a success message
     */
    @PutMapping("/{dishId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> updateDish(@RequestBody DishRequestDto dishRequestDto, @PathVariable("dishId") Long uid) {
        this.dishService.updateDish(dishRequestDto, uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Dish updated Successfully", true), HttpStatus.OK);
    }

    /**
     * Retrieves all dishes with optional pagination and sorting.
     * 
     * @param pageNumber the page number for pagination (default is 0)
     * @param pageSize the number of dishes per page (default is 3)
     * @param sortBy the field to sort dishes by (default is "averageRating")
     * @return a response entity containing the paginated and sorted list of dishes
     */
    @GetMapping
    public ResponseEntity<PostResponse> getAllDishes(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "averageRating", required = false) String sortBy
    ) {
        PostResponse postResponse = this.dishService.getAllDishes(pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(postResponse);
    }

    /**
     * Retrieves dishes by cuisine.
     * 
     * @param cuisineTitle the title of the cuisine to filter dishes by
     * @return a response entity containing the list of dishes for the specified cuisine
     */
    @GetMapping("/cuisine/{cuisineTitle}")
    public ResponseEntity<List<DishResponse>> getDishesByCuisine(@PathVariable("cuisineTitle") String cuisine) {
        List<DishResponse> dishes = this.dishService.getDishesByCuisine(cuisine);
        return new ResponseEntity<List<DishResponse>>(dishes, HttpStatus.OK);
    }
    
    /**
     * Retrieves a single dish by its unique ID.
     * 
     * @param dishId the unique ID of the dish to be retrieved
     * @return a response entity containing the details of the requested dish
     */
    @GetMapping("/{dishId}")
    public ResponseEntity<DishResponse> getDishById(@PathVariable("dishId") Long id) {
        return ResponseEntity.ok(this.dishService.getDishById(id));
    }
    
    /**
     * Creates a new dish.
     * 
     * @param dishRequestDto the details of the dish to be created
     * @param cuisineTitle the title of the cuisine the dish belongs to
     * @return a response entity with a success message
     */
    @PostMapping("/cuisine/{cuisineTitle}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> createDish(@RequestBody DishRequestDto dishRequestDto, @PathVariable("cuisineTitle") String cuisine) {
        this.dishService.saveDish(dishRequestDto, cuisine);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Dish Added Successfully", true), HttpStatus.OK);
    }
    
    /**
     * Deletes a dish by its unique ID.
     * 
     * @param dishId the unique ID of the dish to be deleted
     * @return a response entity with a success message
     */
    @DeleteMapping("/{dishId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteDish(@PathVariable("dishId") Long id) {
        this.dishService.deleteDish(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Dish deleted Successfully", true), HttpStatus.OK);
    }
}
