package com.softserve.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
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
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final String baseUrl;
	
	public ArticleService(@Value("${firebase.base-url}") String baseUrl) {
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
	public void welcomeTo(UserDetails userDetails) {
		log.info("User log in: {}", userDetails.getUsername());
	}
    
}
