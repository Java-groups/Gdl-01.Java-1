package com.softserve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softserve.model.User;


public interface IUserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByFirstName(String firstName);
}
