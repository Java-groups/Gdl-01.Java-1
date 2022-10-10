package com.softserve.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

	@Id
	@Column(name = "id_app_user")
	private Integer idAppUser;
	
	@Column(name = "lastname", length = 30)
	private String lastName;
	  
	@Column(name="firstname", length = 20)
	private String firstName;
	  
	@Column(name="email", length = 50)
	private String email;
	  
	@Column(name = "user_password", length = 32)
	private String userPassword;
	  
	@Column(name="profile_picuture", length = 120)
	private String profilePicture;
	  
	@Column(name="id_role")
	private Integer idRole;
	  
	@Column(name="status")
	private Byte status;
	
	@Column(name="creation_date")
	private Date creationDate;
	
	@Column(name="modification_date")
	private Date modificationDate;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="user_roles", 
				joinColumns = @JoinColumn(name="user_id", referencedColumnName = "ID_APP_USER"),
				inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id_role"))
	private List<Role> roles;
}
