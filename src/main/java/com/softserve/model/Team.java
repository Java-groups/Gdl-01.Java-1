package com.softserve.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_team")
	private Integer idTeam;
	
	@Column(name = "description")
	private String description;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_location")
	private Location location;

	@OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
	private List<Article> articleList;

}
