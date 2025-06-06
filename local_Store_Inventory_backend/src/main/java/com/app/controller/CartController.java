package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddToCartRequestDto;
import com.app.dto.ApiResponse;
import com.app.dto.CartUpadateMultipleItemsRequestDto;
import com.app.service.CartService;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	//User API
	@PostMapping("/add")
	public ResponseEntity<?> addToCart(@RequestBody AddToCartRequestDto cartItems){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(cartService.addToCart(cartItems), true));	
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getCartDetailsbyUserId(@PathVariable Long userId){
		
		return ResponseEntity.status(HttpStatus.OK).body(cartService.getCartByUserId(userId));
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateCartMultipleItems(@RequestBody CartUpadateMultipleItemsRequestDto upatedItems){
		
		return ResponseEntity.status(HttpStatus.OK).body(cartService.updateMultipleCartItems(upatedItems));
		
	}

}
