package com.softserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softserve.model.Location;

@Repository
public interface ILocationRepository extends JpaRepository<Location, Integer> {

}
