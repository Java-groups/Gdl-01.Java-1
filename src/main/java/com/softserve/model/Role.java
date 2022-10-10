package com.softserve.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "id_role")
	private Integer idRole;
	
	private String name;
	
	private Integer status;
	
	public Role(String name) {
		this.name = name;
		this.status=1;
	}
	
}
