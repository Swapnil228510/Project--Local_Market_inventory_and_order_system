package com.app.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"OrderId", "orderDate", "totalAmount","userName","email","mobNo", "DetailedOrderItems"})
public class OrderSummaryStaffDto {
	
	@JsonProperty("OrderId")
	private double id;
	
	private LocalDate orderDate;
	
	private double totalAmount;
	
	@JsonProperty("userName")
	private String name ;
	
	private String email;
	
	private String mobNo;
	
	@JsonProperty("DetailedOrderItems")
	private List<OrderItemResponseDto> orderItemResponseDto;
	
	
	

}
