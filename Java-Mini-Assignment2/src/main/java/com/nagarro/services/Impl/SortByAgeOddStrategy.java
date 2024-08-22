package com.nagarro.services.Impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nagarro.entities.User;
import com.nagarro.services.UserSortStrategy;

/**
 * Sorting strategy for sorting a list of users by age, with odd ages first.
 */
@Service
public class SortByAgeOddStrategy implements UserSortStrategy {

    /**
     * Sorts a list of users by age, with odd ages coming first.
     *
     * @param userList The list of users to be sorted.
     * @return The sorted list of users.
     */
    @Override
    public List<User> sort(List<User> userList) {
        return userList.stream()
                .sorted(Comparator.comparingInt((User u) -> u.getAge() % 2 == 0 ? 1 : 0).thenComparing(User::getAge))
                .collect(Collectors.toList());
    }
}
