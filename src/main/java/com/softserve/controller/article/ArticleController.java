package com.softserve.controller.article;

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
	public String newArticle(Model model) {
		return "article/new-article";
	}
}
