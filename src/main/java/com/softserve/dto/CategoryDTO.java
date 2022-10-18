package com.softserve.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Jose Castellanos
 * DTO for transfer from backend data to frontend
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private List<CategoryDTO> subCategories;
	
	private List<ArticleDTO> articles;
}
