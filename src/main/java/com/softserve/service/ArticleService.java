package com.softserve.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.softserve.dto.CategoryDTO;
import com.softserve.dto.LocationDTO;
import com.softserve.dto.NewArticleDTO;
import com.softserve.dto.TeamDTO;
import com.softserve.model.Article;
import com.softserve.repository.IArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private IArticleRepository articleRepository;
	
	@Autowired
	private SubCategoryService subCategoryService;

	@Autowired
	private TeamService teamService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private CategoryService categoryService;
	
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
	
	public void loadNewArticleContent(Model model, NewArticleDTO newArticleDTO) {
		final List<TeamDTO> teamList = this.teamService.findAll().stream().map(team ->
			new TeamDTO(team.getIdTeam(), team.getDescription())
		).collect(Collectors.toList());
		
		final List<LocationDTO> locationList = this.locationService.findAll().stream().map(location ->
												new LocationDTO(location.getIdLocation(), location.getDescription()))
												.collect(Collectors.toList());
		List<CategoryDTO> categoriesList = this.categoryService.findByIdParentCategoryIsNull().stream()
											.map(category -> 
											new CategoryDTO(category.getIdCategory(), category.getName())
											).collect(Collectors.toList());
		
		model.addAttribute("teamList", teamList);
		model.addAttribute("locationList", locationList);
		model.addAttribute("categoriesList", categoriesList);
	}
    
}
