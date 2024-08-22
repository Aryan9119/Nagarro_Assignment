package com.nagarro.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.entities.Customer;
import com.nagarro.entities.Dish;
import com.nagarro.entities.Feedback;
import com.nagarro.entities.Order;
import com.nagarro.exceptions.ApiException;
import com.nagarro.exceptions.ResourceNotFoundException;
import com.nagarro.payloads.request.FeedbackDto;
import com.nagarro.payloads.response.FeedbackResponse;
import com.nagarro.repositories.CustomerRepository;
import com.nagarro.repositories.DishRepository;
import com.nagarro.repositories.FeedbackRepository;
import com.nagarro.repositories.OrderRepository;
import com.nagarro.services.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private DishRepository dishRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Creates feedback for a dish by a customer.
	 *
	 * @param feedbackDto the details of the feedback to create
	 * @param customerId the ID of the customer providing feedback
	 * @param dishName the name of the dish being reviewed
	 * @return the created {@link FeedbackResponse}
	 * @throws ApiException if the rating is invalid
	 * @throws ResourceNotFoundException if the customer or dish is not found or if the customer has not ordered the dish
	 */
	@Override
	public FeedbackResponse createFeedback(FeedbackDto feedbackDto,Long customerId, String dishName) {
		
		if(feedbackDto.getRating()>5 || feedbackDto.getRating()<0) {
			throw new ApiException("Enter a valid rating");
		}		
		Customer customer = this.customerRepository.findById(customerId).orElseThrow(() ->
	    new ResourceNotFoundException("Dish with given id is not found on server !! : " + customerId));

		Dish dish = this.dishRepository.findByName(dishName);
		if(dish==null) {		
			throw new ResourceNotFoundException("Dish with given name is not found on server !! : " + dishName);
		}	    
		
        List<Order> orders = this.orderRepository.findByCustomerAndDishes(customer, dish);
        if (orders.isEmpty()) {
            throw new RuntimeException("Customer has not ordered this dish");
        }
				
		Feedback feedback = this.modelMapper.map(feedbackDto, Feedback.class);		
		feedback.setCustomer(customer);
		feedback.setDish(dish);
		Feedback savedFeedback = this.feedbackRepository.save(feedback);
		
		List<Feedback> dishFeedbacks = dish.getFeedbacks();
		dishFeedbacks.add(savedFeedback);

		dish.setAverageRating(reCalculateAverageRating(dishFeedbacks));
        dishRepository.save(dish);
        
        FeedbackResponse feedbackResponse = this.modelMapper.map(savedFeedback, FeedbackResponse.class);
		return feedbackResponse;
	}

	/**
	 * Deletes feedback with the specified ID.
	 * <p>
	 * This method also logs debug information to ensure the feedback was deleted.
	 * </p>
	 *
	 * @param feedbackId the ID of the feedback to delete
	 * @throws ResourceNotFoundException if the feedback with the given ID is not found
	 */
	@Override
	@Transactional
	public void deleteFeedback(Integer feedbackId) {

		Feedback feedback = this.feedbackRepository.findById(feedbackId).orElseThrow(() ->
				new ResourceNotFoundException("Feedback with given id is not found on server !! : " + feedbackId));
		System.out.println("Deleting feedback with ID: " + feedbackId);
		
		this.feedbackRepository.delete(feedback);
		
		System.out.println("Feedback deleted from repository.");

		if (this.feedbackRepository.findById(feedbackId).isPresent()) {
		    System.out.println("Feedback was not deleted from the database.");
		} else {
		    System.out.println("Feedback successfully deleted from the database.");
		}
	}

	/**
	 * Recalculates the average rating for a dish based on a list of feedbacks.
	 *
	 * @param feedbacks the list of feedbacks to use for recalculation
	 * @return the recalculated average rating
	 */
	public double reCalculateAverageRating(List<Feedback> feedbacks) {

		double totalRating = feedbacks.stream()
                .mapToDouble(Feedback::getRating)
                .sum();
		System.out.println("total rating = "+ totalRating);
		double averageRating =0.0;
		averageRating = totalRating/feedbacks.size() ;
		return averageRating;
				
	}

}
