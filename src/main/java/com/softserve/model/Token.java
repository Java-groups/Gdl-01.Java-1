package com.softserve.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_token")
	private Integer idToken;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "creation_date")
	private Timestamp creationDate;
	
	@Column(name = "expire_date")
	private Timestamp expireDate;

	@Column(name = "is_valid")
	private Boolean isValid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_request")
	private Request request;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_app_user")
	private User user;
	
	
	
	
}
