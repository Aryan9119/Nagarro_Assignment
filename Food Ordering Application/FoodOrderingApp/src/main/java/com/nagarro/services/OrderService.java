package com.nagarro.services;

import java.util.List;

import com.nagarro.payloads.request.OrderDto;
import com.nagarro.payloads.response.OrderBillDto;

/**
 * Service interface for managing orders.
 * Provides methods for creating, retrieving, and updating orders.
 */
public interface OrderService {

    /**
     * Retrieves the bill for a specific order.
     *
     * @param orderId the unique identifier of the order
     * @param customerId the unique identifier of the customer
     * @return an OrderBillDto containing the details of the order's bill
     */
    OrderBillDto getOrderBill(Long orderId, Long customerId);

    /**
     * Retrieves all orders placed by a specific customer.
     *
     * @param customerId the unique identifier of the customer
     * @return a list of OrderDto containing the details of the orders placed by the customer
     */
    List<OrderDto> getOrdersByCustomer(Long customerId);

    /**
     * Creates a new order with the specified details.
     *
     * @param orderDto the order data transfer object containing the order details
     * @param customerId the unique identifier of the customer placing the order
     * @param dishNames a list of dish names included in the order
     * @return an OrderDto containing the details of the created order
     */
    OrderDto createOrder(OrderDto orderDto, Long customerId, List<String> dishNames);

    /**
     * Retrieves the details of a specific order.
     *
     * @param orderId the unique identifier of the order
     * @return an OrderDto containing the details of the specified order
     */
    OrderDto getOrderById(Long orderId);

    /**
     * Updates the status of a specific order.
     *
     * @param orderId the unique identifier of the order
     * @param updatedStatus the new status to be set for the order
     */
    void updateOrderStatus(Long orderId, String updatedStatus);
}
