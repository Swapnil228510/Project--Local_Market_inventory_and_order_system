package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Cart;
import com.app.pojos.CartItem;
import com.app.pojos.Product;

@Repository
public interface CartItemDao extends JpaRepository<CartItem, Long> {
	
	Optional<CartItem> findByCartAndProduct(Cart cart, Product product );
	
	List<CartItem> findByCartId(Long cartId);

}
