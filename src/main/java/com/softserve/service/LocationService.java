package com.softserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.model.Location;
import com.softserve.repository.ILocationRepository;

@Service
public class LocationService {

	@Autowired
	private ILocationRepository locationRepository;
	
	public List<Location> findAll(){
		return this.locationRepository.findAll();
	}
}
