package com.app.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequestDto {

	private Long userId;
	
	private List<OrderItemDto> orderItemlist;
}
