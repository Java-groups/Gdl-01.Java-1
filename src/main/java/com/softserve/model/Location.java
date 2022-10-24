package com.softserve.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {

	@Id
	@Column(name = "id_location")
	private Integer idLocation;
	
	@Column(name = "description", length = 30)
	private String description;
	
	@Column(name = "creation_date")
	private Timestamp creationDate;
	
	@Column(name = "modification_date")
	private Timestamp modificationDate;

}
