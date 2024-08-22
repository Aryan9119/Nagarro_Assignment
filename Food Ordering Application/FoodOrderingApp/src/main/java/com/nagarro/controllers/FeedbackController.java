package com.nagarro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.payloads.request.FeedbackDto;
import com.nagarro.payloads.response.ApiResponse;
import com.nagarro.payloads.response.FeedbackResponse;
import com.nagarro.services.FeedbackService;

/**
 * Controller class for handling HTTP requests related to feedback.
 * 
 * Provides endpoints to add and delete feedback for dishes, with role-based access control.
 * 
 * @author Aryan Verma
 */
@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * Adds feedback for a specific dish from a specific customer.
     * 
     * @param feedbackDto the feedback details to be added
     * @param customerId the unique ID of the customer giving the feedback
     * @param dishName the name of the dish the feedback is for
     * @return a response entity containing the added feedback and HTTP status code
     */
    @PostMapping("/customer/{customerId}/dish/{name}/feedbacks")
    @PreAuthorize("authentication.principal.customerId == #customerId")
    public ResponseEntity<FeedbackResponse> addFeedback(@RequestBody FeedbackDto feedbackDto, @PathVariable("customerId") Long customerId, @PathVariable("name") String dishName) {

        FeedbackResponse feedback = this.feedbackService.createFeedback(feedbackDto, customerId, dishName);
        return new ResponseEntity<FeedbackResponse>(feedback, HttpStatus.CREATED);
    }

    /**
     * Deletes a specific feedback by its unique ID.
     * 
     * @param feedbackId the unique ID of the feedback to be deleted
     * @return a response entity with a success message and HTTP status code
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/feedbacks/{feedbackId}")
    public ResponseEntity<ApiResponse> deleteFeedback(@PathVariable("feedbackId") Integer feedbackId) {

        this.feedbackService.deleteFeedback(feedbackId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Feedback deleted successfully !!", true), HttpStatus.OK);
    }

}
