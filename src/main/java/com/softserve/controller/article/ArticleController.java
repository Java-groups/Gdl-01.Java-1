package com.softserve.controller.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.softserve.dto.NewArticleDTO;
import com.softserve.service.ArticleService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@GetMapping("/article/new")
	public String newArticle(Model model, @ModelAttribute("article") NewArticleDTO newArticleDTO) {
		this.articleService.loadNewArticleContent(model, newArticleDTO);
		return "article/new-article";
	}
	
	@PostMapping("/article/new")
	public String newArticleSave(Model model, @ModelAttribute("article") NewArticleDTO newArticleDTO) {
		System.out.println("Some text");
		return "article/new-article";
	}
}
