package com.softserve.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.model.Token;
import com.softserve.repository.TokenRepository;

@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRepository;
	
	public Token save(Token token) {
		return this.tokenRepository.save(token);
	}

	public Optional<Token> findById(int id) {
		return this.tokenRepository.findById(id);
	}

	public void deleteToken(Token token) {
		this.tokenRepository.delete(token);
		
	}
}
