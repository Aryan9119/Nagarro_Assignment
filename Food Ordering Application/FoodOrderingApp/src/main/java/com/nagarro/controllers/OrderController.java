package com.nagarro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.payloads.request.OrderDto;
import com.nagarro.payloads.response.ApiResponse;
import com.nagarro.payloads.response.OrderBillDto;
import com.nagarro.services.OrderService;

/**
 * Controller class for managing orders.
 * 
 * Provides endpoints for placing orders, updating order status, retrieving orders by customer, 
 * and getting order details and bills.
 * 
 * @author Aryan Verma
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * Places a new order for a customer.
     * 
     * @param orderDto the order details
     * @param customerId the ID of the customer placing the order
     * @param dishNames a list of dish names included in the order
     * @return a response entity containing a success message
     */
    @PostMapping("/place/customer/{customerId}")
    @PreAuthorize("authentication.principal.customerId == #customerId")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderDto orderDto, @PathVariable("customerId") Long customerId, @RequestParam List<String> dishNames) {

        OrderDto order = this.orderService.createOrder(orderDto, customerId, dishNames);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Order Placed Successfully, Thanks for choosing us, Your order will be delivered to your doorstep shortly. Your Order Id = "+ order.getOrderId(), true), HttpStatus.OK);
    }
    
    /**
     * Updates the status of an existing order.
     * 
     * @param orderId the ID of the order to update
     * @param status the new delivery status of the order
     * @return a response entity with HTTP status 200 (OK)
     */
    @PutMapping("/{orderId}/status/{deliveryStatus}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable("orderId") Long orderId, @PathVariable("deliveryStatus") String status) {
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok().build();
    }
    
    /**
     * Retrieves all orders placed by a specific customer.
     * 
     * @param customerId the ID of the customer whose orders to retrieve
     * @return a response entity containing a list of orders
     */
    @GetMapping("/get/customer/{customerId}")
    @PreAuthorize("authentication.principal.customerId == #customerId")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomer(@PathVariable("customerId") Long customerId) {
        List<OrderDto> orders = this.orderService.getOrdersByCustomer(customerId);
        return ResponseEntity.ok(orders);
    }
    
    /**
     * Retrieves the details of a specific order.
     * 
     * @param orderId the ID of the order to retrieve
     * @return a response entity containing the order details
     */
    @GetMapping("/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderDto> getSingleOrder(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(this.orderService.getOrderById(orderId));
    }
    
    /**
     * Retrieves the bill for a specific order.
     * 
     * @param orderId the ID of the order
     * @param customerId the ID of the customer requesting the bill
     * @return a response entity containing the order bill
     */
    @GetMapping("/{orderId}/bill/customer/{customerId}")
    @PreAuthorize("authentication.principal.customerId == #customerId")
    public ResponseEntity<OrderBillDto> getOrderBill(@PathVariable("orderId") Long orderId, @PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(this.orderService.getOrderBill(orderId, customerId));
    }
}
