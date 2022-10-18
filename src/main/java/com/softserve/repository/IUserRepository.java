package com.softserve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softserve.model.User;

/**
 * 
 * @author José Castellanos
 * Repository for User table
 */
public interface IUserRepository extends JpaRepository<User, Integer>{

	/**
	 * 
	 * @param firstName Find user by the username
	 * @return User founded
	 */
	Optional<User> findByFirstName(String firstName);
	
	/**
	 * 
	 * @param email find user by email address
	 * @return User founded
	 */
	Optional<User> findByEmail(String email);
	
}
