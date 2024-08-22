package com.nagarro.services;

import com.nagarro.payloads.request.FeedbackDto;
import com.nagarro.payloads.response.FeedbackResponse;

/**
 * Service interface for managing feedback.
 * Provides methods for creating and deleting feedback.
 */
public interface FeedbackService {

    /**
     * Creates a new feedback entry for a specific dish by a customer.
     *
     * @param feedbackDto the feedback data transfer object containing the details of the feedback to be created
     * @param customerId the unique identifier of the customer providing the feedback
     * @param dishName the name of the dish that the feedback is related to
     * @return a feedback response data transfer object containing the details of the created feedback
     */
    FeedbackResponse createFeedback(FeedbackDto feedbackDto, Long customerId, String dishName);

    /**
     * Deletes a specific feedback entry.
     *
     * @param feedbackId the unique identifier of the feedback to be deleted
     */
    void deleteFeedback(Integer feedbackId);
}
