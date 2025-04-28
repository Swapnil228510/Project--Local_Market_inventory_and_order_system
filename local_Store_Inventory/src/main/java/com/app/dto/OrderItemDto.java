package com.app.dto;


import lombok.Data;

@Data
public class OrderItemDto {

	private Long productId;
	
	private Double quantity;
}
