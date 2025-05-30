package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {

	
	Optional<Product> findById(Long id);
	
	List<Product> findByCategoryId(Long categoryId);
	
}
