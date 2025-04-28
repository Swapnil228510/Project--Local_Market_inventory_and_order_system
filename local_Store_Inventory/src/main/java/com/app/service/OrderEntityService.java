package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.ComboPlacedOrderDto;
import com.app.dto.OrderRequestDto;
import com.app.dto.OrderSummaryStaffDto;
import com.app.pojos.OrderEntity;

public interface OrderEntityService {

	public ApiResponse placeOrder(OrderRequestDto orderDto); 
	
	public List<ComboPlacedOrderDto> getPlacedOrderDetailsByUserId (Long userId);
	
	public List<OrderSummaryStaffDto> getAllOrder();
	
	public OrderSummaryStaffDto getOrderDetailsWithOrderId(Long orderId);
}