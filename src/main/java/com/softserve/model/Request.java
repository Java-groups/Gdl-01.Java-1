package com.softserve.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

	@Id
	@Column(name = "id_request")
	private Integer idRequest;
	
	@Column(name = "request_type")
	private Integer request_type;
	
	@Column(name = "modification_date")
	private Timestamp modificationDate;
	
	@OneToMany(mappedBy = "request",cascade = CascadeType.ALL)
	private List<Token> tokenList;
}
