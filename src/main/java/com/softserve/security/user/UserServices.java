package com.softserve.security.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.softserve.dto.CodeVerificationDTO;
import com.softserve.dto.EmailContent;
import com.softserve.dto.ForgotPasswordDT;
import com.softserve.model.Request;
import com.softserve.model.Role;
import com.softserve.model.Token;
import com.softserve.model.User;
import com.softserve.repository.IUserRepository;
import com.softserve.service.RequestService;
import com.softserve.service.TokenService;
import com.softserve.util.Email;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServices implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private Email email;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private RequestService requestService;
	
	private String EMAIL_USERNAME;
	
	private String TEMPLATE_FORGOT_PASSWORD;
	
	private String URL_RECOVERY_PASSWORD;
	
	
	
	
	public UserServices(@Value("${template.forgot-password}") String TEMPLATE_FORGOT_PASSWORD,
						@Value("${spring.mail.username}") String EMAIL_USERNAME,
						@Value("${custom-url.recovery-password}") String URL_RECOVERY_PASSWORD) {
		this.TEMPLATE_FORGOT_PASSWORD = TEMPLATE_FORGOT_PASSWORD;
		this.EMAIL_USERNAME =  EMAIL_USERNAME;
		this.URL_RECOVERY_PASSWORD = URL_RECOVERY_PASSWORD;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		final Optional<User> optionalUseruser = userRepository.findByEmail(userName);
		if (optionalUseruser.isPresent()) {

			User user = optionalUseruser.get();
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getUserPassword(),
					mapAutorities(user.getRoles()));
		} else
			throw new UsernameNotFoundException("User or password wrong.");
	}

	private Collection<? extends GrantedAuthority> mapAutorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	public void findByEmail(final String email, Model model) {
	}

	public void forgotPasswordProcess(ForgotPasswordDT forgotPasswordDT, Model model) {
		final Optional<User> userOp = this.userRepository.findByEmail(forgotPasswordDT.getEmail());
		if (userOp.isPresent()) {
			try {
				Map<String, Object> emailContentData = loadMap(forgotPasswordDT);
				
				EmailContent emailContent = new EmailContent();
				emailContent.setSubject("Restore password");
				emailContent.setTo(forgotPasswordDT.getEmail());
				emailContent.setTemplate(TEMPLATE_FORGOT_PASSWORD);
				emailContent.setModel(emailContentData);
				
				this.email.sendMessage(emailContent);
				model.addAttribute("emailSended", "Email has been sended successfully, please check your inbox");
				log.info("Email was sended successfully for -> {}", forgotPasswordDT.getEmail());
			} catch (MailException | InterruptedException | ExecutionException | MessagingException | IOException
					| TemplateException e) {
				log.error("Error when email was builded -> {}", e);
				e.printStackTrace();
			}

		} else {
			model.addAttribute("userNotFounded", "The email provided was not valid");
			log.error("Email was not sended successfully for -> {}", forgotPasswordDT.getEmail());
		}

	}

	private Map<String, Object> loadMap(ForgotPasswordDT forgotPasswordDT) {
		Map<String, Object> content = new HashMap<>();
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
	    
		content.put("date", myDateObj.format(myFormatObj));
		
		String secureCodeToken = RandomStringUtils.random(5, false, true);
		content.put("token", secureCodeToken);
		
		Optional<User> user = this.userRepository.findByEmail(forgotPasswordDT.getEmail());
		Optional<Request> request = this.requestService.findById(1);
		
		Token token = new Token();
		token.setValue(secureCodeToken);
		token.setIsValid(true);
		
		Instant instant = Instant.now();
		
		token.setCreationDate(Timestamp.from(instant));
		token.setExpireDate(Timestamp.from(instant.plusSeconds(15*60)));
		token.setUser(user.get());
		token.setRequest(request.get());
		
		this.tokenService.save(token);
		
		final String urlRecovery = String.format(URL_RECOVERY_PASSWORD, token.getIdToken());
		
		content.put("urlForgot", urlRecovery);
		
		return content;
	}

	public void verificationCodeProcess(int id, Model model) {
		Optional<Token> tokenOptional = this.tokenService.findById(id);
		model.addAttribute("idToken", id);
		
		if(tokenOptional.isPresent()) {
			final String message = String.format("We emailed you the five digit code to %s", tokenOptional.get().getUser().getEmail());
			model.addAttribute("message", message);	
		}else {
			model.addAttribute("error", "Something wrong with your token, please send another forgot password request");
		}
		
	}

	public void codeVerificationRequest(int id, Model model, CodeVerificationDTO codeVerificationDTO) {
		// TODO Auto-generated method stub
		
	}
}
