package com.app.service;

import com.app.dto.AddToCartRequestDto;
import com.app.dto.CartResponseDto;
import com.app.dto.CartUpadateMultipleItemsRequestDto;

public interface CartService {

	public String addToCart(AddToCartRequestDto cartDto); 
	
	public CartResponseDto getCartByUserId(Long userId);
	
	public CartResponseDto updateMultipleCartItems(CartUpadateMultipleItemsRequestDto items);
}
