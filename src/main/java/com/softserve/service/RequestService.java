package com.softserve.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.model.Request;
import com.softserve.repository.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestRepository;
	
	public Optional<Request> findById(Integer id){
		return this.requestRepository.findById(id);
	}
	
	public Request save(Request request) {
		return this.requestRepository.save(request);
	}
}
