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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.payloads.request.CuisineDto;
import com.nagarro.payloads.response.ApiResponse;
import com.nagarro.services.CuisineService;
/**
 * Controller class for handling HTTP requests related to cuisines.
 * 
 * Provides endpoints to create, delete, and retrieve cuisines.
 * All endpoints are secured and require ADMIN role for creation and deletion operations.
 * 
 * @author Aryan Verma
 */
@RestController
@RequestMapping("/cuisines")
public class CuisineController {
	
    @Autowired
    private CuisineService cuisineService;

    /**
     * Creates a new cuisine.
     * 
     * @param cuisine the details of the cuisine to be created
     * @return a response entity with a success message
     */
 	@PostMapping("/add")
 	@PreAuthorize("hasRole('ADMIN')")
 	public ResponseEntity<ApiResponse> createCuisine(@RequestBody CuisineDto cuisine) {
 		this.cuisineService.createCuisine(cuisine);
 		return new ResponseEntity<ApiResponse>(new ApiResponse("Cuisine added Successfully", true), HttpStatus.OK);
 	}

 	/**
     * Deletes an existing cuisine by title.
     * 
     * @param cuisineToDelete the title of the cuisine to be deleted
     * @return a response entity with a success message
     */
 	@DeleteMapping("/{cuisineTitle}")
 	@PreAuthorize("hasRole('ADMIN')")
 	public ResponseEntity<ApiResponse> deleteCuisine(@PathVariable("cuisineTitle") String cuisineToDelete) {
 		this.cuisineService.deleteCuisine(cuisineToDelete);
 		return new ResponseEntity<ApiResponse>(new ApiResponse("Cuisine deleted Successfully", true), HttpStatus.OK);
 	}

 	/**
     * Retrieves all available cuisines.
     * 
     * @return a response entity containing a list of all cuisines
     */
 	@GetMapping
 	public ResponseEntity<List<CuisineDto>> getAllCuisines() {
 		return ResponseEntity.ok(this.cuisineService.getAllCuisine());
 	}
 	
 	/**
     * Retrieves a single cuisine by title.
     * 
     * @param cuisine the title of the cuisine to be retrieved
     * @return a response entity containing the details of the requested cuisine
     */
 	@GetMapping("/{cuisineTitle}")
 	public ResponseEntity<CuisineDto> getSingleCuisine(@PathVariable("cuisineTitle") String cuisine) {
 		return ResponseEntity.ok(this.cuisineService.getCuisine(cuisine));
 	}
}


