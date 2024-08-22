package com.nagarro;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import com.nagarro.entities.User;
import com.nagarro.payloads.PostResponse;
import com.nagarro.repositories.UserRepository;
import com.nagarro.services.UserSortingService;
import com.nagarro.services.Impl.UserServiceImpl;

@SpringBootTest
class JavaMiniAssignment2ApplicationTests {

	@Test
	void contextLoads() {
	}
}

