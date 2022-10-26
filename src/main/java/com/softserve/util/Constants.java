package com.softserve.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Constants {

	@Value("${spring.mail.username}")
	public static String EMAIL_USERNAME;
	
	@Value("${template.forgot-password}")
	public static String TEMPLATE_FORGOT_PASSWORD;
	
	
	
}
