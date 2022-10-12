package com.softserve.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String welcome() {
		return "welcome/index";
	}
	
	@GetMapping("/home")
	public String home (){
		return "upload";
	}
	
	@GetMapping("/forgot-password")
	public String forgotPassword() {
		return "welcome/forgot-password";
	}
}
