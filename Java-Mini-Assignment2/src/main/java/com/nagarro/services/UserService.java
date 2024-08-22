package com.nagarro.services;

import com.nagarro.payloads.PostResponse;

/**
 * Service interface defining methods for managing user-related operations.
 */
public interface UserService {

    /**
     * Retrieves a paginated list of users based on the specified offset, page size, sorting type, and sorting order.
     *
     * @param offset    The offset for pagination.
     * @param pageSize  The number of users per page.
     * @param sortType  The type by which to sort the users (e.g., "name" or "age").
     * @param sortOrder The order in which to sort the users (e.g., "asc" or "desc").
     * @return A {@link PostResponse} containing the paginated list of users based on the provided parameters.
     */
    PostResponse getAllUsers(Integer offset, Integer pageSize, String sortType, String sortOrder);
}

