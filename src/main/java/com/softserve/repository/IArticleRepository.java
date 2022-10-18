package com.softserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softserve.model.Article;

/**
 * 
 * @author José Castellanos
 * Repository for Article data retrieving
 */
@Repository
public interface IArticleRepository extends JpaRepository<Article, Integer>{
    

}
