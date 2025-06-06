package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {
	
	public Category findByName(String name);

}
