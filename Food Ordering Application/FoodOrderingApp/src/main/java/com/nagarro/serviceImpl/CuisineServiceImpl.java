package com.nagarro.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.entities.Cuisine;
import com.nagarro.exceptions.ApiException;
import com.nagarro.exceptions.ResourceNotFoundException;
import com.nagarro.payloads.request.CuisineDto;
import com.nagarro.repositories.CuisineRepository;
import com.nagarro.services.CuisineService;

@Service
public class CuisineServiceImpl implements CuisineService {
	
	@Autowired
	private CuisineRepository cuisineRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	/**
     * Creates a new cuisine if it does not already exist.
     * 
     * @param cuisineDto The DTO object containing the details of the cuisine to be created.
     * @throws ApiException If the cuisine name is invalid or if a cuisine with the provided name already exists.
     */
	@Override
	public void createCuisine(CuisineDto cuisineDto) {
		
		if (cuisineDto.getCuisineTitle() == null || !cuisineDto.getCuisineTitle().matches("^[a-zA-Z ]+$")) {
            throw new ApiException("Enter valid cuisine name");
        }
        
        Cuisine cuisine = this.cuisineRepository.findByCuisineTitle(cuisineDto.getCuisineTitle()).get();
        if (cuisine != null) {
            throw new ApiException("Cuisine with provided name already exists, try different one.");
        }

		Cuisine cuisine1 = this.modelMapper.map(cuisineDto, Cuisine.class);
		this.cuisineRepository.save(cuisine1);
	}

	/**
     * Updates an existing cuisine with new details.
     * 
     * @param newCuisine The DTO object containing the new details of the cuisine.
     * @param cuisineToUpdate The title of the cuisine to be updated.
     * @throws ApiException If the new cuisine name is invalid.
     * @throws ResourceNotFoundException If the cuisine with the provided name is not found.
     */
	@Override
	public void updateCuisine(CuisineDto newCuisine, String cuisineToUpdate) {
		
		if (newCuisine.getCuisineTitle() == null || !newCuisine.getCuisineTitle().matches("^[a-zA-Z ]+$")) {
            throw new ApiException("Enter valid cuisine name");
        }
		Cuisine cuisine = this.cuisineRepository.findByCuisineTitle(cuisineToUpdate).orElseThrow(() ->
        new ResourceNotFoundException("Cuisine with given name is not found. !! : " + cuisineToUpdate));

		cuisine.setCuisineTitle(newCuisine.getCuisineTitle());
		this.cuisineRepository.save(cuisine);

	}

	/**
     * Deletes an existing cuisine.
     * 
     * @param cuisineToDelete The title of the cuisine to be deleted.
     * @throws ResourceNotFoundException If the cuisine with the provided name is not found.
     */
	@Override
	public void deleteCuisine(String cuisineToDelete) {
		
		
		Cuisine cuisine = this.cuisineRepository.findByCuisineTitle(cuisineToDelete).orElseThrow(() ->
        new ResourceNotFoundException("Cuisine with given name is not found on server !! : " + cuisineToDelete));
		
		this.cuisineRepository.delete(cuisine);

	}

	/**
     * Retrieves a cuisine by its title.
     * 
     * @param cuisineToGet The title of the cuisine to be retrieved.
     * @return The DTO object representing the retrieved cuisine.
     * @throws ResourceNotFoundException If the cuisine with the provided name is not found.
     */
	@Override
	public CuisineDto getCuisine(String cuisineToGet) {

		Cuisine cuisine = this.cuisineRepository.findByCuisineTitle(cuisineToGet).orElseThrow(() ->
        new ResourceNotFoundException("Cuisine with given name is not found on server !! : " + cuisineToGet));
		
		return this.modelMapper.map(cuisine, CuisineDto.class);
	}

	/**
     * Retrieves all cuisines.
     * 
     * @return A list of DTO objects representing all cuisines.
     */
	@Override
	public List<CuisineDto> getAllCuisine() {
		List<Cuisine> cuisines = this.cuisineRepository.findAll();
		List<CuisineDto> cuisineDtos = cuisines.stream().map((cuisin) -> this.modelMapper.map(cuisin, CuisineDto.class))
				.collect(Collectors.toList());
		return cuisineDtos;
	}


}
