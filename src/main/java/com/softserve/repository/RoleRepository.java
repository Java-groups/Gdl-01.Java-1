package com.softserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softserve.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
