package com.softserve.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.softserve.service.ArticleService;
import com.softserve.service.SubCategoryService;

@Controller
public class HomeController {
	@Autowired
	private SubCategoryService categoriesService;

	private ArticleService articleService;
	
	public HomeController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
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
		this.categoriesService.loadContentMain(model);
		return "index";
	}
	
	@GetMapping("/homeArticle/article/description")
	public String articleDescription(Model model, @RequestParam(value = "id")Integer idArticle) {
		this.articleService.loadArticleDescription(model, idArticle);
		return "dashboard/article-description";
	}
}
