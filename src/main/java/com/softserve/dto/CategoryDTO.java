package com.softserve.dto;

import java.io.Serializable;
import java.util.List;

import com.softserve.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private List<Category> subCategories;
}
