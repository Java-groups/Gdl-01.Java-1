package com.softserve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

	private Integer idArticle;
	
	private Integer idCategory;
	
	private String categoryType;
	
	private String title;
}
