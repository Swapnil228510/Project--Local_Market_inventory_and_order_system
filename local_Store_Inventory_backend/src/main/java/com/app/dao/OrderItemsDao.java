package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.OrderItem;

public interface OrderItemsDao extends JpaRepository<OrderItem, Long> {

	public List<OrderItem> findByOrderId(Long orderId);
	
	public List<OrderItem> findAllByOrderId(Long OrderId);
}
