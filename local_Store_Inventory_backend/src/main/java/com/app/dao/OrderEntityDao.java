package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.OrderEntity;

@Repository
public interface OrderEntityDao extends JpaRepository<OrderEntity, Long> {
	
	List<OrderEntity> findAllByUserId(Long userId);
	
	List<OrderEntity> findAll();

}
