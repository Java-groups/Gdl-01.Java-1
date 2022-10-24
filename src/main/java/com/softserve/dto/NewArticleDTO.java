package com.softserve.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewArticleDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MultipartFile articleImage;
	
	private int subCategory;
	
	private int team;
	
	private int location;
	
	private String headLine;
	
	private String caption;
	
	private String articleDescriptionHtml;
	

}
