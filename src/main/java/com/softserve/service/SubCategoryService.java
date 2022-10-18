package com.softserve.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.softserve.dto.CategoryDTO;
import com.softserve.model.Category;
import com.softserve.repository.SportHubDatabaseRepository;
/**
 * 
 * @author José Castellanos & Luis Ábrego
 * Service layer that help to load resource
 */
@Service
public class SubCategoryService {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SportHubDatabaseRepository databaseRepository;

	/**
	 * This method will load the main content for home page
	 * @param model contais all context for frontend side
	 */
	public void loadContentMain(Model model) {

		List<Category> listCategories = this.categoryService.findByIdParentCategoryIsNull();

		List<CategoryDTO> categoriesDtos = listCategories.stream().map(category -> {
			CategoryDTO categoryDTO = new CategoryDTO();

			categoryDTO.setName(category.getName());

			List<Category> subCategories = categoryService.findByCategoryParent(category.getIdCategory());

			categoryDTO.setSubCategories(subCategories.stream().map(subCategory -> {
				CategoryDTO categoryDTO2 = new CategoryDTO();

				categoryDTO2.setName(subCategory.getName());
				categoryDTO2.setArticles(this.databaseRepository
										.findArticlesFromCategories(subCategory.getIdCategory()));
				return categoryDTO2;
			}).collect(Collectors.toList()));

			return categoryDTO;

		}).collect(Collectors.toList());

		model.addAttribute("categories", categoriesDtos);
	}
}
