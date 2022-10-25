package com.softserve.controller.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.softserve.dto.NewArticleDTO;
import com.softserve.service.ArticleService;

import javax.annotation.security.RolesAllowed;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/article/new")
	public String newArticle(Model model, @ModelAttribute("article") NewArticleDTO newArticleDTO) {
		this.articleService.loadNewArticleContent(model, newArticleDTO);
		return "article/new-article";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/article/new")
	public String newArticleSave(Model model, @ModelAttribute("article") NewArticleDTO newArticleDTO) {
		this.articleService.saveArticle(model, newArticleDTO);
		return "article/new-article";
	}
}
