package com.softserve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softserve.model.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {
	/*
	 * Custom query for searching main categories or parent categories
	 */
	List<Category> findByIdParentCategoryIsNull();

	/*
	 * Custom query for searching subCategories
	 */
	List<Category> findByIdParentCategory(Integer idParentCategory);

}
