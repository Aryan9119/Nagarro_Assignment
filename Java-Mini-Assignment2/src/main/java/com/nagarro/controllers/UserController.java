package com.nagarro.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.entities.User;
import com.nagarro.payloads.PostResponse;
import com.nagarro.payloads.Result;
import com.nagarro.payloads.UserDto;
import com.nagarro.services.ApiService;
import com.nagarro.services.ExternalApiHandler;
import com.nagarro.services.UserService;
import com.nagarro.services.ValidatorService;
/**
 * Controller class for handling user-related endpoints.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    ApiService apiService;

    @Autowired
    ExternalApiHandler externalApiHandler;

    @Autowired
    ValidatorService validatorService;

    @Autowired
    UserService userservice;

    /**
     * Retrieves a list of users with optional sorting and pagination.
     *
     * @param sortType  The type by which the users should be sorted (default: name).
     * @param sortOrder The order in which the users should be sorted (default: even).
     * @param limit     The maximum number of users to be returned (default: 5).
     * @param offset    The starting index for pagination (default: 0).
     * @return ResponseEntity containing a {@link PostResponse} with the list of users and metadata.
     */
    @GetMapping
    public ResponseEntity<PostResponse> getUsers(
            @RequestParam(value = "sortType", required = false, defaultValue = "name") String sortType,
            @RequestParam(value = "sortOrder", required = false, defaultValue = "odd") String sortOrder,
            @RequestParam(value = "limit", required = false, defaultValue = "5") String limit,
            @RequestParam(value = "offset", required = false, defaultValue = "0") String offset) {
        this.validatorService.validations(sortType, sortOrder, limit, offset);
        PostResponse postResponse = this.userservice.getAllUsers(Integer.parseInt(offset), Integer.parseInt(limit),
                sortType, sortOrder);
        return ResponseEntity.ok(postResponse);
    }

    /**
     * Creates a specified number of users and returns the list.
     *
     * @param size The number of users to create.
     * @return ResponseEntity containing a list of created {@link User} objects.
     */
    @PostMapping
    public ResponseEntity<List<User>> createUser(@RequestParam String size) {
        this.validatorService.validateParameter("size", size);
        int sizeValue = Integer.parseInt(size);
        UserDto userData = this.apiService.callAPI1(sizeValue);
        Result[] results = userData.getResults();

        List<User> userList = Arrays.stream(results).map(result -> externalApiHandler.apiProcessing(result))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userList);
    }

}
