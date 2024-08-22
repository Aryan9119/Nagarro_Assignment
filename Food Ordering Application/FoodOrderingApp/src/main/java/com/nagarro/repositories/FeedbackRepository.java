package com.nagarro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}
