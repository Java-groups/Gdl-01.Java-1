package com.softserve.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.softserve.exceptions.ArticleException;
import com.softserve.model.Team;
import com.softserve.model.User;
import com.softserve.security.user.UserServices;
import com.softserve.util.FireBaseProcess;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Autowired
	private UserServices userServices;

	@Autowired
	private FireBaseProcess fireBaseProcess;

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
		log.info("User log in: {} with roles: {}", userDetails.getUsername(), userDetails.getAuthorities());
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

	public void saveArticle(Model model, NewArticleDTO newArticleDTO) {

		try {
			loadArticle(newArticleDTO);

			model.addAttribute("generalMessage", "The article was created successfully.");
			loadNewArticleContent(model, newArticleDTO);
		}catch (ArticleException articleException){
			log.error("Error when article was be builded. ->{}", articleException);
			model.addAttribute("error", articleException.getMessage());
			loadNewArticleContent(model, newArticleDTO);
		}

	}

	private Article loadArticle(NewArticleDTO newArticleDTO) throws ArticleException{

		if(newArticleDTO.getArticleDescriptionHtml().equals(""))
			throw new ArticleException("The article description is required");
		if(newArticleDTO.getArticleImage().getOriginalFilename().equals(""))
			throw new ArticleException("The article image is required");

		Article article = new Article();

		article.setCreationDate(Timestamp.from(Instant.now()));
		article.setModificationDate(Timestamp.from(Instant.now()));
		article.setDescriptionHtml(newArticleDTO.getArticleDescriptionHtml());
		article.setDescription(newArticleDTO.getArticleDescriptionHtml());
		article.setIsCommentable(true);
		article.setStatus(1);
		final Optional<Team> teamOptional = this.teamService.finById(newArticleDTO.getTeam());

		if(!teamOptional.isPresent())
			throw new ArticleException("The team was not founded.");

		final Team team = teamOptional.get();
		article.setTeam(team);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		final String userName = authentication.getName();

		User user = this.userServices.findByEmail(userName).get();

		article.setIdAppUser(user.getIdAppUser());
		article.setTitle(newArticleDTO.getHeadLine());

		final String ALT_MEDIA = "?alt=media";

		final String articleImageName = String.format("%s%s",this.fireBaseProcess.upload(newArticleDTO.getArticleImage(), article), ALT_MEDIA);
		article.setImage(articleImageName);
		this.articleRepository.save(article);
		return article;
	}
}
