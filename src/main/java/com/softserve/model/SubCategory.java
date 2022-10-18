package com.softserve.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Luis Ábrego
 * Entity for subcategory table
 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_category")
	private Integer idCategory;
	
	@Column(name = "\"name\"", length = 30)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "id_parent_category")
	private Integer idParentCategory;
	
	@Column(name = "category_type", length = 20)
	private String categoryType;
	
	@Column(name = "status", length = 20)
	private Byte status;
	
	@Column(name = "creation_date")
	private Timestamp creationDate;
	
	@Column(name = "modification_date")
	private Timestamp modificationDate;
	
}
