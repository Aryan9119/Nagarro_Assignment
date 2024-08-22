//package com.nagarro;
// 
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// 
//import static org.mockito.Mockito.*;
// 
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nagarro.controllers.UserController;
//import com.nagarro.entities.User;
//import com.nagarro.payloads.CountryDto;
//import com.nagarro.payloads.DOB;
//import com.nagarro.payloads.Name;
//import com.nagarro.payloads.PageInfo;
//import com.nagarro.payloads.PostResponse;
//import com.nagarro.payloads.Register;
//import com.nagarro.payloads.Result;
//import com.nagarro.payloads.UserDto;
//import com.nagarro.repositories.UserRepository;
//import com.nagarro.services.ApiService;
//import com.nagarro.services.ExternalApiHandler;
//import com.nagarro.services.UserService;
//import com.nagarro.services.ValidatorService;
// 
//import reactor.core.publisher.Mono;
// 
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.reactive.function.client.WebClient;
// 
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
// 
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.junit.jupiter.api.Assertions.assertEquals;
// 
//public class UserControllerTest {
// 
//    @Mock
//    private UserRepository userRepository;
// 
//    @InjectMocks
//    private UserController userController;
// 
//    private MockMvc mockMvc;
// 
//    @Mock
//    private ValidatorService validatorService;
// 
//    @Mock
//    private UserService userService;
// 
//    @Mock
//    private ApiService apiService;
// 
//    @Mock
//    private ExternalApiHandler externalApiHandler;
//    
//    @Mock
//    UserDto userDto;
// 
//    private final User RECORD_1 = new User(1, "Mr Roberto Vidal", 74, "male", "1949-05-29T02:14:57.446Z", "ES",
//            "TO_BE_VERIFIED", "1949-05-29T02:14:57.446Z", "1949-05-29T02:14:57.446Z");
//    private final User RECORD_2 = new User(2, "Mr Austin Dunn", 49, "male", "1974-10-15T08:12:46.824Z", "AU",
//            "TO_BE_VERIFIED", "1974-10-15T08:12:46.824Z", "1974-10-15T08:12:46.824Z");
// 
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//    }
// 
//    
//    @Test
//    public void testCreateUser() throws Exception {
//        String size = "5";
//        int sizeValue = 5;
// 
//        doNothing().when(validatorService).validateParameter(eq("size"), eq(size));
// 
//        UserDto mockedUserDto = new UserDto();
//        Result[] results = new Result[1];
//        results[0] = generateDummyResult();
//        mockedUserDto.setResults(results);
//        when(apiService.callAPI1(sizeValue)).thenReturn(mockedUserDto);
// 
//        User user = mapResultToUser(results[0]);
//        when(externalApiHandler.apiProcessing(any(Result.class))).thenReturn(user);
// 
//        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).param("size", size))
//                .andExpect(status().isOk());
// 
//        verify(validatorService, times(1)).validateParameter("size", size);
//        verify(apiService, times(1)).callAPI1(sizeValue);
//        verify(externalApiHandler, times(1)).apiProcessing(any(Result.class));
//    }
// 
// 
// 
//    public static Result generateDummyResult() {
//        Result result = new Result();
// 
//        // Generate random data for the Result
//        result.setGender("Male");
//        result.setName(new Name("Mr", "Satyam", "Prajapati"));
//        result.setNat("Indian");
//        result.setEmail("saurabh.nagarro@gmail.com");
//        result.setPhone("9084899870");
//        result.setCity("Hathras");
//        result.setState("Uttar Pradesh");
//        result.setPostcode("12345");
//        result.setRegistered(new Register("2023-01-01", 20));
//        result.setDob(new DOB("2023-01-01", 20));
// 
//        return result;
//    }
// 
// 
//}