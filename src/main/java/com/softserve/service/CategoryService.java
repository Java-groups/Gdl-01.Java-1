package com.softserve.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.model.Category;
import com.softserve.repository.ICategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;
	
	/**
	 * Custom query for searching parentCategories
	 * @return the list of parentCategories
	 */
	public List<Category> findByIdParentCategoryIsNull(){
		return this.categoryRepository.findByIdParentCategoryIsNull();
	}
	
	
	/**
	 * Searching for subCategories of each parentCategory
	 * @param idCategoryParent id of the parentCategory
	 * @return the list with subCategories
	 */
	public List<Category> findByCategoryParent(Integer idCategoryParent){
		return this.categoryRepository.findByIdParentCategory(idCategoryParent);
	}

	public Optional<Category> findById(Integer id) {
        Category category = categoryRepository.findById(id).get();
        return category != null ? Optional.of(category) : Optional.empty();
    }
	
}
