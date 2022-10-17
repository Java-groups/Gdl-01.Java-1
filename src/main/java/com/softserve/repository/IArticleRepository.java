package com.softserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softserve.model.Category;

@Repository
public interface IArticleRepository extends JpaRepository<Category, Integer>{
    

}
