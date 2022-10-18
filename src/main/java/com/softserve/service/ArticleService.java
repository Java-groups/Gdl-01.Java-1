package com.softserve.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.softserve.model.Article;
import com.softserve.repository.IArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private IArticleRepository articleRepository;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	private final String baseUrl;
	
	public ArticleService(@Value("${firabe.base-url}") String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public void loadArticleDescription(Model model, Integer idArticle) {
		this.subCategoryService.loadContentMain(model);
		final Optional<Article> articleOptional = this.articleRepository.findById(idArticle);
		
		if(articleOptional.isPresent()) {
			Article article = articleOptional.get();
			article.setImage(String.format(this.baseUrl, article.getImage()));
			model.addAttribute("article", article);	
		}
		
		
	}
    
}
