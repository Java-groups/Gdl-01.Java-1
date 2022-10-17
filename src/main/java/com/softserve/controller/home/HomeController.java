package com.softserve.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.softserve.service.SubCategoryService;

@Controller
public class HomeController {
	@Autowired
	private SubCategoryService articleService;

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

	@GetMapping("/homeArticle")
	public String homeArticle(Model model) {
		this.articleService.loadContentMain(model);
		return "index";
	}
}
