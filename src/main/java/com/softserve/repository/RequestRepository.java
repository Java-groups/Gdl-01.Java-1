package com.softserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softserve.model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

}
