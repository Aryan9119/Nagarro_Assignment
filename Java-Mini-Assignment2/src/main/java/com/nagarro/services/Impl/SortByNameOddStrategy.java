package com.nagarro.services.Impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nagarro.entities.User;
import com.nagarro.services.UserSortStrategy;


/**
 * Sorting strategy for sorting a list of users by name length, with odd lengths first.
 */
@Service
public class SortByNameOddStrategy implements UserSortStrategy {

    /**
     * Sorts a list of users by name length, with odd lengths coming first.
     *
     * @param userList The list of users to be sorted.
     * @return The sorted list of users.
     */
    @Override
    public List<User> sort(List<User> userList) {
        return userList.stream()
                .sorted(Comparator.comparingInt((User u) -> u.getName().length() % 2 == 0 ? 1 : 0).thenComparing(User::getName))
                .collect(Collectors.toList());
    }
}
