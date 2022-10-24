package com.softserve.controller.article;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {

	@GetMapping("/article/new")
	public String newArticle(Model model) {
		return "article/new-article";
	}
}
