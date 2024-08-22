package com.nagarro.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.constants.DeliveryStatusConstant;
import com.nagarro.entities.Customer;
import com.nagarro.entities.Dish;
import com.nagarro.entities.Order;
import com.nagarro.exceptions.ApiException;
import com.nagarro.payloads.request.OrderDto;
import com.nagarro.payloads.response.OrderBillDto;
import com.nagarro.repositories.CustomerRepository;
import com.nagarro.repositories.DishRepository;
import com.nagarro.repositories.OrderRepository;
import com.nagarro.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	 	@Autowired
	    private CustomerRepository customerRepository;

	    @Autowired
	    private DishRepository dishRepository;

	    @Autowired
	    private OrderRepository orderRepository;
	    
	    @Autowired
		private ModelMapper modelMapper;
	    
	    /**
		 * Creates a new order based on the provided order details and customer ID.
		 * <p>
		 * This method validates the provided dish names, calculates the total price, increments the order count for each dish, 
		 * and saves the order to the repository.
		 * </p>
		 *
		 * @param orderDto the details of the order to create
		 * @param customerId the ID of the customer placing the order
		 * @param dishNames the names of the dishes being ordered
		 * @return the created {@link OrderDto}
		 * @throws ApiException if any of the dish names are invalid
		 */
	    @Override
	    public OrderDto createOrder(OrderDto orderDto, Long customerId, List<String> dishNames) {
	        Customer customer = customerRepository.findById(customerId)
	            .orElseThrow(() -> new RuntimeException("Customer not found"));
	        
	        List<Dish> dishes =  dishRepository.findAllByNameIn(dishNames);
	        
	        if (dishes != null && !dishes.isEmpty()) {
	            double totalPrice = dishes.stream()
	                .peek(dish -> dish.incrementOrderCount())
	                .mapToDouble(Dish::getPrice)
	                .sum();
	            //to save changes for incremented order count
	            dishRepository.saveAll(dishes);
	            
	            Order order = this.modelMapper.map(orderDto, Order.class);
	            order.setOrderDate(new Date(System.currentTimeMillis()));
	            order.setTotalPrice(totalPrice);
	            order.setDeliveryAddress(orderDto.getDeliveryAddress());
	            order.setDeliveryStatus(DeliveryStatusConstant.PENDING);
	            order.setCustomer(customer);
	            order.setDishes(dishes);

	            Order savedOrder = orderRepository.save(order);
	            return this.modelMapper.map(savedOrder, OrderDto.class);
	        } else {
	            throw new ApiException("Seems like you entered an invalid dish name, please enter valid dish name for placing an order");
	        }
	    }

	    /**
		 * Updates the delivery status of an existing order.
		 *
		 * @param orderId the ID of the order to update
		 * @param status the new delivery status
		 */
	    @Override
	    public void updateOrderStatus(Long orderId, String status) {
	        Order order = orderRepository.findById(orderId)
	            .orElseThrow(() -> new RuntimeException("Order not found"));
	        order.setDeliveryStatus(status);
	        orderRepository.save(order);
	    }

	    /**
		 * Retrieves a list of orders placed by a specific customer.
		 *
		 * @param customerId the ID of the customer whose orders are to be retrieved
		 * @return a list of {@link OrderDto} representing the orders placed by the customer
		 */
		@Override
		public List<OrderDto> getOrdersByCustomer(Long customerId) {
			// TODO Auto-generated method stub
			Customer customer = customerRepository.findById(customerId)
		            .orElseThrow(() -> new RuntimeException("Customer not found"));
			List<Order> orders = orderRepository.findByCustomer(customer);
			List<OrderDto> orderDtos = orders.stream().map((order) -> this.modelMapper.map(order, OrderDto.class))
	                .collect(Collectors.toList());
			return orderDtos;
		}

		/**
		 * Retrieves the details of a specific order by its ID.
		 *
		 * @param orderId the ID of the order to retrieve
		 * @return the {@link OrderDto} representing the order
		 */
		@Override
		public OrderDto getOrderById(Long orderId) {
			Order order = orderRepository.findById(orderId).get();
			OrderDto orderDto = this.modelMapper.map(order, OrderDto.class);
			return orderDto;
		}
	
		/**
		 * Generates the bill for a specific order.
		 * <p>
		 * Includes the customer's name in the bill details.
		 * </p>
		 *
		 * @param orderId the ID of the order for which the bill is to be generated
		 * @param customerId the ID of the customer associated with the order
		 * @return the {@link OrderBillDto} representing the bill for the order
		 */
		@Override
		public OrderBillDto getOrderBill(Long orderId, Long customerId) {
			Customer customer = customerRepository.findById(customerId)
		            .orElseThrow(() -> new RuntimeException("Customer not found"));
			Order order = orderRepository.findById(orderId).get();
			OrderBillDto bill = this.modelMapper.map(order, OrderBillDto.class);
			bill.setCustomerName(customer.getUsername());
	        return bill;
	    }

}
