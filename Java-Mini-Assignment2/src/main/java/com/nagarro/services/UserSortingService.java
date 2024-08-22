package com.nagarro.services;

import org.springframework.stereotype.Service;

import com.nagarro.entities.User;
import com.nagarro.services.Impl.SortByAgeEvenStrategy;
import com.nagarro.services.Impl.SortByAgeOddStrategy;
import com.nagarro.services.Impl.SortByNameEvenStrategy;
import com.nagarro.services.Impl.SortByNameOddStrategy;

import java.util.List;

/**
 * Service class responsible for sorting a list of users based on specified criteria.
 */
@Service
public class UserSortingService {

    private final SortByAgeEvenStrategy sortByAgeEvenStrategy;
    private final SortByNameOddStrategy sortByNameOddStrategy;
    private final SortByAgeOddStrategy sortByAgeOddStrategy;
    private final SortByNameEvenStrategy sortByNameEvenStrategy;

    /**
     * Constructs a new instance of the UserSortingService with the provided sorting strategies.
     *
     * @param sortByAgeEvenStrategy   The strategy for sorting users by age in even order.
     * @param sortByNameOddStrategy   The strategy for sorting users by name in odd order.
     * @param sortByAgeOddStrategy    The strategy for sorting users by age in odd order.
     * @param sortByNameEvenStrategy  The strategy for sorting users by name in even order.
     */
    public UserSortingService(SortByAgeEvenStrategy sortByAgeEvenStrategy, SortByNameOddStrategy sortByNameOddStrategy,
                              SortByAgeOddStrategy sortByAgeOddStrategy, SortByNameEvenStrategy sortByNameEvenStrategy) {
        this.sortByAgeEvenStrategy = sortByAgeEvenStrategy;
        this.sortByNameOddStrategy = sortByNameOddStrategy;
        this.sortByAgeOddStrategy = sortByAgeOddStrategy;
        this.sortByNameEvenStrategy = sortByNameEvenStrategy;
    }

    /**
     * Sorts a list of users based on the specified sort type and sort order.
     *
     * @param userList  The list of users to be sorted.
     * @param sortType  The type by which to sort the users (e.g., "Name" or "Age").
     * @param sortOrder The order in which to sort the users (e.g., "Even" or "Odd").
     * @return A sorted list of users based on the provided criteria.
     */
    public List<User> sortUsers(List<User> userList, String sortType, String sortOrder) {
        UserSortStrategy strategy = determineStrategy(sortType, sortOrder);

        if (strategy != null) {
            return strategy.sort(userList);
        } else {
            return userList;
        }
    }

    private UserSortStrategy determineStrategy(String sortType, String sortOrder) {
        if ("Age".equalsIgnoreCase(sortType) && "Even".equalsIgnoreCase(sortOrder)) {
            return sortByAgeEvenStrategy;
        } else if ("Name".equalsIgnoreCase(sortType) && "Odd".equalsIgnoreCase(sortOrder)) {
            return sortByNameOddStrategy;
        } else if ("Age".equalsIgnoreCase(sortType) && "Odd".equalsIgnoreCase(sortOrder)) {
            return sortByAgeOddStrategy;
        } else if ("Name".equalsIgnoreCase(sortType) && "Even".equalsIgnoreCase(sortOrder)) {
            return sortByNameEvenStrategy;
        }
        return null;
    }
}
