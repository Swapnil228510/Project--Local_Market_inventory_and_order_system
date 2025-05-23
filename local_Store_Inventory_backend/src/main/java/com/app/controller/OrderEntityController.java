package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.OrderRequestDto;
import com.app.service.OrderEntityService;

@RestController
@CrossOrigin("*")
@RequestMapping("/orderEntity")
public class OrderEntityController {
	
	@Autowired
	private OrderEntityService orderEntityService;
	

	@PostMapping("/placeOrder")
	public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDto orderDto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(orderEntityService.placeOrder(orderDto));
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getPlacedOrderByUserId(@PathVariable Long userId){
//		System.out.println(" in get Placed Order controller");
		return ResponseEntity.status(HttpStatus.OK).body(orderEntityService.getPlacedOrderDetailsByUserId(userId));
	}
	
	//ADMIN API
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<?> getAllOrders(){
		return ResponseEntity.status(HttpStatus.OK).body(orderEntityService.getAllOrder());	
	}
	
	// STAFF/ADMIN API
	@PreAuthorize("hasAnyRole('STAFF','ADMIN')")
	@GetMapping("/orderByOrderId/{orderId}")
	public ResponseEntity<?> getOrderByOrderId (@PathVariable Long orderId){
		return ResponseEntity.status(HttpStatus.FOUND).body(orderEntityService.getOrderDetailsWithOrderId(orderId));
	}

}
