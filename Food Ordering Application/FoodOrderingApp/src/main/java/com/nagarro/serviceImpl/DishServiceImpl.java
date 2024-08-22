package com.nagarro.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nagarro.entities.Cuisine;
import com.nagarro.entities.Dish;
import com.nagarro.exceptions.ApiException;
import com.nagarro.exceptions.ResourceNotFoundException;
import com.nagarro.payloads.request.DishRequestDto;
import com.nagarro.payloads.request.FeedbackDto;
import com.nagarro.payloads.response.DishResponse;
import com.nagarro.payloads.response.PostResponse;
import com.nagarro.repositories.CuisineRepository;
import com.nagarro.repositories.DishRepository;
import com.nagarro.services.DishService;

@Service
public class DishServiceImpl implements DishService {
	
	@Autowired
	private DishRepository dishRepository;
	
	@Autowired
	private CuisineRepository cuisineRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Saves a new dish with the given details and associated cuisine.
	 *
	 * @param dishDto the details of the dish to save
	 * @param cuisine the cuisine to associate with the dish
	 * @throws ApiException if the dish already exists
	 */
	@Override
	public void saveDish(DishRequestDto dishDto, String cuisine) {
		
		Dish dish1 = this.dishRepository.findByName(dishDto.getName());
		if(dish1==null) {
		
		Cuisine cuisine1 = this.cuisineRepository.findByCuisineTitle(cuisine).get();		
		Dish dish = this.dtoToDish(dishDto);
		dish.setCuisine(cuisine1);
	    dishRepository.save(dish);
		}else {
			throw new ApiException("This dish has already been added");
		}
	}

	/**
	 * Updates the details of an existing dish.
	 *
	 * @param newDish the new details of the dish
	 * @param dishId the ID of the dish to update
	 * @throws ResourceNotFoundException if the dish with the given ID is not found
	 */
	@Override
	public void updateDish(DishRequestDto newDish, Long dishId) {
		
		Dish dish = this.dishRepository.findById(dishId).orElseThrow(() ->
	    new ResourceNotFoundException("Dish with given id is not found on server !! : " + dishId));

		dish.setName(newDish.getName());
		dish.setDescription(newDish.getDescription());
		dish.setPrice(newDish.getPrice());
		dish.setAvailability(newDish.isAvailability());
		dishRepository.save(dish);
	}

	/**
	 * Retrieves the dish with the specified ID.
	 *
	 * @param dishId the ID of the dish to retrieve
	 * @return the details of the dish
	 * @throws ResourceNotFoundException if the dish with the given ID is not found
	 */
	@Override
	public DishResponse getDishById(Long dishId) {
		
		Dish dish = this.dishRepository.findById(dishId).orElseThrow(() ->
	    new ResourceNotFoundException("Dish with given id is not found on server !! : " + dishId));
		
		return this.dishToDto(dish);
	}

	/**
	 * Retrieves a paginated list of all dishes, optionally sorted by the specified field.
	 *
	 * @param pageNumber the page number to retrieve (0-based)
	 * @param pageSize the number of dishes per page
	 * @param sortBy the field to sort by (e.g., "orderCount", "averageRating")
	 * @return a {@link PostResponse} containing the list of dishes and pagination details
	 * @throws ApiException if an invalid sort field is provided
	 */
	@Override
	public PostResponse getAllDishes(Integer pageNumber, Integer pageSize, String sortBy) {

	    Pageable pageable;
	    if ("orderCount".equalsIgnoreCase(sortBy)) {
	        pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "orderCount"));
	    } else if ("averageRating".equalsIgnoreCase(sortBy)) {
	        pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "averageRating"));
	    } else {
	    	throw new ApiException("Enter valid sortBy value");
	    }

        Page<Dish> pageDish = this.dishRepository.findAll(pageable);
        List<Dish> allDishes = pageDish.getContent();
		List<DishResponse> dishDtos = allDishes.stream().map(dish -> this.dishToDto(dish)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(dishDtos);
        postResponse.setPageNumber(pageDish.getNumber());
        postResponse.setPageSize(pageDish.getSize());
        postResponse.setTotalElements(pageDish.getTotalElements());
        postResponse.setTotalPages(pageDish.getTotalPages());
        postResponse.setLastPage(pageDish.isLast());

        return postResponse;
	}

	/**
	 * Deletes the dish with the specified ID.
	 *
	 * @param dishId the ID of the dish to delete
	 * @throws ResourceNotFoundException if the dish with the given ID is not found
	 */
	@Override
	public void deleteDish(Long dishId) {
		
		Dish dish = this.dishRepository.findById(dishId).orElseThrow(() ->
        new ResourceNotFoundException("Dish with given id is not found on server !! : " + dishId));
		this.dishRepository.delete(dish);

	}

	/**
	 * Retrieves a list of dishes associated with the specified cuisine.
	 *
	 * @param cuisine the cuisine to filter dishes by
	 * @return a list of dishes associated with the given cuisine
	 * @throws ResourceNotFoundException if no dishes are found for the given cuisine
	 */
	@Override
	public List<DishResponse> getDishesByCuisine(String cuisine) {
		
		Cuisine savedCuisin = cuisineRepository.findByCuisineTitle(cuisine).orElseThrow(() ->
        new ResourceNotFoundException("Dish with given cuisine is not found on server !! : " + cuisine));
        List<Dish> dishes = this.dishRepository.findByCuisine(savedCuisin);

        List<DishResponse> dishDtos = dishes.stream().map((dish) -> this.modelMapper.map(dish, DishResponse.class))
                .collect(Collectors.toList());

        return dishDtos;
	}

	/**
	 * Converts a {@link DishRequestDto} to a {@link Dish}.
	 *
	 * @param dishDto the DTO to convert
	 * @return the converted {@link Dish}
	 */
	public Dish dtoToDish(DishRequestDto dishDto) {
		Dish dish = this.modelMapper.map(dishDto, Dish.class);
		return dish;
	}

	/**
	 * Converts a {@link Dish} to a {@link DishResponse}.
	 *
	 * @param dish the entity to convert
	 * @return the converted {@link DishResponse}
	 */
	public DishResponse dishToDto(Dish dish) {
		DishResponse dishResponse = this.modelMapper.map(dish, DishResponse.class);
		if (dish.getFeedbacks() != null) {
            List<FeedbackDto> feedbackDtos = dish.getFeedbacks().stream()
                    .map(feedback -> modelMapper.map(feedback, FeedbackDto.class))
                    .collect(Collectors.toList());
            dishResponse.setFeedbacks(feedbackDtos);
        }
	    
		dishResponse.setAverageRating(dish.getAverageRating());
	    dishResponse.setOrderCount(dish.getOrderCount());
		return dishResponse;
	}



}
