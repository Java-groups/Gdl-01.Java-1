package com.softserve.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.softserve.dto.CategoryDTO;
import com.softserve.model.Category;

@Service
public class SubCategoryService {

	private CategoryService categoryService;

	public SubCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void loadContentMain(Model model) {

		List<Category> listCategories = this.categoryService.findByIdParentCategoryIsNull();


		List<CategoryDTO> categoriesDtos = listCategories.stream().map(category -> {
			CategoryDTO categoryDTO = new CategoryDTO();
			
			categoryDTO.setName(category.getName());

			List<Category> subCategories = categoryService.findByCategoryParent(category.getIdCategory());
			
			categoryDTO.setSubCategories(subCategories.stream().map(subcategory -> {
				return subcategory;
			}).collect(Collectors.toList()));
			return categoryDTO;
			
		}).collect(Collectors.toList());
		
		model.addAttribute("categories", categoriesDtos);
	}
}
