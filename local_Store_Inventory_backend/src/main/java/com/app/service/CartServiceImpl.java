package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CartDao;
import com.app.dao.CartItemDao;
import com.app.dao.ProductDao;
import com.app.dao.UserDao;
import com.app.dto.AddToCartRequestDto;
import com.app.dto.CartItemResponseDto;
import com.app.dto.CartItemUpdateDto;
import com.app.dto.CartResponseDto;
import com.app.dto.CartUpadateMultipleItemsRequestDto;
import com.app.exception.ResourceNotFoundException;
import com.app.pojos.Cart;
import com.app.pojos.CartItem;
import com.app.pojos.Product;
import com.app.pojos.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private CartItemDao cartItemDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public String addToCart(AddToCartRequestDto cartDto) {
		
		User user = userDao.findById(cartDto.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User is not present "));
		
		Product product = productDao.findById(cartDto.getProductId()).orElseThrow(()-> new ResourceNotFoundException("Product is not present"));
		
		Cart cart = cartDao.findByUser(user).orElseGet(()->{
			Cart newCart = new Cart();
			newCart.setUser(user);
			return cartDao.save(newCart);				
		});
		
		Optional<CartItem> existingItem = cartItemDao.findByCartAndProduct(cart, product);
		
		if(existingItem.isPresent()) {
			CartItem item = existingItem.get();
			item.setQuantity(item.getQuantity() + cartDto.getQuantity());
			cartItemDao.save(item);
		}else {
			CartItem newCartItem = new CartItem();
			newCartItem.setCart(cart);
			newCartItem.setProduct(product);
			newCartItem.setQuantity(cartDto.getQuantity());
			cartItemDao.save(newCartItem);
		}
		
		return "Product added to cart successfully";
	}

	@Override
	public CartResponseDto getCartByUserId(Long userId) {
		Cart cartInfo = cartDao.findByUserId(userId).orElseThrow(()-> new ResourceNotFoundException(" Cart is not Found !! "));
		
		List<CartItem> cartItemsList =  cartItemDao.findByCartId(cartInfo.getId());
		
		List<CartItemResponseDto> cartItemsDtoList = new ArrayList<CartItemResponseDto>();
		
		Double totalAmout = 0.0;
		
		for( CartItem items : cartItemsList) {
			
			
			CartItemResponseDto cartItems = mapper.map(items, CartItemResponseDto.class);
			
			cartItems.setProductId(items.getProduct().getId());
			
			cartItems.setProductName(items.getProduct().getName());
			
			cartItems.setUnitPrice(items.getProduct().getPrize());
			
			Double eachCartItemTotal = items.getQuantity() * items.getProduct().getPrize();
			
			cartItems.setSubtotal(eachCartItemTotal);

			totalAmout += eachCartItemTotal;
			cartItemsDtoList.add(cartItems);		
			
			
		}
		
		CartResponseDto cartResponse = new CartResponseDto();
		
		cartResponse.setCartId(cartInfo.getId());
		cartResponse.setItems(cartItemsDtoList);
		cartResponse.setUserId(userId);
		
		
		cartResponse.setTotalAmount(totalAmout);
		
		return cartResponse;
	}

	@Override
	public CartResponseDto updateMultipleCartItems(CartUpadateMultipleItemsRequestDto updatedItems) {
		Cart cart = cartDao.findByUserId(updatedItems.getUserId()).orElseThrow(()-> new ResourceNotFoundException("Invalid user ID"));
		
		List<CartItemUpdateDto> updatedItemsList = updatedItems.getItems();

		List<CartItem> existingCartItems = cartItemDao.findByCartId(cart.getId());
		
		for( CartItemUpdateDto items : updatedItemsList) {
			
		    Long productId = items.getProductId();
		    double quantity = items.getQuantity();
		    
		    CartItem existingItem = existingCartItems.stream()
								    .filter(item -> item.getProduct().getId().equals(productId))
								    .findFirst()
//								    .orElseThrow(()-> new ResourceNotFoundException("Product is not found "));
								    .orElse(null);
		    
		    if(existingItem != null) {
		    	if(quantity > 0) {
		    		existingItem.setQuantity(quantity);
		    		cartItemDao.save(existingItem);
		    	}else {
		    		cartItemDao.delete(existingItem);
		    	}
		    }else {
		    	
		    	CartItem newItem = new CartItem();
		    	
		    	newItem.setCart(cart);
		    	
		    	//instead of hitting database we can provide proxy of it 
		    	Product product = entityManager.getReference(Product.class, productId);
		    	newItem.setProduct(product);
		    	
		    	newItem.setQuantity(quantity);
		    	
		    	cartItemDao.save(newItem);
		    	
		    }
		        
		}
		
		return getCartByUserId(updatedItems.getUserId());
	}
	
	

}
