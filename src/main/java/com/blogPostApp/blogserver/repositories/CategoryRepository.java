package com.blogPostApp.blogserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogPostApp.blogserver.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
