package com.softserve.controller.home;

import com.softserve.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserve.dto.CodeVerificationDTO;
import com.softserve.dto.ForgotPasswordDT;
import com.softserve.dto.ResetPasswordDTO;
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
	
	@PostMapping("/forgot-password/recovery")
	public String codeVerificationRequest(@RequestParam(name = "id", required = false) int id, Model model, @ModelAttribute("code") CodeVerificationDTO codeVerificationDTO, RedirectAttributes redirectAttributes) {
		return this.userServices.codeVerificationRequest(id, model, codeVerificationDTO, redirectAttributes);
	}
	
	@GetMapping("/reset-password")
	public String resetPassword(Model model, @ModelAttribute("resetPassword") ResetPasswordDTO resetPasswordDTO) {
		return "welcome/reset-password";
	}
	
	@PostMapping("/reset-password")
	public String resetPasswordSave(Model model, @ModelAttribute("resetPassword") ResetPasswordDTO resetPasswordDTO, @RequestParam(name = "id", required = false) int idUser) {
		this.userServices.saveNewPasswordProcess(model, resetPasswordDTO, idUser);
		return "welcome/reset-password";
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

	@GetMapping("/create-account")
	public String createAccount(Model model, @ModelAttribute("user") UserDTO userDTO){
		return "user/new-user";
	}

	@PostMapping("/create-account")
	public String createAccountSave(Model model, @ModelAttribute("user") UserDTO userDTO){
		this.userServices.saveAccount(model, userDTO);
		return "user/new-user";
	}
	@GetMapping("/access-denied")
	public String accessDenied(){
		return "access-denied/denied";
	}
}
