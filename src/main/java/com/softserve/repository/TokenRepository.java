package com.softserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softserve.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

}
