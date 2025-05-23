package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Cart;
import com.app.pojos.User;

@Repository
public interface CartDao extends JpaRepository<Cart, Long> {
	
	Optional<Cart> findByUser(User user);
	Optional<Cart> findByUserId(Long userId);

}
