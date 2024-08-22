package com.nagarro.services;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.entities.User;
import com.nagarro.payloads.Country;
import com.nagarro.payloads.CountryDto;
import com.nagarro.payloads.GenderDto;
import com.nagarro.payloads.Result;
import com.nagarro.repositories.UserRepository;

/**
 * Service class responsible for processing data received from external APIs and saving it to the database.
 */
@Service
public class ExternalApiHandler {

    @Autowired
    ApiService apiService;

    @Autowired
    UserRepository userRepo;

    /**
     * Processes the data from the external API, validates it, and saves the user to the database.
     *
     * @param result The result object containing user data.
     * @return The saved {@link User} object after processing and validation.
     */
    public User apiProcessing(Result result) {
        String firstName = result.getName().getFirst();

        // Asynchronously call API2 and API3
        CompletableFuture<CountryDto> countryFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return this.apiService.callAPI2(firstName);
            } catch (Exception e) {
                return null;
            }
        });

        CompletableFuture<GenderDto> genderFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return this.apiService.callAPI3(firstName);
            } catch (Exception e) {
                return null;
            }
        });

        // Wait for both API calls to complete
        CompletableFuture.allOf(countryFuture, genderFuture).join();

        // Get results from API calls
        CountryDto countryData = countryFuture.join();
        GenderDto genderData = genderFuture.join();
        Country countries[] = countryData.getCountry();

        // Create a new User object
        User user = new User();
        user.setName(result.getName().getTitle() + result.getName().getFirst() + result.getName().getLast());
        user.setGender(result.getGender());
        user.setAge(result.getDob().getAge());
        user.setDateCreated(result.getDob().getDate());
        user.setDateModified(result.getDob().getDate());
        user.setNationality(result.getNat());
        user.setDob(result.getDob().getDate());

        // Validate and set verification status
        if (genderData.getGender().equals(result.getGender())) {
            boolean isCountryExist = Arrays.stream(countries)
                    .anyMatch(country -> result.getNat().equals(country.getCountry_id()));
            if (isCountryExist) {
                user.setVerificationStatus("VERIFIED");
            } else {
                user.setVerificationStatus("TO_BE_VERIFIED");
            }
        } else {
            user.setVerificationStatus("TO_BE_VERIFIED");
        }

        // Save the user to the database
        return userRepo.save(user);
    }
}