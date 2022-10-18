package com.softserve.repository;

import java.util.List;

import com.softserve.dto.ArticleDTO;

/**
 * 
 * @author José Castellanos
 * Repository for build sql explicit
 */
public interface SportHubDatabaseRepository {

	/**
	 * 
	 * @param categoryId 
	 * @return List of the Articles ArticleDTO
	 */
	List<ArticleDTO> findArticlesFromCategories(Integer categoryId);
}
