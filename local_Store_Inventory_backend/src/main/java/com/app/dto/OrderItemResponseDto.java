package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"productName","unitPrice","quantity"})
public class OrderItemResponseDto {

	@JsonProperty("productName")
	private String name; 
	private double quantity;
	private double unitPrice;
}
