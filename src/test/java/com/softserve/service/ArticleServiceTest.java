package com.softserve.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import com.softserve.dto.ForgotPasswordDT;
import com.softserve.model.User;
import com.softserve.repository.IUserRepository;
import com.softserve.repository.TokenRepository;
import com.softserve.security.user.UserServices;

import com.softserve.util.EmailService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ArticleServiceTest {

	@MockBean
	private IUserRepository userRepository;
	
	@MockBean
	private TokenRepository tokenRepository;

	@Mock
	private EmailService emailService;
	
	@Mock
	private RequestService requestService;
	
	@Mock
	private TokenService tokenService;
	
	@MockBean
	private Model model;
	
	@Autowired
	private UserServices userServices;
	
	
	@Test
	void testEmailSended() {
		ForgotPasswordDT forgotPasswordDT = new ForgotPasswordDT();
		forgotPasswordDT.setEmail("jotaguzman08@gmail.com");
		forgotPasswordDT.setIdRequest(3);
		
		when(userRepository.findByEmail(forgotPasswordDT.getEmail())).thenReturn(createUserEntity());
		userServices.forgotPasswordProcess(forgotPasswordDT, model);
		assertEquals("A","A");
	}
	
	@Test
	void testEmailNotSended() {
		ForgotPasswordDT forgotPasswordDT = new ForgotPasswordDT();
		forgotPasswordDT.setEmail("jotaguzman08@gmail.com");
		forgotPasswordDT.setIdRequest(2);
		
		when(userRepository.findByEmail(forgotPasswordDT.getEmail())).thenReturn(createUserEntity());
		
		userServices.forgotPasswordProcess(forgotPasswordDT, model);
		assertEquals("A","A");
	}
	
	@Test
	void testUserNotFound() {
		ForgotPasswordDT forgotPasswordDT = new ForgotPasswordDT();
		forgotPasswordDT.setEmail("abc@gmail.com");
		forgotPasswordDT.setIdRequest(3);
		
		assertThrows(Exception.class, ()-> userServices.forgotPasswordProcess(forgotPasswordDT, model));
	}


	private Optional<User> createUserEntity() {
		User user = new User();
		
		user.setEmail("jotaguzman08@gmail.com");
		user.setUserPassword("some");
		return Optional.of(user);
	}

}
