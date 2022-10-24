package com.softserve.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.dto.CodeVerificationDTO;
import com.softserve.dto.ForgotPasswordDT;
import com.softserve.security.user.UserServices;
import com.softserve.service.ArticleService;
import com.softserve.service.SubCategoryService;

@Controller
public class HomeController {
	@Autowired
	private SubCategoryService categoriesService;

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserServices userServices;
	
	@GetMapping("/")
	public String welcome() {	
		return "welcome/index";
	}
	
	@GetMapping("/home")
	public String home (){
		return "upload";
	}
	
	@GetMapping("/forgot-password")
	public String forgotPassword(@ModelAttribute("forgotPassword") ForgotPasswordDT forgotPasswordDT, Model model) {
		return "welcome/forgot-password";
	}
	
	@PostMapping("/forgot-password")
	public String restartPassword(@ModelAttribute("forgotPassword") ForgotPasswordDT forgotPasswordDT, Model model) {
		this.userServices.forgotPasswordProcess(forgotPasswordDT, model);
		return "welcome/forgot-password";
	}

	@GetMapping("/forgot-password/recovery")
	public String codeVerification(@RequestParam(name = "id", required = false) int id, Model model, @ModelAttribute("code") CodeVerificationDTO codeVerificationDTO) {
		this.userServices.verificationCodeProcess(id, model);
		return "welcome/code-verification";
	}
	
	@GetMapping("/start")
	public String startServices(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		this.articleService.welcomeTo(userDetails);
		return "redirect:/homeArticle";
	}
	@GetMapping("/homeArticle")
	public String homeArticle(Model model) {
		this.categoriesService.loadContentMain(model);
		return "index";
	}
	
	@GetMapping("/homeArticle/article/description")
	public String articleDescription(Model model, @RequestParam(value = "id")Integer idArticle) {
		this.articleService.loadArticleDescription(model, idArticle);
		return "dashboard/article-description";
	}
}
