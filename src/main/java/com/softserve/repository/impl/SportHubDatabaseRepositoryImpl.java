package com.softserve.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.softserve.dto.ArticleDTO;
import com.softserve.repository.SportHubDatabaseRepository;

/**
 * 
 * @author José Castellanos
 * Implementation of SportHubDatabaseRepository that helps to build complex queries
 */
@Repository
public class SportHubDatabaseRepositoryImpl implements SportHubDatabaseRepository {

	private JdbcTemplate jdbcTemplate;
	
	public SportHubDatabaseRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<ArticleDTO> findArticlesFromCategories(Integer idCategoria) {
		final String query = String.format(
				"select ac.id_article , c.id_category , c.category_type , a.title"
						+ "				from category c join article_category ac"
						+ "				ON c.id_category  = ac.id_category "
						+ "				join article a on a.id_article = ac.id_article  where c.id_category  =%d",
				idCategoria);

		return jdbcTemplate.query(query, new RowMapper<ArticleDTO>() {

			@Override
			public ArticleDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArticleDTO articleDTO = new ArticleDTO();
				articleDTO.setIdArticle(rs.getInt("id_article"));
				articleDTO.setIdCategory(rs.getInt("id_category"));
				articleDTO.setCategoryType(rs.getString("category_type"));
				articleDTO.setTitle(rs.getString("title"));
				return articleDTO;
			}

		});
	}

}
