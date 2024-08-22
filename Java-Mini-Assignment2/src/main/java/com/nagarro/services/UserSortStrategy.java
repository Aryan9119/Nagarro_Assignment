package com.nagarro.services;

import java.util.List;

import com.nagarro.entities.User;

/**
 * Interface defining a strategy for sorting a list of users.
 */
public interface UserSortStrategy {

    /**
     * Sorts a list of users based on the implementing strategy.
     *
     * @param userList The list of users to be sorted.
     * @return A sorted list of users based on the implementing strategy.
     */
    List<User> sort(List<User> userList);
}
