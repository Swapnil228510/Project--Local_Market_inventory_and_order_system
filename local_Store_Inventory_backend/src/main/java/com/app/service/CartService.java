package com.app.service;

import com.app.dto.AddToCartRequestDto;
import com.app.dto.CartResponseDto;

public interface CartService {

	public String addToCart(AddToCartRequestDto cartDto); 
	
	public CartResponseDto getCartByUserId(Long userId);
}
