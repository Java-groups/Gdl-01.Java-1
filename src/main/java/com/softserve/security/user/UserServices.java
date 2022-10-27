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

import com.softserve.dto.UserDTO;
import com.softserve.exceptions.UserException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.softserve.dto.CodeVerificationDTO;
import com.softserve.dto.EmailContent;
import com.softserve.dto.ForgotPasswordDT;
import com.softserve.dto.ResetPasswordDTO;
import com.softserve.exceptions.ForgotPasswordProcessException;
import com.softserve.model.Request;
import com.softserve.model.Role;
import com.softserve.model.Token;
import com.softserve.model.User;
import com.softserve.repository.IUserRepository;
import com.softserve.service.RequestService;
import com.softserve.service.TokenService;
import com.softserve.util.EmailService;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServices implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private RequestService requestService;
	
	private String TEMPLATE_FORGOT_PASSWORD;
	
	private String URL_RECOVERY_PASSWORD;
	
	
	
	
	public UserServices(@Value("${template.forgot-password}") String TEMPLATE_FORGOT_PASSWORD,
						@Value("${spring.mail.username}") String EMAIL_USERNAME,
						@Value("${custom-url.recovery-password}") String URL_RECOVERY_PASSWORD) {
		this.TEMPLATE_FORGOT_PASSWORD = TEMPLATE_FORGOT_PASSWORD;
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

	public Optional<User> findByEmail(final String email) {
		return this.userRepository.findByEmail(email);
	}

	public void forgotPasswordProcess(ForgotPasswordDT forgotPasswordDT, Model model) {
		final Optional<User> userOp = this.userRepository.findByEmail(forgotPasswordDT.getEmail());
		forgotPasswordDT.setIdRequest(3);
		if (userOp.isPresent()) {
			try {
				
				
				Map<String, Object> emailContentData = loadMap(forgotPasswordDT, userOp.get());
				
				EmailContent emailContent = new EmailContent();
				emailContent.setSubject("Restore password");
				emailContent.setTo(forgotPasswordDT.getEmail());
				emailContent.setTemplate(TEMPLATE_FORGOT_PASSWORD);
				emailContent.setModel(emailContentData);
				
				this.emailService.sendMessage(emailContent);
				
				model.addAttribute("emailSended", "Email has been sended successfully, please check your inbox");
				log.info("Email was sended successfully for -> {}", forgotPasswordDT.getEmail());
				
			} catch (MailException | InterruptedException | ExecutionException | MessagingException | IOException
					| TemplateException e) {
				
				log.error("Error when email was builded -> {}", e);
				model.addAttribute("error" , "The email was not sended.");
			} catch (ForgotPasswordProcessException e) {
				model.addAttribute("error" , "Something went wrong with the forgot password request, please contact your admin.");
				log.error("Forgot password went wrong -> {}", e);
				
			}

		} else {
			model.addAttribute("userNotFounded", "The email provided was not valid");
			log.error("Email was not sended successfully for -> {}", forgotPasswordDT.getEmail());
		}

	}

	public void verificationCodeProcess(int id, Model model) {
		Optional<Token> tokenOptional = this.tokenService.findById(id);
		model.addAttribute("idToken", id);
		
		if(tokenOptional.isPresent()) {
			final String message = String.format("We emailed you the five digit code to %s", tokenOptional.get().getUser().getEmail());
			model.addAttribute("message", message);	
			model.addAttribute("token", id);
		}else {
			model.addAttribute("error", "Something wrong with your token, please send another forgot password request");
		}
		
	}

	public String codeVerificationRequest(int id, Model model, CodeVerificationDTO codeVerificationDTO, RedirectAttributes redirectAttributes) {
		Optional<Token> tokenOptional = this.tokenService.findById(id);
		if(tokenOptional.isPresent()) {
			final String code = codeVerificationDTO.toString();
			final Token token = tokenOptional.get();
			Timestamp now = Timestamp.from(Instant.now());
			if(now.after(token.getExpireDate())) {
				model.addAttribute("error", "Your secure code has expired.");
				return "welcome/code-verification";
			}else {
				if(code.equals(token.getValue())) {

					redirectAttributes.addFlashAttribute("idUser", token.getUser().getIdAppUser());
					this.tokenService.deleteToken(token);
					return "redirect:/reset-password";

				}else {
					log.error("Secure code bad -> {}", codeVerificationDTO.toString());
					model.addAttribute("error", "Your secure code is incorrect.");
					model.addAttribute("idToken",id);
					return "welcome/code-verification";
				}
			}
		}else {
			model.addAttribute("error", "Your secure code was not founded.");
			return "welcome/code-verification";
		}
		
	}

    public void saveAccount(Model model, UserDTO userDTO) {

		try{
			validateNewUser(userDTO);
			loadAndSaveUSer(userDTO);

			model.addAttribute("generalMessage","Your account has been created successfully, please contact your admin for role configuration");
		}catch (UserException e){
			model.addAttribute("error", e.getMessage());
		}
    }

	private void loadAndSaveUSer(UserDTO userDTO) {
		User user = new User();
		final Timestamp now = Timestamp.from(Instant.now());
		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

 		user.setFirstName(userDTO.getFirstName());
		user.setEmail(userDTO.getEmail());
		user.setLastName(userDTO.getLastName());
		user.setStatus((byte) 1);
		user.setModificationDate(now);
		user.setCreationDate(now);
		user.setUserPassword(encoder.encode(userDTO.getPassword()));

		this.userRepository.save(user);
	}

	private void validateNewUser(UserDTO userDTO) throws UserException {

		if (!userDTO.getPassword().equals(userDTO.getConfirmPassword()))
			throw new UserException("Your password must be equals");
	}

	private Map<String, Object> loadMap(ForgotPasswordDT forgotPasswordDT, User user) throws ForgotPasswordProcessException {
		Map<String, Object> content = new HashMap<>();
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
	    
		content.put("date", myDateObj.format(myFormatObj));
		
		String secureCodeToken = RandomStringUtils.random(5, false, true);
		content.put("token", secureCodeToken);
		
		Optional<Request> request = this.requestService.findById(forgotPasswordDT.getIdRequest());
		
		if(request.isPresent()) {
			Token token = new Token();
			token.setValue(secureCodeToken);
			token.setIsValid(true);
			
			Instant instant = Instant.now();
			
			token.setCreationDate(Timestamp.from(instant));
			token.setExpireDate(Timestamp.from(instant.plusSeconds(15*60)));
			token.setUser(user);
			token.setRequest(request.get());
			
			this.tokenService.save(token);
			
			final String urlRecovery = String.format(URL_RECOVERY_PASSWORD, token.getIdToken());
			
			content.put("urlForgot", urlRecovery);
			
			return content;
		}else {
			throw new ForgotPasswordProcessException("The request of forgot password don't exists");
		}
		
	}

	public void saveNewPasswordProcess(Model model, ResetPasswordDTO resetPasswordDTO, int idUser) {
		try {
			
			User user = this.userRepository.findById(idUser).get();

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			validateInputs(resetPasswordDTO, encoder);

			user.setUserPassword(encoder.encode(resetPasswordDTO.getNewPassword()));
			this.userRepository.save(user);
				
			model.addAttribute("generalMessage", "The password has been restored");
			model.addAttribute("idUser", idUser);
		} catch (ForgotPasswordProcessException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("idUser", idUser);
			log.error("Error when inputs were validated");
		}
		
	}

	private void validateInputs(ResetPasswordDTO resetPasswordDTO, BCryptPasswordEncoder encoder) throws ForgotPasswordProcessException {
		if(!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getRepeatNewPassword())) {
			throw new ForgotPasswordProcessException("Your new password are not the same");
		}
	}
}
