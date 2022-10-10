package com.softserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.model.Role;
import com.softserve.repository.RoleRepository;

@Service
public class RoleServices {

	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> findAll(){
		return roleRepository.findAll();
	}
}
