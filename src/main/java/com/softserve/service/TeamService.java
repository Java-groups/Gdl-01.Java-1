package com.softserve.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softserve.model.Team;
import com.softserve.repository.ITeamRepository;

@Service
public class TeamService {

	@Autowired
	private ITeamRepository teamRepository;
	
	
	public List<Team> findAll(){
		return this.teamRepository.findAll();
	}

    public Optional<Team> finById(int idTeam) {
		return this.teamRepository.findById(idTeam);
    }
}
